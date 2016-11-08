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

/* Critter3 is scared of fighting, and avoids fighting in every situation possible.
 * If its energy is greater than 100, and there is not another Critter in that location,
 * Critter3 reproduces and runs in that direction, otherwise it only walks in that direction.
 * Critter3 is a blue star outlined in green.
 */ 

package assignment5;

public class Critter3 extends Critter{

	@Override
	public String toString(){
		return "3";
	}

	@Override
	public boolean fight(String oponent) {
		return false;	
	}
	
	@Override
	public void doTimeStep() {
	
		for(int i = 0; i < 8; i++){
			if(getEnergy() > 100 && (this.look(i, true) == null)){
	            run(i);
			   	Critter3 child = new Critter3();
		 	    reproduce(child, getRandomInt(7));
			}
			else if (this.look(i, false) == null){ 
				walk(i); 
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


