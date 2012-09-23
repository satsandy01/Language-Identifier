import java.io.*;
import java.util.*;
public class LanguageTrainer
{
	private static HashSet<String> vocabulary;
	//Set of all the words in all the languages combined
	private ArrayList<Language> list;
	//Array of Language Objects, corresponding to the list of languages supported by the identifier
	private String languageNamesFile;
	//file that holds the name of languages and their corresponding word-files
	private int numberofLanguages;
	//number of languages supported by the identifier
	private int trainingSize;
	//number of words/language to train upon; by default=5000, can be explicitly specified by the user
	public LanguageTrainer(String file,int tSize)
	{
		vocabulary = new HashSet<String>();
		languageNamesFile=file;
		numberofLanguages=0;
		trainingSize=tSize;
		list = new ArrayList<Language>();
	}
	//Making stored data available
	public int getNumberofLanguages()
	{
		return numberofLanguages;
	}
	public int getSizeOfVocabulary()
	{
		//total number of words in all the languages combined
		return vocabulary.size();
	}
	public Language getLanguage(int i)
	{
		//returns the language object correspoding to language-id=i
		//the language object holds the trained information about the corresponding language
		return list.get(i);
	}
	public void startTraining()
	{
		try
		{
			InputStream in = Language.class.getResourceAsStream(languageNamesFile);
			BufferedReader filein = new BufferedReader(new InputStreamReader(in));
			String line="";
			int idx=0;
			System.out.println("Starting training...");
			while((line = filein.readLine())!=null)
			{
				if(line=="\n")
					continue;
				String inp[] = line.split("\t");
				Language l=new Language(idx, inp[0], inp[1], trainingSize);
				l.train();
				list.add(l); //adding the new language to the list of languages
				Set<String> words = l.getSetOfWords();
				for(String word : words)
					vocabulary.add(word);
				//adding the set of words in the language to the combined vocabulary
				idx++;
			}
			numberofLanguages = list.size();
			System.out.println("Training complete.");
		}
		catch(Exception io)
		{
			System.err.println("\nFile containing the list of languages not found.\nPlease create the file \"resources/languages.txt\" in the format mentioned in the README\n");
			System.exit(0);
		}
	}
}
