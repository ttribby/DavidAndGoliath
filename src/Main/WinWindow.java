package Main;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.smartcardio.Card;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class WinWindow extends JFrame {
	public WinWindow(){
		setSize(300,300);
		JLabel wonInfo = new JLabel("You Win!!");
		wonInfo.setFont(new Font("Serif", Font.PLAIN, 35));
		JButton ok = new JButton("Okay! I am awesome at playing this game");
		ok.addActionListener(new closePanel());
		add(ok, BorderLayout.SOUTH);
		add(wonInfo,BorderLayout.CENTER);
	}

	private class closePanel implements ActionListener{
		public void actionPerformed(ActionEvent e)
		{
			setVisible(false);
		}
	}
}
