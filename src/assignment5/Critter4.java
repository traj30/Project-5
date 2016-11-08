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

/* Critter4 partakes in fighting only if its energy is greater than 80. It walks in 
 * a random direction, and if its energy is greater than 120, it reproduces. The 
 * offspring is spawned at a location adjacent to the parent at a location above the parent.
 */ 

package assignment5;


public class Critter4 extends Critter{

	@Override
	public String toString(){
		return "4";
	}

	@Override
	public boolean fight(String oponent) {
		if (getEnergy() > 80) {
			return true;
		}
		else { return false; }
	}
	
	@Override
	public void doTimeStep() {
		walk(Critter.getRandomInt(7));
		
		if (getEnergy() > 120) {
			Critter3 child = new Critter3();
			reproduce(child, getRandomInt(4));	
		}
	
	}
	
	
	@Override
	public CritterShape viewShape() {
		return CritterShape.CIRCLE;
	}
	
	public javafx.scene.paint.Color viewOutlineColor() { 
		return javafx.scene.paint.Color.BLACK; 
	}
	
	public javafx.scene.paint.Color viewFillColor() { 
		return javafx.scene.paint.Color.RED; 
	}	
	
	
}


