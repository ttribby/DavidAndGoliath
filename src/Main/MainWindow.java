package Main;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class MainWindow extends JFrame {
	//Creating all of the areas of the game window.
	public SouthDisplay southDisplay;
	public PlayingArea playingArea;
	public WinWindow winWindow;
	public QuizWindow quizWindow;

	//Setting 'winWindow' to visible.
	public void win(){
		winWindow.setVisible(true);
	}

	public MainWindow(){
		//Creating the visible windows upon start and setting JFrame behaviors. 
		winWindow = new WinWindow();
		winWindow.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		playingArea = new PlayingArea(this);
		add(playingArea,BorderLayout.CENTER);
		//Pop-up game play and winning instructions.
		JOptionPane.showMessageDialog(null, "Hit Goliath in the head to win!!", "Welcome to 1200 BC", JOptionPane.INFORMATION_MESSAGE);
		setSize(1300,550);
		//This is for displaying angle and power.
		southDisplay = new SouthDisplay(playingArea);
		//Creating the quiz button.
		JButton quiz = new JButton("Take a Quiz");
		quiz.addActionListener(new QuizDude());
		//Instantiate the quiz and set its visibility and behavior. 
		quizWindow = new QuizWindow(playingArea);
		quizWindow.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		add(quiz, BorderLayout.WEST);
		add(southDisplay,BorderLayout.SOUTH);
	}

	//Just your everyday 'main' function.
	public static void main(String[] args) {
		MainWindow mainWindow = new MainWindow();
		mainWindow.setVisible(true);
	}

	//Add an ActionListener for the quiz window.
	private class QuizDude implements ActionListener{
		public void actionPerformed(ActionEvent e)
		{	
			//This demonstrates the answer to the quiz.
			playingArea.firstRockQuiz = new Rock(0,100);
			playingArea.secondRockQuiz = new Rock(0,50);
			playingArea.firstRockQuiz.timeConstant = 500;
			playingArea.secondRockQuiz.timeConstant = 500;
			quizWindow.setVisible(true);			
			quizWindow.setVisible(true);
		}
	}
}
