import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Category 
{
	private String categoryName;
	private String[] words;
	
	public Category(String name, String fileName) throws FileNotFoundException
	{
		categoryName = name;
		Scanner fileScan = new Scanner(new File(fileName));
		String temp = fileScan.nextLine();
		while(!temp.equalsIgnoreCase(categoryName))
		{
			temp = fileScan.nextLine();
		}
		words = fileScan.nextLine().split(" ");
	}

	public String getRandomWord()
	{
		int numWords = words.length;
		int randNum = (int) (Math.random() * numWords);
		return words[randNum].toUpperCase();
	}
	
	public String getCategoryName()
	{
		return categoryName;
	}
}