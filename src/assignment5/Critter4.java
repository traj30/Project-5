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

/* Critter4 is a reasonable fighter. It partakes in fighting only if its energy is greater than 80,
 * and if it is not surrounded by another Critter. It walks in a random direction, and if its 
 * energy is greater than 120, it reproduces. The offspring is spawned at a location adjacent to 
 * the parent at a location above the parent. Critter3 is a red circle outlined in black.
 */ 

package assignment5;

public class Critter4 extends Critter{

	@Override
	public String toString(){
		return "4";
	}

	@Override
	public boolean fight(String oponent) {
		int count = 0;
		for(int i = 0; i < 8; i++){
			if (this.look(i, false) != null) {
				count++;
			}
		}
		
		if (getEnergy() > 80 && count == 0){
			return true;
		}
		else{
			return false;
		}
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
		return CritterShape.TRIANGLE;
	}
	
	public javafx.scene.paint.Color viewOutlineColor() { 
		return javafx.scene.paint.Color.BLACK; 
	}
	
	public javafx.scene.paint.Color viewFillColor() { 
		return javafx.scene.paint.Color.RED; 
	}	
	
	
}


