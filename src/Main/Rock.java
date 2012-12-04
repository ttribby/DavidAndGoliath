package Main;

import java.awt.Graphics;

public class Rock {
	public Rock(int angle, int speed){

		location = new Location(100,200);
		this.angle = angle;
		this.speed = speed;//20-120
		locationForTesting = new Location(100,200);
	}
	public double speed;

	public int milliCount=0;
	public int angle;
	
	public Location location;
	public Location locationForTesting;
	//increment 1 ms
	public void updateRockLocation(Graphics g,Location c){

		location = c;
//		System.out.println("the speed is ========" + speed);
		//decrease y speed from gravity
		milliCount++;
		double timeConstant = .1;
//		System.out.println("cos =  " + Math.cos(Math.toRadians(angle)));
//		System.out.println("sin = " + Math.sin(Math.toRadians(angle)));
		double x = Math.cos(Math.toRadians(angle))*speed*milliCount*timeConstant + location.x;
		double y = 550 - (-.5*9.81*Math.pow(milliCount*timeConstant, 2) + Math.sin(Math.toRadians(angle))*speed*milliCount*timeConstant + 550-location.y);
		locationForTesting.setX(x);
		locationForTesting.setY((-.5*9.81*Math.pow(milliCount*timeConstant, 2) + Math.sin(Math.toRadians(angle))*speed*milliCount*timeConstant + location.y));
		//print stuff
//		System.out.println("///////printing x=" + Math.floor(x) + "y=" + Math.floor(y));
		//check if rock has hit Goliath Rock
		if(x<Goliath.headRadius+Goliath.headLocation.x && x> Goliath.headLocation.x && y<Goliath.headRadius + Goliath.headLocation.y && y>Goliath.headLocation.y){
			System.out.println("------------------------hit him");
			PlayingArea.hitGoliath = true;
		}else{
		g.fillOval((int)Math.floor(x), (int)Math.floor(y), 10, 10);
		g.fillOval((int)Math.floor(x), (int)Math.floor(y)-1, 10, 10);
		g.fillOval((int)Math.floor(x), (int)Math.floor(y)-2, 10, 10);
		g.fillOval((int)Math.floor(x)-1, (int)Math.floor(y), 10, 10);
		g.fillOval((int)Math.floor(x)-2, (int)Math.floor(y), 10, 10);
		}
	}
}
