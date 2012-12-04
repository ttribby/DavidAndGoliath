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

//Instantiates the class for the game play area.
public class PlayingArea extends JPanel{
	//Declare all of the elements in the game playing area.
	public MainWindow mainWindow;
	public David david;
	private Goliath goliath;
	public Rock rock;
	public Rock firstRockQuiz;
	public Rock secondRockQuiz;
	public Sling sling;
	public Graphics theGraphics;

	//Main game play window.
	public PlayingArea(MainWindow mainWindow){
		this.mainWindow = mainWindow;
		//Create listeners for mouse location and clicking/dragging.
		CellClickedListener cellClickedListener = new CellClickedListener();
		addMouseListener(cellClickedListener);
		addMouseMotionListener(cellClickedListener);
		//Repaint the game area.
		repaint();
		//Create the elements of the game play area.
		david = new David();
		goliath = new Goliath();
		rock = new Rock(0,0);
		sling = new Sling(david,this);
		//Create a timer that operates in milliseconds for refreshing
		Timer oneMilliSecTimer = new Timer();
		//Initialize variables for the mouse attributes.
		mouseXCoord = 0;
		mouseYCoord = 0;
		distanceDragged = 0;
		//This redraws the game area every 15 milliseconds to account for swinging/flinging/throwing.
		oneMilliSecTimer.schedule(new UpdateDrawing(),0,15);
		theGraphics = this.getGraphics();
	}

	//This function handles the redrawing when it is called.
	private class UpdateDrawing extends TimerTask {
		public void run() {
			repaint();
		}
	}

	private Location mouseClickLocation;

	@Override
	protected void paintComponent(Graphics g) {
		//Again, Graphics2 is used so that we may pass it doubles.
		Graphics2D g2 = (Graphics2D) g;
		//Draw David and Goliath.
		super.paintComponent(g);
		david.drawDavid(g);
		goliath.drawGoliath(g);

		//This does the visual demo for the quiz answer.
		if(showMoreQuizInfo){
			firstRockQuiz.milliCount--;
			secondRockQuiz.milliCount--;
			firstRockQuiz.updateRockLocation(g,sling.endOfSling);
			secondRockQuiz.updateRockLocation(g,sling.endOfSling);
			g.drawString("Both rocks will land at the same time explained by Newton's Second Law", (int) Math.floor(secondRockQuiz.locationForTesting.x), (int) Math.floor(500 - secondRockQuiz.locationForTesting.y - 50));
		}

		//This function draws the line to represent the power and angle.
		if(drawLine){
			g2.draw(new Line2D.Double(lineStart.getX(), lineStart.getY(), lineEnd.getX(), lineEnd.getY()));
			if ((lineEnd.getX()-lineStart.getX())   == 0)
				mainWindow.southDisplay.angleResult.setText("0");
			else{
				mainWindow.southDisplay.angleResult.setText(angle+ "¡");
			}
			mainWindow.southDisplay.powerResult.setText(distanceDragged/2 + "%");
			sling.drawNextEndOfSlingLocation(angle, distanceDragged/50+3, g,shootSling);
			//This part will draw rock(s).
		}else{
			if(quizing){
				sling.updateAngle(0);
				firstRockQuiz.updateRockLocation(g,sling.endOfSling);
				secondRockQuiz.updateRockLocation(g,sling.endOfSling);
				if(firstRockQuiz.locationForTesting.y<120) {
					quizing = false;
					showMoreQuizInfo = true;					
				}
			}else{
				//If you have not hit Goliath, the rocks location will continue to be updated.
				if(!hitGoliath){
					rock.updateRockLocation(g,sling.endOfSling);
				}else{
					hitGoliath = false;
					mainWindow.win();
				}
			}
		}
	}
	
	public boolean showMoreQuizInfo = false;
	public static boolean hitGoliath;
	public boolean quizing;
	private boolean shootSling = false;
	//Shooting angle form 0 to 360.
	private int shootingAngle;
	//Initialize the angle.
	public int angle = 0;
	private int mouseXCoord;
	private int mouseYCoord;
	public int distanceDragged;
	public boolean drawLine = false;
	public Location lineStart = new Location(0,0);
	public Location lineEnd = new Location(0,0);

	//These next lines are for listening to the mouse and reading the 'dragging' and 'clicking'.
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
		//This function gets all the information form the mouse and tells the line how to be drawn.
		public void mousePressed(MouseEvent e) {
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
			//Create a new rock to be thrown
			rock = new Rock(angle,distanceDragged);
			repaint();
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			//Throw the rock upon mouse release.
			shootSling = true;
			rock.angle = angle;
			rock.speed = distanceDragged/5;
			repaint();
		}

		//This function is used to calculate the length of the line dragged and updates the game play area.
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

	//This function calculates the angle of the line dragged.
	public void setAngle(){
		angle = (int) Math.floor((Math.atan2( (lineEnd.getY()-lineStart.getY()),(lineStart.getX()-lineEnd.getX())  )*180/Math.PI) );
	}
}
