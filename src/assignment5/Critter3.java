/* CRITTER Critter3.java
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
		return true;	
	}
	
	@Override
	public void doTimeStep() {
		if(getEnergy() > 100){
            run(2);
		   	Critter3 child = new Critter3();
	 	    reproduce(child, getRandomInt(7));		
		}
		else{ walk(2); } 
	}
}


