package Main;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

//This class handles David's rock slinging.
public class Sling {
	//The next 9 lines create the needed variables and the 'sling' between David's hand and the rock.
	private David david;
	private PlayingArea playingArea;
	public Sling(David david,PlayingArea playingArea){
		this.playingArea = playingArea;
		this.david = david;
		movingLeft = false;
		slingLengthRadius = 80;
		endOfSling = new Location((david.shootingHandLocation.getX() + slingLengthRadius),(david.shootingHandLocation.getY() ));
	}
	//Calculating the sling length given position of David's hand/
	public Location endOfSling;
	private boolean movingLeft;
	private int slingLengthRadius;
	private int getSlingLengthFromPosition(){
		return (int) Math.hypot( endOfSling.getX()-david.shootingHandLocation.getX() ,(endOfSling.getY()-david.shootingHandLocation.getY()) );
	}
	//This function is called to update the slings angle.
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
	//This function is called when the mouse moves to update the direction and speed in which David's sling is moving.
	public void drawNextEndOfSlingLocation(int angle,int speed,Graphics g, boolean shootRock){
		angle = -angle;
		Graphics2D g2 = (Graphics2D) g;
		int xMovingIncrementor;
		int yMovingIncrementor;
		//If the sling is moving to the left...
		if(movingLeft){
			//If the sling is at maximum left....
			if(getSlingLengthFromPosition()>=slingLengthRadius){
				movingLeft=false;
				endOfSling.setX((int)(speed*Math.cos(Math.toRadians(angle)))+endOfSling.getX());
				endOfSling.setY((int)(speed*Math.sin(Math.toRadians(angle)))+endOfSling.getY());
			}else{
				//If not, continue moving left.
				endOfSling.setX( (int)(-speed*Math.cos(Math.toRadians(angle)))+endOfSling.getX());
				endOfSling.setY( (int)(-speed*Math.sin(Math.toRadians(angle)))+endOfSling.getY());
			}
		}else{
			//If the sling is at the maximum right...
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
				//If not, continue moving ot the right.
				xMovingIncrementor = (int)(speed*Math.cos(Math.toRadians(angle)));
				yMovingIncrementor = (int)(speed*Math.sin(Math.toRadians(angle)));
				endOfSling.setX(xMovingIncrementor+endOfSling.getX());
				endOfSling.setY(yMovingIncrementor+endOfSling.getY());
			}	
		}
		//Using Graphics2 again to draw the sling and rock.
		g2.draw(new Line2D.Double(david.shootingHandLocation.getX(), david.shootingHandLocation.getY(), endOfSling.getX(), endOfSling.getY()));
		g2.fill(new Ellipse2D.Double(endOfSling.getX()-rockWidth/2,endOfSling.getY()-rockWidth/2, rockWidth, rockWidth));
	}
	//This is how wide the rock is.
	private int rockWidth = 10;
}
