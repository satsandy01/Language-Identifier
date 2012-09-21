import java.io.*;
import java.util.*;
public class Language
{
	private int trainingSize; //trainingSize no. of words to be taken into account while training
	private int id;
	String name;
	private int total_frequency;
	private HashMap<String, String> wordId;
	private HashMap<String, String> wordFreq;
	public Language(int i, String n, int t)
	{
		wordId=new HashMap<String, String>(1000);
		wordFreq=new HashMap<String, String>(1000);
		id=i;
		total_frequency = 0;
		name=n;
		trainingSize=t;
	}
	public int getLanguageId()
	{
		return id;
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
	public Set<String> getSetOfWords()
	{
		return wordId.keySet();
	}
	public void train(String filename)throws IOException
	{
		InputStream in = Language.class.getResourceAsStream(filename);
		BufferedReader filein = new BufferedReader(new InputStreamReader(in));
		System.out.print("Training on "+name+"...");
		String line="";
		int count=0;
		while(((line=filein.readLine())!=null) && (count<trainingSize))
		{
			if(count<100)
			{
				count++;
				continue;
			}
			String contents[] = line.split("\t");
			total_frequency+=Integer.parseInt(contents[2]);
			wordId.put(contents[1], contents[0]);
			wordFreq.put(contents[1], contents[2]);
		}
		filein.close();
		System.out.print("Done\n");
	}
}
