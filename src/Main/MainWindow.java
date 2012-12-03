package Main;
import java.awt.BorderLayout;

import javax.swing.JFrame;


public class MainWindow extends JFrame {
public SouthDisplay southDisplay;
public PlayingArea playingArea;
	public MainWindow(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		playingArea = new PlayingArea(this);
		add(playingArea,BorderLayout.CENTER);
		setSize(1300,550);
		southDisplay = new SouthDisplay(playingArea);
		add(southDisplay,BorderLayout.SOUTH);
	}
	public static void main(String[] args) {
		MainWindow mainWindow = new MainWindow();
		mainWindow.setVisible(true);
		
	}
}
