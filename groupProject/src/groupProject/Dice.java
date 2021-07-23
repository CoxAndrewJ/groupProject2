package groupProject;

import java.util.Random;

public class Dice
{
	
	Random rand = new Random();
	int sides = 6;
	
	Dice(){
	}

	public int roll() {
		int roll = rand.nextInt(sides) +1;
		return roll;
	}
	


}
