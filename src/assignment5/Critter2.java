/* CRITTER Critter2.java
 * EE422C Project 4 submission by
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


import javafx.scene.paint.Color;

/*
 * Purely Carnivourous critter that will walk around in a counter-clockwise circle
 * Is not canabalistic
 * Does not eat Algae
 */
public class Critter2 extends Critter {
	int dir;
	/** Constructor
	 */
	public Critter2()
	{
		dir = 0;
	}
	/** Determines if the Critter2 wants to fight the other critter
	 * @param opponent potential prey/predator of Critter2
	 * @return true/false depending on whether or not it wants to eats it
	 */
	public boolean fight(String opponent)
	{
		if(!opponent.equals("@") && !opponent.equals(this.toString()))
		{
			return true;
		}
		return false;
	}
	@Override
	public void doTimeStep()
	{
		String next = look(dir,false);
		if(next.equals("@") || next.equals("@") || next.equals(this.toString()))
		{
			dir += 3;
			dir %= 8;
		}
		walk(dir);
		dir += 7;
		dir %= 8;
	}
	@Override
	public String toString()
	{
		return "2";
	}

	@Override
	public CritterShape viewShape() {
		return CritterShape.CIRCLE;
	}

	@Override
	public Color viewFillColor() {
		return Color.VIOLET;
	}

	@Override
	public Color viewOutlineColor() {
		return Color.GOLD;
	}
}
