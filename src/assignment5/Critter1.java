/* CRITTER Critter1.java
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

package assignment4;

/** This Critter is only eats algae and will attempt to run if fighting occurs
 * It will only move if either faced with conflict or it gets hungry enough
 * It walks around in a clockwise circle
 */
public class Critter1 extends Critter {
	int dir;
	/** Constructor
	 *
	 */
	public Critter1()
	{
		dir = 0;
	}
	
	/** Determines whether or not the critter1 wants to fight the oppponent
	 *	@param opponent the potential victim/victimizer of the Critter1
	 *	@return boolean value, true for wants to fight, false for otherwise
	 *
	 */
	public boolean fight(String opponent)
	{
		if(opponent.equals("@"))
		{
			return true;
		}
		run(dir);
		dir += 1;
		dir %= 8;

		return false;
	}
	@Override
	public void doTimeStep()
	{
		if(getEnergy() < 100)
		{
			walk(dir);
			dir += 1;
			dir %= 8;
		}
	}
	@Override
	public String toString()
	{
		return "1";
	}


}
