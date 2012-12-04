package Main;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

public class Goliath {
	
	public Goliath(){
		//These are all the attributes of Goliath and where they are drawn.  The same jumper is used for Goliath, as is David, to keep
		//them at the same level and allow easy offsetting of both David and Goliath.
		crotchLocationLocation =  new Location(1025,145+jumper);
		leftFootLocationLocation =  new Location(975,190+jumper);
		rightFootLocationLocation =  new Location(1075,190+jumper);
		neckLocation =  new Location(1025,50+jumper);
		neckEndLocation =  new Location(1025,70+jumper);
		rHandLocation= new Location(975,75+jumper);
		lHandLocation= new Location(975,70+jumper);
	}
	
	//Same as in David; these are 'Locations' of all of Goliath's attributes.
	private final static int  jumper = 200;
	public final static int headRadius = 45;
	public Location rHandLocation;
	public Location lHandLocation;
	//The head location is made accessible for calculating when the rock hits him.
	public static final Location headLocation = new Location(1025 - headRadius/2,50-headRadius+jumper);
	public Location crotchLocationLocation;
	public Location leftFootLocationLocation;
	public Location rightFootLocationLocation;
	public Location neckLocation;
	public Location neckEndLocation;
	private int armLength = 50 ;
	
	public void drawGoliath(Graphics g){
		//This is for drawing Goliath; Graphics2 is used so that we may use doubles as opposed to integers.
		Graphics2D g2 = (Graphics2D) g;
		g2.draw(new Line2D.Double(leftFootLocationLocation.getX(), leftFootLocationLocation.getY(), crotchLocationLocation.getX(), crotchLocationLocation.getY()));
		g2.draw(new Line2D.Double(rightFootLocationLocation.getX(), rightFootLocationLocation.getY(), crotchLocationLocation.getX(), crotchLocationLocation.getY()));
		g2.draw(new Line2D.Double(crotchLocationLocation.getX(), crotchLocationLocation.getY(), neckLocation.getX(), neckLocation.getY()));
		//if goliath has been hit then draw a square on his head else draw a circle
		if(PlayingArea.hitGoliath){
			g2.draw(new Rectangle2D.Double(headLocation.getX(), headLocation.getY(), headRadius, headRadius));			
		}else
			g2.draw(new Ellipse2D.Double(headLocation.getX(), headLocation.getY(), headRadius, headRadius));
		g2.draw(new Line2D.Double(neckEndLocation.getX(), neckEndLocation.getY(), rHandLocation.getX(), rHandLocation.getY()));
		g2.draw(new Line2D.Double(neckEndLocation.getX(), neckEndLocation.getY(), lHandLocation.getX(), lHandLocation.getY()));
		
	}
}
