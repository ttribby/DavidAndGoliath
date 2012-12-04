package Main;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Line2D;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;


public class PlayingArea extends JPanel{
	public MainWindow mainWindow;
	public David david;
	private Goliath goliath;
	public Rock rock;
	public Rock firstRockQuiz;
	public Rock secondRockQuiz;
	public Sling sling;
	public Graphics theGraphics;

	public PlayingArea(MainWindow mainWindow){
		this.mainWindow = mainWindow;
		CellClickedListener cellClickedListener = new CellClickedListener();
		addMouseListener(cellClickedListener);
		addMouseMotionListener(cellClickedListener);
		repaint();
		david = new David();
		goliath = new Goliath();
		rock = new Rock(0,0);
		sling = new Sling(david,this);
		Timer oneMilliSecTimer = new Timer();
		mouseXCoord = 0;
		mouseYCoord = 0;
		distanceDragged = 0;
		oneMilliSecTimer.schedule(new UpdateDrawing(),0,15);
		theGraphics = this.getGraphics();
	}

	private class UpdateDrawing extends TimerTask {
		public void run() {
			repaint();
		}
	}

	private Location mouseClickLocation;
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		super.paintComponent(g);
		david.drawDavid(g);
		goliath.drawGoliath(g);

		//show more quiz info
		if(showMoreQuizInfo){
			firstRockQuiz.milliCount--;
			secondRockQuiz.milliCount--;
			firstRockQuiz.updateRockLocation(g,sling.endOfSling);
			secondRockQuiz.updateRockLocation(g,sling.endOfSling);
			g.drawString("see both land at the same time", (int) Math.floor(secondRockQuiz.locationForTesting.x), (int) Math.floor(500 - secondRockQuiz.locationForTesting.y - 50));
		}
		
		//draw line
		if(drawLine){
			//use graphics2 because it can take doubles as inputs
			g2.draw(new Line2D.Double(lineStart.getX(), lineStart.getY(), lineEnd.getX(), lineEnd.getY()));
			if ((lineEnd.getX()-lineStart.getX())   == 0)
				mainWindow.southDisplay.angleResult.setText("0");
			else{
				mainWindow.southDisplay.angleResult.setText(angle+ "deg");

			}
			mainWindow.southDisplay.powerResult.setText(distanceDragged/2 + "%");
			sling.drawNextEndOfSlingLocation(angle, /*5*/distanceDragged/50+3, g,shootSling);
			//draw rock
		}else{
			if(quizing){


				firstRockQuiz.updateRockLocation(g,sling.endOfSling);
				secondRockQuiz.updateRockLocation(g,sling.endOfSling);
//				System.out.println("location = " + firstRockQuiz.locationForTesting.y);
				if(firstRockQuiz.locationForTesting.y<80) {
					quizing = false;
					showMoreQuizInfo = true;					
				}

			}else{
				if(!hitGoliath){
					rock.updateRockLocation(g,sling.endOfSling);
				}else{
					hitGoliath = false;
					mainWindow.win();
				}
			}
		}

	}//////////////////}//////////////////}//////////////////}//////////////////}//////////////////}//////////////////}//////////////////
	public boolean showMoreQuizInfo = false;

	
	






	public static boolean hitGoliath;
	public boolean quizing;
	//shooting angle from 0 to 360
	private boolean shootSling = false;
	private int shootingAngle;
	public int angle = 0;
	private int mouseXCoord;
	private int mouseYCoord;
	public int distanceDragged;
	public boolean drawLine = false;
	public Location lineStart = new Location(0,0);
	public Location lineEnd = new Location(0,0);

	private class CellClickedListener implements MouseListener, MouseMotionListener{
		private int x,y;
		@Override
		public void mouseClicked(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

		@Override
		public void mousePressed(MouseEvent e) {

			// TODO Auto-generated method stub
			showMoreQuizInfo = false;

			shootSling = false;
			mouseXCoord = e.getX();
			mouseYCoord = e.getY();
			angle = 0;
			distanceDragged = 0;
			drawLine = true;
			lineStart.setX(e.getX());
			lineStart.setY(e.getY());
			lineEnd.setX(e.getX());
			lineEnd.setY(e.getY());
			rock = new Rock(angle,distanceDragged);
			repaint();
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			shootSling = true;
			rock.angle = angle;
			rock.speed = distanceDragged/5;
			repaint();
		}

		public void mouseDragged(MouseEvent e) {
			distanceDragged = (int) (Math.abs(Math.sqrt((Math.pow(mouseXCoord-e.getX(), 2) + Math.pow(( mouseYCoord)-e.getY(),2)))));
			setAngle();
			lineEnd.setX(e.getX());
			lineEnd.setY(e.getY());
			angle = (int) Math.floor((Math.atan2( (lineEnd.getY()-lineStart.getY()),(lineStart.getX()-lineEnd.getX())  )*180/Math.PI) );
			sling.updateAngle(angle);
			repaint();
		}

		@Override
		public void mouseMoved(MouseEvent arg0) {

		} 
	}
	
	public void setAngle(){
		angle = (int) Math.floor((Math.atan2( (lineEnd.getY()-lineStart.getY()),(lineStart.getX()-lineEnd.getX())  )*180/Math.PI) );
	}
	
}
