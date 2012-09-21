import java.io.*;
import java.util.*;
public class LanguageTrainer
{
	private static HashSet<String> vocabulary;
	private Language list[];
	private String languageNamesFile;
	private int numberofLanguages;
	private int trainingSize;
	public LanguageTrainer(String file,int tSize)
	{
		vocabulary = new HashSet<String>();
		languageNamesFile=file;
		numberofLanguages=0;
		trainingSize=tSize;
	}
	public int getNumberofLanguages()
	{
		return numberofLanguages;
	}
	public int getVocabularySize()
	{
		return vocabulary.size();
	}
	public int getSizeOfVocabulary()
	{
		return vocabulary.size();
	}
	public Language getLanguage(int i)
	{
		return list[i];
	}
	private void createList()
	{
		list = new Language[numberofLanguages];
	}
	public void startTraining() throws IOException
	{
		InputStream in = Language.class.getResourceAsStream(languageNamesFile);
		BufferedReader filein = new BufferedReader(new InputStreamReader(in));
		String line="";
		System.out.print("Calculating number of languages...");
		while((line = filein.readLine())!=null)
		{
			numberofLanguages++;
		}
		filein.close();
		System.out.print("Done\n");
		createList();
		in = Language.class.getResourceAsStream(languageNamesFile);
		filein = new BufferedReader(new InputStreamReader(in));
		line="";
		int idx=0;
		System.out.println("Starting training...");
		while((line = filein.readLine())!=null)
		{
			String inp[] = line.split("\t");
			list[idx]=new Language(idx+1, inp[0], trainingSize);
			list[idx].train(inp[1]);
			Set<String> words = list[idx].getSetOfWords();
			for(String word : words)
				vocabulary.add(word);
			idx++;
		}
		System.out.println("Training complete.");
	}
}
