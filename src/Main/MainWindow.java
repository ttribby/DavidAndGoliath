package Main;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class MainWindow extends JFrame {
public SouthDisplay southDisplay;
public PlayingArea playingArea;
public WinWindow winWindow;
public QuizWindow quizWindow;
public void win(){
	winWindow.setVisible(true);
//	JOptionPane.showMessageDialog(null, "You Win!!", "Winner", JOptionPane.INFORMATION_MESSAGE);
}
	public MainWindow(){
		winWindow = new WinWindow();
		winWindow.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		playingArea = new PlayingArea(this);
		add(playingArea,BorderLayout.CENTER);
		
		JOptionPane.showMessageDialog(null, "Hit Goliath in the head to win!!", "Welcome to 1200 BC", JOptionPane.INFORMATION_MESSAGE);

		setSize(1300,550);
		southDisplay = new SouthDisplay(playingArea);
		JButton quiz = new JButton("Take a Quiz");
		quiz.addActionListener(new QuizDude());
		quizWindow = new QuizWindow();
		quizWindow.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		add(quiz, BorderLayout.WEST);
		add(southDisplay,BorderLayout.SOUTH);
	}

	public static void main(String[] args) {
		MainWindow mainWindow = new MainWindow();
		mainWindow.setVisible(true);
		
	}
	private class QuizDude implements ActionListener{
		public void actionPerformed(ActionEvent e)
		{	
			playingArea.firstRockQuiz = new Rock(0,30);
			playingArea.secondRockQuiz = new Rock(0,5);
			playingArea.firstRockQuiz.timeConstant = 500;
			playingArea.secondRockQuiz.timeConstant = 500;
			playingArea.quizing = true;
			quizWindow.setVisible(true);			
		}
	}
}
