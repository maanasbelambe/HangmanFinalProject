import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Hangman
{
	private Category[] categories;
	private String currentWord;
	private Category currentCategory;
	private String hiddenWord;
	private int score;
	private int wrongGuessRem;
	
	public Hangman(String fileName) throws FileNotFoundException
	{
		categories = initCategories(fileName);
		currentCategory = categories[(int) (Math.random() * categories.length)];
		currentWord = currentCategory.getRandomWord();
		hiddenWord = "";
		for(int i = 0; i < currentWord.length() - 1; i++)
			hiddenWord += "_ ";
		hiddenWord += "_";
		score = 0;
		wrongGuessRem = 6;
	}
	
	private Category[] initCategories(String fileName) throws FileNotFoundException
	{
		Scanner fileScan = new Scanner(new File(fileName));
		ArrayList<String> temp = new ArrayList<String>();
		while(fileScan.hasNextLine())
			temp.add(fileScan.nextLine());
		int tempSize = temp.size();
		Category[] output = new Category[tempSize / 2];
		for(int i = 0; i < output.length; i++)
		{
			output[i] = new Category(temp.get(i * 2), fileName);
		}
		return output;
	}
	
	public String getCurrentWord()
	{
		return currentWord;
	}
	
	private void setCurrentWord()
	{
		currentWord = currentCategory.getRandomWord();
	}
	
	public Category getCurrentCategory()
	{
		return currentCategory;
	}
	
	private void setCurrentCategory()
	{
		currentCategory = categories[(int) (Math.random() * categories.length)];
	}
	
	public String getHiddenWord()
	{
		return hiddenWord;
	}
	
	private void setHiddenWord()
	{
		hiddenWord = "";
		for(int i = 0; i < currentWord.length() - 1; i++)
			hiddenWord += "_ ";
		hiddenWord += "_";
	}
	
	public int getScore()
	{
		return score;
	}
	
	private void changeScore()
	{
		score += currentWord.length() * 10;
	}
	
	public boolean isCorrectLetter(String letter)
	{
		boolean output = false;
		String[] tempReal = currentWord.split("");
		String[] tempHidden = hiddenWord.split(" ");
		for(int i = 0; i < tempReal.length; i++)
		{
			if(tempReal[i].equalsIgnoreCase(letter))
			{
				tempHidden[i] = letter;
				output = true;
			}
		}
		if(output)
		{
			hiddenWord = "";
			for(int i = 0; i < tempHidden.length - 1; i++)
				hiddenWord += tempHidden[i] + " ";
			hiddenWord += tempHidden[tempHidden.length - 1];
		}
		else
			wrongGuessRem--;
		return output;
	}
	
	public boolean isGameLost()
	{
		return wrongGuessRem <= 0;
	}
	
	public boolean isGameWon()
	{
		boolean output = true;
		if(hiddenWord.contains("_"))
			output = false;
		else
			changeScore();
		return output;
	}
	
	public void reset()
	{
		setCurrentCategory();
		setCurrentWord();
		setHiddenWord();
		wrongGuessRem = 6;
	}
}