/* CRITTERS GUI Critter3.java
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

//Made by Tiraj Parikh

/* Critter3  partakes in fighting, and moves only upward.
 * If its energy is greater than 100, Critter3 reproduces 
 * and runs upwards, otherwise it only walks upwards.
 */ 

package assignment5;

public class Critter3 extends Critter{

	@Override
	public String toString(){
		return "3";
	}

	@Override
	public boolean fight(String oponent) {
		return false;	//never fights
	}
	
	@Override
	public void doTimeStep() {
	
		for(int i = 0; i < 8; i++){
			if(getEnergy() > 100 && (this.look(i, true) == null)){
	            run(2);
			   	Critter3 child = new Critter3();
		 	    reproduce(child, getRandomInt(7));
			}
			else if (this.look(i, false) == null){ 
				walk(2); 
			} 
			
		}
	
	}
	
	@Override
	public CritterShape viewShape() {
		return CritterShape.STAR;
	}
	
	public javafx.scene.paint.Color viewOutlineColor() { 
		return javafx.scene.paint.Color.GREEN; 
	}
	
	public javafx.scene.paint.Color viewFillColor() { 
		return javafx.scene.paint.Color.BLUE; 
	}	

	
}


