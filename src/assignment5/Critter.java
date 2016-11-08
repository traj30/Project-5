/* CRITTERS GUI Critter.java
 * EE422C Project 4b submission by
 * Replace <...> with your actual data.
 * Jake Klovenski
 * jdk2595
 * 16445
 * Tiraj Parikh
 * trp589
 * 16460
 * Slip days used: <0>
 * Fall 2016
 */

package assignment5;

import java.util.List;

import javafx.scene.paint.Color;

import java.util.ArrayList;

public abstract class Critter {
	/* NEW FOR PROJECT 5 */
	public enum CritterShape {
		CIRCLE,
		SQUARE,
		TRIANGLE,
		DIAMOND,
		STAR
	}
	
	/* the default color is white, which I hope makes critters invisible by default
	 * If you change the background color of your View component, then update the default
	 * color to be the same as you background 
	 * 
	 * critters must override at least one of the following three methods, it is not 
	 * proper for critters to remain invisible in the view
	 * 
	 * If a critter only overrides the outline color, then it will look like a non-filled 
	 * shape, at least, that's the intent. You can edit these default methods however you 
	 * need to, but please preserve that intent as you implement them. 
	 */
	public javafx.scene.paint.Color viewColor() { 
		return javafx.scene.paint.Color.WHITE; 
	}
	
	public javafx.scene.paint.Color viewOutlineColor() { return viewColor(); }
	public javafx.scene.paint.Color viewFillColor() { return viewColor(); }
	
	public abstract CritterShape viewShape(); 
	
	private static String myPackage;
	private	static List<Critter> population = new java.util.ArrayList<Critter>();
	private static List<Critter> babies = new java.util.ArrayList<Critter>();

	// Gets the package name.  This assumes that Critter and its subclasses are all in the same package.
	static {
		myPackage = Critter.class.getPackage().toString().split(" ")[1];
	}
	
	protected String look(int direction, boolean steps) 
	{
		int num_steps = 1;
		int vertical = 0;
		int horizontal = 0;
		if(steps)
		{
			num_steps++;
		}

		if(direction == 0 || direction == 1 || direction == 7)
		{
			horizontal = num_steps;
		}
		if(direction == 1 || direction == 2 || direction == 3)
		{
			vertical = -1 * num_steps;
		}
		if(direction == 3 || direction == 4 || direction == 5)
		{
			horizontal = -1 * num_steps;
		}
		if(direction == 5 || direction == 6 || direction == 7)
		{
			horizontal = num_steps;
		}

		int look_x = this.x_coord + horizontal;
		look_x = Math.floorMod(look_x, Params.world_width);
		int look_y = this.y_coord + vertical;
		look_y = Math.floorMod(look_y, Params.world_width);

		this.energy -= Params.look_energy_cost;


		for(Critter c : population)
		{
			if(c.x_coord == look_x && c.y_coord == look_y)
			{
				return c.toString();
			}
		}
		return null;
	}
	
	/* rest is unchanged from Project 4 */
	
	
	private static java.util.Random rand = new java.util.Random();
	public static int getRandomInt(int max) {
		return rand.nextInt(max);
	}
	
	public static void setSeed(long new_seed) {
		rand = new java.util.Random(new_seed);
	}
	
	
	/* a one-character long string that visually depicts your critter in the ASCII interface */
	public String toString() { return ""; }
	
	private int energy = 0;
	protected int getEnergy() { return energy; }
	
	private int x_coord;
	private int y_coord;
	private boolean isFighting;
	private boolean isDead;
	private boolean hasMoved;
	
	/**
	 * makes critter walk 1 step in any of 8 directions
	 * @param direction indicates which direction to move
	 * following are helper functions to walk in a given direction
	 */
	protected final void walk(int direction) {
		this.energy -= Params.walk_energy_cost;
		
		if(this.hasMoved == true){
			return;
		}
		switch(direction)
		{
			case 0:
				this.x_coord+=1;
				break;
			case 1:
				this.x_coord+=1;
				this.y_coord-=1;
				break;
			case 2:
				this.y_coord-=1;
				break;
			case 3:
				this.x_coord-=1;
				this.y_coord-=1;
				break;
			case 4:
				this.x_coord-=1;
				break;
			case 5:
				this.x_coord-=1;
				this.y_coord+=1;
				break;
			case 6:
				this.y_coord+=1;
				break;
			case 7:
				this.x_coord+=1;
				this.y_coord+=1;
				break;
		}

		this.x_coord = Math.floorMod(this.x_coord, Params.world_width);
		this.y_coord = Math.floorMod(this.y_coord, Params.world_height);
		this.hasMoved = true;
	}
	/**
	 * makes critter run 2 steps in any of 8 directions
	 * @param direction determines direction in which to travel
	 * following are helper functions to run in a given direction
	 */
	protected final void run(int direction) 
	{

		this.energy -= Params.run_energy_cost;
		if(this.hasMoved == true){
			return;
		}
		switch(direction)
		{
			case 0:
				this.x_coord+=2;
				break;
			case 1:
				this.x_coord+=2;
				this.y_coord-=2;
				break;
			case 2:
				this.y_coord-=2;
				break;
			case 3:
				this.x_coord-=2;
				this.y_coord-=2;
				break;
			case 4:
				this.x_coord-=2;
				break;
			case 5:
				this.x_coord-=2;
				this.y_coord+=2;
				break;
			case 6:
				this.y_coord+=2;
				break;
			case 7:
				this.x_coord+=2;
				this.y_coord+=2;
				break;
		}

		this.x_coord = Math.floorMod(this.x_coord, Params.world_width);
		this.y_coord = Math.floorMod(this.y_coord, Params.world_height);
		this.hasMoved = true;

	}
	
	/**
	 * creates an offspring adjacent to the parent
	 * @param offspring creates new offspring with half the energy of the parent 
	 * @param direction indicates position adjacent to parent that offspring are born into
	 * makes use of walk helper functions to choose location of birth
	 */
	protected final void reproduce(Critter offspring, int direction) {
		if(this.energy <= Params.min_reproduce_energy){
			return;
		}
		
		offspring.energy = this.energy/2;
		babies.add(offspring);
		int rounded = this.energy/2;
		this.energy = (int) Math.ceil(rounded);

		switch(direction)
		{
			case 0:
				offspring.x_coord = this.x_coord + 1;
				break;
			case 1:
				offspring.x_coord = this.x_coord + 1;
				offspring.y_coord = this.y_coord - 1;
				break;
			case 2:
				offspring.y_coord = this.y_coord - 1;
				break;
			case 3:
				offspring.x_coord = this.x_coord - 1;
				offspring.y_coord = this.y_coord - 1;
				break;
			case 4:
				offspring.x_coord = this.x_coord - 1;
				break;
			case 5:
				offspring.x_coord = this.x_coord - 1;
				offspring.y_coord = this.y_coord + 1;
				break;
			case 6:
				offspring.y_coord = this.y_coord + 1;
				break;
			case 7:
				offspring.x_coord = this.x_coord + 1;
				offspring.y_coord = this.y_coord + 1;
				break;
		}
		offspring.x_coord = Math.floorMod(offspring.x_coord, Params.world_width);
		offspring.y_coord = Math.floorMod(offspring.y_coord, Params.world_height);
	}

	public abstract void doTimeStep();
	public abstract boolean fight(String oponent);
	
	/**
	 * create and initialize a Critter subclass.
	 * critter_class_name must be the unqualified name of a concrete subclass of Critter, if not,
	 * an InvalidCritterException must be thrown.
	 * (Java weirdness: Exception throwing does not work properly if the parameter has lower-case instead of
	 * upper. For example, if craig is supplied instead of Craig, an error is thrown instead of
	 * an Exception.)
	 * @param critter_class_name
	 * @throws InvalidCritterException
	 */
	public static void makeCritter(String critter_class_name) throws InvalidCritterException {
		try {
			Class clazz = Class.forName(myPackage + "." + critter_class_name);
			Critter critter = (Critter)clazz.newInstance();
			critter.x_coord = Critter.getRandomInt(Params.world_width);
			critter.y_coord = Critter.getRandomInt(Params.world_height);
			critter.isDead = false;
			critter.energy = Params.start_energy;
			population.add(critter);
		} catch(ClassNotFoundException e1) {
			throw new InvalidCritterException(critter_class_name);
		} catch(InstantiationException e2) {
			throw new InvalidCritterException(critter_class_name);
	    } catch(IllegalAccessException e3) {
	    	throw new InvalidCritterException(critter_class_name);
	    }
	}
	
	/**
	 * Gets a list of critters of a specific type.
	 * @param critter_class_name What kind of Critter is to be listed.  Unqualified class name.
	 * @return List of Critters.
	 * @throws InvalidCritterException
	 */
	public static List<Critter> getInstances(String critter_class_name) throws InvalidCritterException {
		List<Critter> result = new java.util.ArrayList<Critter>();
		for(Critter x : population)
		{
			if(x.getClass().getName().equals(myPackage + "." + critter_class_name))
			{
				result.add(x);
			}
		}
		return result;
	}
	
	/**
	 * Prints out how many Critters of each type there are on the board.
	 * @param critters List of Critters.
	 */
	public static void runStats(List<Critter> critters) {
		System.out.print("" + critters.size() + " critters as follows -- ");
		java.util.Map<String, Integer> critter_count = new java.util.HashMap<String, Integer>();
		for (Critter crit : critters) {
			String crit_string = crit.toString();
			Integer old_count = critter_count.get(crit_string);
			if (old_count == null) {
				critter_count.put(crit_string,  1);
			} else {
				critter_count.put(crit_string, old_count.intValue() + 1);
			}
		}
		String prefix = "";
		for (String s : critter_count.keySet()) {
			System.out.print(prefix + s + ":" + critter_count.get(s));
			prefix = ", ";
		}
		System.out.println();		
	}
	
	/* the TestCritter class allows some critters to "cheat". If you want to 
	 * create tests of your Critter model, you can create subclasses of this class
	 * and then use the setter functions contained here. 
	 * 
	 * NOTE: you must make sure that the setter functions work with your implementation
	 * of Critter. That means, if you're recording the positions of your critters
	 * using some sort of external grid or some other data structure in addition
	 * to the x_coord and y_coord functions, then you MUST update these setter functions
	 * so that they correctly update your grid/data structure.
	 */
	static abstract class TestCritter extends Critter {
		protected void setEnergy(int new_energy_value) {
			super.energy = new_energy_value;
		}
		
		protected void setX_coord(int new_x_coord) {
			super.x_coord = new_x_coord;
		}
		
		protected void setY_coord(int new_y_coord) {
			super.y_coord = new_y_coord;
		}
		
		protected int getX_coord() {
			return super.x_coord;
		}
		
		protected int getY_coord() {
			return super.y_coord;
		}
		

		/*
		 * This method getPopulation has to be modified by you if you are not using the population
		 * ArrayList that has been provided in the starter code.  In any case, it has to be
		 * implemented for grading tests to work.
		 */
		protected static List<Critter> getPopulation() {
			return population;
		}
		
		/*
		 * This method getBabies has to be modified by you if you are not using the babies
		 * ArrayList that has been provided in the starter code.  In any case, it has to be
		 * implemented for grading tests to work.  Babies should be added to the general population 
		 * at either the beginning OR the end of every timestep.
		 */
		protected static List<Critter> getBabies() {
			return babies;
		}
	}

	/**
	 * Clear the world of all critters, dead and alive
	 */
	public static void clearWorld() {
		babies.clear();
		population.clear();
	}
	
	
	/**
	 * Makes competing critters in the same location fight one another.
	 * One critter will die will be removed from the board while the other
	 * will gain half of that critter's energy.
	 */
	private static void fighting(){
		for(Critter c : population){
			ArrayList<Critter> fighter = new java.util.ArrayList<Critter>();
			for(Critter dTemp : population)
			{
				if(dTemp != c)
				{
					if((c.x_coord == dTemp.x_coord) && (c.y_coord == dTemp.y_coord))
					{
						fighter.add(dTemp);
					}
				}
			}

			if(!fighter.isEmpty())
			{
				for(Critter d : fighter)
				{
					if((c.isDead == false) && (d.isDead == false))
					{
						boolean fightDC = d.fight(c.toString());
						boolean fightCD = c.fight(d.toString());
						//System.out.println(fighter + " " + c.x_coord + " " + c.y_coord + " " + d.x_coord + " " + d.y_coord);
						//System.out.println(fightDC + " " + d.energy + " " + c.energy +" " + fightCD);
						//System.out.println(c.isDead + " " + d.isDead);
						//System.out.println(d + " " + d.energy);
						//System.out.println(c + " " + c.energy);
						if (fightDC || fightCD) 
						{
							//System.out.println("They are fighting");
							if ((c.isDead == false) && (d.isDead == false) && (c.x_coord == d.x_coord) && (c.y_coord == d.y_coord)) 
							{
								//System.out.println("Still fighting");
								int cRoll; 
								int dRoll;

								if (fightDC == true && d.energy > 0) 
								{
									dRoll = getRandomInt(d.energy);
								} 
								else 
								{
									dRoll = 0; 
								}
							
								if (fightCD == true && c.energy > 0) 
								{
									cRoll = getRandomInt(c.energy);
								} 
								else 
								{
									cRoll = 0; 
								}
								//System.out.println("Both rolled");

								if (dRoll >= cRoll) {
									//System.out.println(d + " ate a " + c);
									d.energy += (c.energy / 2);
									c.energy = 0;
									c.isDead = true;
								}	 
								else {	
									//System.out.println(c + " ate a " + d);
									c.energy += (d.energy / 2);
									d.energy = 0;
									d.isDead = true;
								}
							}
						}	
					}
				}	
			}
		}	
	}			

	
	/**
	 * Simulates one step for every critter present. Includes updating energy, 
	 * solving encounters, producing algae, reproducing, walking/running, and killing 
	 * any creatures whose energy reaches 0.
	 */
	public static void worldTimeStep() {
				
		for(int i = 0; i < population.size(); i++) 
		{
			population.get(i).doTimeStep();
			population.get(i).hasMoved = false;
			if(population.get(i).energy <= 0) 
			{
				population.get(i).isDead = true;
				population.remove(i);
			} 
			else 
			{
				population.get(i).isDead = false;				
			}

			population.get(i).energy -= Params.rest_energy_cost;			
		}

		fighting();
			
		//update rest energy cost
		for (int i = 0; i < population.size(); i++) 
		{
			if(population.get(i).energy <= 0) 
			{
				population.get(i).isDead = true;		
				population.remove(i);
			} 
			else 
			{
				population.get(i).isDead = false;
			}
		}
			
		for(int i = 0; i < Params.refresh_algae_count; i++)
		{
			try
			{
				Critter.makeCritter("Algae");
			} 
			catch(InvalidCritterException e) 
			{
				System.out.println(e.toString());
			}
		}
			
			//update babies
		population.addAll(babies);
		babies.clear();
	}
	
	/**
	 * Displays the board and updates the location of each Critter on the board.
	 */
	public static void displayWorld(WorldControl world)
	{
		world.clearGraphics();
		world.drawGraph();
		for(Critter c: population)
		{
			world.shapes(c.x_coord,c.y_coord, c.viewShape(),c.viewFillColor(),c.viewOutlineColor());
		}
	}
}
