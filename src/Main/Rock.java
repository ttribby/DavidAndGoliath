package Main;

import java.awt.Graphics;

public class Rock {
	//Creates a rock traveling at 'angle' and 'speed'.
	public Rock(int angle, int speed){
		location = new Location(100,200);
		this.angle = angle;
		this.speed = speed;
		locationForTesting = new Location(100,200);
		goliathHasBeenHit = false;
	}
	
	//Instantiating the variables for the rock and calculations to follow.
	public double speed;
	public int milliCount=0;
	public int angle;
	public Location location;
	public Location locationForTesting;
	public double timeConstant = .1;
	private boolean goliathHasBeenHit;
	//This function updates the rock's location every millisecond.
	public void updateRockLocation(Graphics g,Location c){
		location = c;
		milliCount++;
		double timeConstant = .1;
		//The following math calculates the velocity of the rock in respective directions over time.  This accounts for acceleration due to gravity.
		double x = Math.cos(Math.toRadians(angle))*speed*milliCount*timeConstant + location.x;
		double y = 550 - (-.5*9.81*Math.pow(milliCount*timeConstant, 2) + Math.sin(Math.toRadians(angle))*speed*milliCount*timeConstant + 550-location.y);
		locationForTesting.setX(x);
		locationForTesting.setY((-.5*9.81*Math.pow(milliCount*timeConstant, 2) + Math.sin(Math.toRadians(angle))*speed*milliCount*timeConstant + location.y));
		if(x<Goliath.headRadius+Goliath.headLocation.x && x> Goliath.headLocation.x && y<Goliath.headRadius + Goliath.headLocation.y && y>Goliath.headLocation.y){
			PlayingArea.hitGoliath = true;
			goliathHasBeenHit = true;
		}else{
			//The rock is made up of several ovals/circles to create a more irregular look.
			if(!goliathHasBeenHit){
				g.fillOval((int)Math.floor(x), (int)Math.floor(y), 10, 10);
				g.fillOval((int)Math.floor(x), (int)Math.floor(y)-1, 10, 10);
				g.fillOval((int)Math.floor(x), (int)Math.floor(y)-2, 10, 10);
				g.fillOval((int)Math.floor(x)-1, (int)Math.floor(y), 10, 10);
				g.fillOval((int)Math.floor(x)-2, (int)Math.floor(y), 10, 10);
			}
		}
	}
}
