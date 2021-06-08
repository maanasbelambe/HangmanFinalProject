import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class HangmanGUI extends JFrame implements ActionListener
{
	private static final int WIDTH = 900;
	private static final int HEIGHT = 600;
	private Hangman game;
	private ArrayList<JButton> letterButtons;
	private JLabel categoryLabel;
	private JLabel wordLabel;
	private JLabel scoreLabel;
	private JButton newGameButton;
	private JButton quitButton;
	private HangmanDisplay hd;
	private JLabel winSign;
	private JLabel loseSign;
	
	public HangmanGUI() throws FileNotFoundException
	{
		setSize(WIDTH, HEIGHT);
		setLocation(200, 50);
		setTitle("Hangman");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		game = new Hangman("words.txt");
		
		int index = 0;
		letterButtons = new ArrayList<JButton>();
		for(char letter = 'A'; letter <= 'Z'; letter++)
		{
			letterButtons.add(new JButton("" + letter));
			letterButtons.get(index).addActionListener(this);
			index++;
		}
		
		newGameButton = new JButton("New Game");
		newGameButton.addActionListener(this);
		
		quitButton = new JButton("Quit");
		quitButton.addActionListener(this);
		
		categoryLabel = new JLabel(game.getCurrentCategory().getCategoryName(), JLabel.CENTER);
		categoryLabel.setFont(new Font("TIMES", Font.BOLD, 20));
		
		wordLabel = new JLabel(game.getHiddenWord(), JLabel.CENTER);
		wordLabel.setFont(new Font("TIMES", Font.BOLD, 20));
		
		scoreLabel = new JLabel("Total Score: " + game.getScore(), JLabel.CENTER);
		scoreLabel.setFont(new Font("TIMES", Font.BOLD, 20));
		
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		
		JPanel center = new JPanel();
		hd = new HangmanDisplay();
		center.add(hd.getDisplay());
		
		JPanel north = new JPanel();
		JPanel n1 = new JPanel();
		n1.setLayout(new GridLayout(1, 4));
		n1.add(categoryLabel); n1.add(scoreLabel); n1.add(newGameButton); n1.add(quitButton);
		north.setLayout(new GridLayout(2, 1));
		north.add(n1); north.add(wordLabel);
		
		JPanel south = new JPanel();
		south.setLayout(new GridLayout(4, 7));
		for(int i = 0; i < 26; i++)
			south.add(letterButtons.get(i));
		
		c.add(north, BorderLayout.NORTH);
		c.add(center, BorderLayout.CENTER);
		c.add(south, BorderLayout.SOUTH);
		
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		Object o = e.getSource();
		for(JButton jb : letterButtons)
		{
			if(o == jb)
			{
				String letterGuess = jb.getText();
				jb.setEnabled(false);
				boolean isCorrectLetter = game.isCorrectLetter(letterGuess);
				if(isCorrectLetter)
				{
					wordLabel.setText(game.getHiddenWord());
					if(game.isGameWon())
					{
						hd.gameOver(true);
						wordLabel.setText(game.getCurrentWord());
						scoreLabel.setText("Total Score: " + game.getScore());
						disableAllButtons();
					}
				}
				else
				{
					hd.addBodyParts();
					if(game.isGameLost())
					{
						hd.gameOver(false);
						wordLabel.setText(game.getCurrentWord());
						disableAllButtons();
					}
				}
			}
		}
		if(o == newGameButton)
		{
			game.reset();
			categoryLabel.setText(game.getCurrentCategory().getCategoryName());
			wordLabel.setText(game.getHiddenWord());
			hd.reset();
			enableAllButtons();
		}
		if(o == quitButton)
		{
			System.exit(0);
		}
	}
	
	private void disableAllButtons()
	{
		for(JButton jb : letterButtons)
			jb.setEnabled(false);
	}
	
	private void enableAllButtons()
	{
		for(JButton jb : letterButtons)
			jb.setEnabled(true);
	}
	
	public static void main(String[] args) throws FileNotFoundException
	{
		HangmanGUI hg = new HangmanGUI();
	}
}