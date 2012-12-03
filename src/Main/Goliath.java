package Main;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

public class Goliath {
	public Goliath(){
		crotchLocationLocation =  new Location(1025,145+jumper);
		leftFootLocationLocation =  new Location(975,190+jumper);
		rightFootLocationLocation =  new Location(1075,190+jumper);
		neckLocation =  new Location(1025,50+jumper);
		neckEndLocation =  new Location(1025,70+jumper);
	
		rHandLocation= new Location(975,75+jumper);
		lHandLocation= new Location(975,70+jumper);
		headRadius = 45;
		headLocation = new Location(1025 - headRadius/2,50-headRadius+jumper);
	}
	private final int  jumper = 200;
	public int headRadius;
	public Location rHandLocation;
	public Location lHandLocation;
	public Location headLocation;
	public Location crotchLocationLocation;
	public Location leftFootLocationLocation;
	public Location rightFootLocationLocation;
	public Location neckLocation;
	public Location neckEndLocation;
	private int armLength = 50 ;
	
		

	
	public void drawGoliath(Graphics g){
		Graphics2D g2 = (Graphics2D) g;
		g2.draw(new Line2D.Double(leftFootLocationLocation.getX(), leftFootLocationLocation.getY(), crotchLocationLocation.getX(), crotchLocationLocation.getY()));
		g2.draw(new Line2D.Double(rightFootLocationLocation.getX(), rightFootLocationLocation.getY(), crotchLocationLocation.getX(), crotchLocationLocation.getY()));
		g2.draw(new Line2D.Double(crotchLocationLocation.getX(), crotchLocationLocation.getY(), neckLocation.getX(), neckLocation.getY()));
		g2.draw(new Ellipse2D.Double(headLocation.getX(), headLocation.getY(), headRadius, headRadius));
		g2.draw(new Line2D.Double(neckEndLocation.getX(), neckEndLocation.getY(), rHandLocation.getX(), rHandLocation.getY()));
		g2.draw(new Line2D.Double(neckEndLocation.getX(), neckEndLocation.getY(), lHandLocation.getX(), lHandLocation.getY()));
		
	}
}
