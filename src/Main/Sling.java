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
		if(endOfSling.getX()>=david.shootingHandLocation.getX()){
			endOfSling.setX((int) (slingLength*Math.cos(Math.toRadians(angle))+david.shootingHandLocation.getX()));
			endOfSling.setY((int) (slingLength*Math.sin(Math.toRadians(angle))+david.shootingHandLocation.getY()));			
		}else{
			endOfSling.setX((int) (-slingLength*Math.cos(Math.toRadians(angle))+david.shootingHandLocation.getX()));
			endOfSling.setY((int) (-slingLength*Math.sin(Math.toRadians(angle))+david.shootingHandLocation.getY()));
		}
	} 
	public void drawNextEndOfSlingLocation(int angle,int speed,Graphics g, boolean shootRock){
		angle = -angle;
		Graphics2D g2 = (Graphics2D) g;
		int xMovingIncrementor;
		int yMovingIncrementor;
		//if sling is moving to the left
		if(movingLeft){
			//if reached left end
			if(getSlingLengthFromPosition()>=slingLengthRadius){
				movingLeft=false;
				endOfSling.setX((int)(speed*Math.cos(Math.toRadians(angle)))+endOfSling.getX());
				endOfSling.setY((int)(speed*Math.sin(Math.toRadians(angle)))+endOfSling.getY());
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
				endOfSling.setX(xMovingIncrementor+endOfSling.getX());
				endOfSling.setY(yMovingIncrementor+endOfSling.getY());
				movingLeft=true;
			}else{
				//keep moving to the keep moving right
				xMovingIncrementor = (int)(speed*Math.cos(Math.toRadians(angle)));
				yMovingIncrementor = (int)(speed*Math.sin(Math.toRadians(angle)));
				endOfSling.setX(xMovingIncrementor+endOfSling.getX());
				endOfSling.setY(yMovingIncrementor+endOfSling.getY());
			}	
		}

		g2.draw(new Line2D.Double(david.shootingHandLocation.getX(), david.shootingHandLocation.getY(), endOfSling.getX(), endOfSling.getY()));
		g2.fill(new Ellipse2D.Double(endOfSling.getX()-rockWidth/2,endOfSling.getY()-rockWidth/2, rockWidth, rockWidth));
	}
	private int rockWidth = 10;
}
