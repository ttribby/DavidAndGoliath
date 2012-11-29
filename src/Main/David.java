package Main;
//raaaaaa
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

public class David {
	public David(){
		crotchLocationLocation =  new Location(125,175+jumper);
		leftFootLocationLocation =  new Location(100,200+jumper);
		rightFootLocationLocation =  new Location(150,200+jumper);
		neckLocation =  new Location(125,125+jumper);
		neckEndLocation =  new Location(125,135+jumper);
		shootingElboLocation = new Location(150,125+jumper);
		shootingHandLocation= new Location(150,90+jumper);
		nonShootingHandLocation= new Location(100,130+jumper);
		headRadius = 15;
		headLocation = new Location(125 - headRadius/2,125-headRadius+jumper);
	}
	private final int  jumper = 200;
	public int headRadius;
	public Location nonShootingHandLocation;
	public Location shootingHandLocation;
	public Location headLocation;
	public Location shootingElboLocation;
	public Location crotchLocationLocation;
	public Location leftFootLocationLocation;
	public Location rightFootLocationLocation;
	public Location neckLocation;
	public Location neckEndLocation;
	private int armLength = 20;
	
		
	public Location getHandShootingLocation(int angle){

		int y=(int) (shootingHandLocation.getY() - armLength*Math.acos(Math.toRadians(angle)));
		int x=(int) (shootingHandLocation.getX() - armLength*Math.asin(Math.toRadians(angle)));
//		System.out.println("angle= " + angle);
//		System.out.println("armLength*Math.acos(angle)=" + armLength*Math.acos(Math.toRadians(angle)));
//		System.out.println("armLength*Math.acos(angle)=" + armLength*Math.acos(Math.toRadians(angle)));
//		System.out.println();
//	System.out.println("x= " + x + " y= " + y);
		return new Location(x,y);
	}
	
	public void drawDavid(Graphics g){
		Graphics2D g2 = (Graphics2D) g;
		g2.draw(new Line2D.Double(leftFootLocationLocation.getX(), leftFootLocationLocation.getY(), crotchLocationLocation.getX(), crotchLocationLocation.getY()));
		g2.draw(new Line2D.Double(rightFootLocationLocation.getX(), rightFootLocationLocation.getY(), crotchLocationLocation.getX(), crotchLocationLocation.getY()));
		g2.draw(new Line2D.Double(crotchLocationLocation.getX(), crotchLocationLocation.getY(), neckLocation.getX(), neckLocation.getY()));
		g2.draw(new Ellipse2D.Double(headLocation.getX(), headLocation.getY(), headRadius, headRadius));
		g2.draw(new Line2D.Double(neckEndLocation.getX(), neckEndLocation.getY(), nonShootingHandLocation.getX(), nonShootingHandLocation.getY()));
		g2.draw(new Line2D.Double(neckEndLocation.getX(), neckEndLocation.getY(), shootingElboLocation.getX(), shootingElboLocation.getY()));
		g2.draw(new Line2D.Double(shootingElboLocation.getX(), shootingElboLocation.getY(), shootingHandLocation.getX(), shootingHandLocation.getY()));
	}
}
