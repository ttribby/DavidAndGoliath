package Main;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.smartcardio.Card;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

//This class is used to pop-up the window declaring your victory.  If you are victorious.
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

	//This closes the window when you click the button.
	private class closePanel implements ActionListener{
		public void actionPerformed(ActionEvent e)
		{
			setVisible(false);
		}
	}
}
