package Main;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class QuizWindow extends JFrame {
	public QuizWindow(){

		JLabel question = new JLabel("<html>     Two rocks fly in the air at 0 degrees.  <br>    The first one is launched at 50% power and the second is launched at 100% power. <br> Which will hit the ground first? </html>      ");
		JButton hundred = new JButton("100%");
		JButton fifty = new JButton("50%");
		question.setBorder(new TitledBorder(new EtchedBorder(), "Quizzer"));
		hundred.addActionListener(new HundredListener());
		fifty.addActionListener(new FiftyListener());
		add(question, BorderLayout.NORTH);
		add(hundred,BorderLayout.SOUTH);
		add(fifty,BorderLayout.CENTER);
		setSize(800,200);
		this.pack();
	}

	private class HundredListener implements ActionListener{
		public void actionPerformed(ActionEvent e)
		{	
			JOptionPane.showMessageDialog(null, "Your wrong they will both hit the ground at the same time", "Wrong", JOptionPane.QUESTION_MESSAGE);
			setVisible(false);
		}
	}

	private class FiftyListener implements ActionListener{
		public void actionPerformed(ActionEvent e)
		{	
			JOptionPane.showMessageDialog(null, "Your wrong they will both hit the ground at the same time", "Wrong", JOptionPane.QUESTION_MESSAGE);
			setVisible(false);		
		}
	}
}
