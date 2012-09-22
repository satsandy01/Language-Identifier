import java.io.*;
import java.util.*;
public class Language
{
	/**
	 * Each Language object models one natural language
	 * Trains over available statistical data
	 * Makes the trained data available to the Classifier via an instance of LanguageTrainer
	 */
	private int trainingSize; //trainingSize = no. of words to be taken into account while training
	private int id; //language id generated according to the position of this language in the file "languages.txt"
	private String name; //name of the language
	private int total_frequency; //sum total of the frequencies of all the words in a language
	private HashMap<String, String> wordId; // word --> word-Id
	private HashMap<String, String> wordFreq; //word --> word-Frequency
	private String filename; //name of the file containing statistical data of words in that language
	public Language(int i, String n, String file, int t)
	{
		wordId=new HashMap<String, String>(1000);
		wordFreq=new HashMap<String, String>(1000);
		id=i;
		total_frequency = 0;
		name=n;
		trainingSize=t;
		filename = file;
	}
	//making the trained data available
	public int getLanguageId()
	{
		return id;
	}
	public String getName()
	{
		return name;
	}
	public int getTotalFrequencyCount()
	{
		return total_frequency;
	}
	public int getWordFrequency(String word)
	{
		if(wordFreq.containsKey(word))
			return Integer.parseInt(wordFreq.get(word));
		else
			return 0;
	}
	public int getWordId(String word)
	{
		if(wordId.containsKey(word))
			return Integer.parseInt(wordId.get(word));
		else
			return -1;
	}
	public Set<String> getSetOfWords() //returns the set of words in a particular language
	{
		return wordId.keySet();
	}
	public void train()
	{
		try
		{
			//Training by putting word-Frequencies in a HashMap with words as indices
			InputStream in = Language.class.getResourceAsStream(filename);
			BufferedReader filein = new BufferedReader(new InputStreamReader(in));
			System.out.print("Training on "+name+"...");
			String line="";
			int count=0;
			while(((line=filein.readLine())!=null) && (count<trainingSize))
			{
				count++;
				if(count<100)				
					continue;
				String contents[] = line.split("\t");
				total_frequency+=Integer.parseInt(contents[2]);
				wordId.put(contents[1], contents[0]);
				wordFreq.put(contents[1], contents[2]);
			}
			filein.close();
			System.out.print("Done\n");
		}
		catch(Exception io)
		{
			System.err.println("Data file for "+name+" not found!");
			System.exit(0);
		}
	}
}
