import javax.swing.*;

public class HangmanDisplay
{
	private JLabel display;
	private int numBodyParts;
		
	public HangmanDisplay()
	{
		numBodyParts = 0;
		display = new JLabel(new ImageIcon(getClass().getResource("hangman_0.png")));
	}
	
	public JLabel getDisplay()
	{
		return display;
	}
	
	public void addBodyParts()
	{
		numBodyParts++;
		if(numBodyParts < 6)
			display.setIcon(new ImageIcon(getClass().getResource("hangman_" + numBodyParts + ".png")));
	}
	
	public void reset()
	{
		numBodyParts = 0;
		display.setIcon(new ImageIcon(getClass().getResource("hangman_0.png")));
	}
	
	public void gameOver(boolean b)
	{
		if(b)
			display.setIcon(new ImageIcon(getClass().getResource("hangman_win.png")));
		else
			display.setIcon(new ImageIcon(getClass().getResource("hangman_loss.png")));
	}
}
