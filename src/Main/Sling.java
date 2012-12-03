package Main;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;


public class Sling {
	private David david;
	private PlayingArea playingArea;
	public Sling(David david,PlayingArea playingArea){
		this.playingArea = playingArea;
		this.david = david;
		movingLeft = false;
		slingLengthRadius = 80;
		endOfSling = new Location((david.shootingHandLocation.getX() + slingLengthRadius),(david.shootingHandLocation.getY() ));
	}
	
	public Location endOfSling;
	private boolean movingLeft;

	private int slingLengthRadius;
	private int getSlingLengthFromPosition(){
		return (int) Math.hypot( endOfSling.getX()-david.shootingHandLocation.getX() ,(endOfSling.getY()-david.shootingHandLocation.getY()) );
	}
	public void updateAngle(int angle){
		angle = -angle;

		final int slingLength = getSlingLengthFromPosition();
//		System.out.println("angle = " + angle);
//		System.out.println(endOfSling.getX());
//		System.out.println("slingLength= " + slingLength);
//		System.out.println("slingLength*Math.cos(Math.toRadians(angle))=" + slingLength*Math.cos(Math.toRadians(angle)));
//		System.out.println("david.shootingHandLocation.getX()=" + david.shootingHandLocation.getX());
		System.out.println("-0---0-0-0-0-0-0-0-0--------------------------------------- the sling length="  + slingLength*Math.cos(Math.toRadians(angle)));
		if(endOfSling.getX()>=david.shootingHandLocation.getX()){
			endOfSling.setX((int) (slingLength*Math.cos(Math.toRadians(angle))+david.shootingHandLocation.getX()));
			endOfSling.setY((int) (slingLength*Math.sin(Math.toRadians(angle))+david.shootingHandLocation.getY()));			
		}else{
			endOfSling.setX((int) (-slingLength*Math.cos(Math.toRadians(angle))+david.shootingHandLocation.getX()));
			endOfSling.setY((int) (-slingLength*Math.sin(Math.toRadians(angle))+david.shootingHandLocation.getY()));
		}

//		System.out.println(endOfSling.getX());
	} 
	public void drawNextEndOfSlingLocation(int angle,int speed,Graphics g, boolean shootRock){
		angle = -angle;
		Graphics2D g2 = (Graphics2D) g;
		int xMovingIncrementor;
		int yMovingIncrementor;
		//if sling is moving to the left
		if(movingLeft){
//			System.out.println("Angle is " + angle);
//			System.out.println("x = " + speed*Math.cos(Math.toRadians(angle)));
//			System.out.println("y = " + speed*Math.sin(Math.toRadians(angle)));
			//if reached left end
			if(getSlingLengthFromPosition()>=slingLengthRadius){
				movingLeft=false;
				endOfSling.setX((int)(speed*Math.cos(Math.toRadians(angle)))+endOfSling.getX());
				endOfSling.setY((int)(speed*Math.sin(Math.toRadians(angle)))+endOfSling.getY());
				System.out.println("movingLeft = false");
			}else{
			//keep moving to the left
				endOfSling.setX( (int)(-speed*Math.cos(Math.toRadians(angle)))+endOfSling.getX());
				endOfSling.setY( (int)(-speed*Math.sin(Math.toRadians(angle)))+endOfSling.getY());
			}
		}else{
			//if reached right end
			if(getSlingLengthFromPosition()>=slingLengthRadius){
				if(shootRock){
					playingArea.drawLine = false;
					return;
				}
				xMovingIncrementor = (int)(-speed*Math.cos(Math.toRadians(angle)));
				yMovingIncrementor = (int)(-speed*Math.sin(Math.toRadians(angle)));
//				System.out.println("xMovingIncrementor = " + xMovingIncrementor);
//				System.out.println("yMovingIncrementor = " + yMovingIncrementor);
				endOfSling.setX(xMovingIncrementor+endOfSling.getX());
				endOfSling.setY(yMovingIncrementor+endOfSling.getY());
				movingLeft=true;
				System.out.println("movingLeft = true");
			}else{
			//keep moving to the keep moving right
				xMovingIncrementor = (int)(speed*Math.cos(Math.toRadians(angle)));
				yMovingIncrementor = (int)(speed*Math.sin(Math.toRadians(angle)));
				endOfSling.setX(xMovingIncrementor+endOfSling.getX());
				endOfSling.setY(yMovingIncrementor+endOfSling.getY());


			}	
		}
//		System.out.println("xlocation is " + endOfSling.getX());
//		System.out.println("ylocation is " + endOfSling.getY());
		g2.draw(new Line2D.Double(david.shootingHandLocation.getX(), david.shootingHandLocation.getY(), endOfSling.getX(), endOfSling.getY()));
		g2.fill(new Ellipse2D.Double(endOfSling.getX()-rockWidth/2,endOfSling.getY()-rockWidth/2, rockWidth, rockWidth));
	
	}
	private int rockWidth = 10;
}
