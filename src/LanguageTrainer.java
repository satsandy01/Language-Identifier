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
	private int numberOfLanguages;
	//number of languages supported by the identifier
	private int trainingSize;
	//number of words-per-language to train upon; by default=5000, can be explicitly specified by the user
	/**
	 * 
	 * file is the name of the file containing the list of languages and location of their statistical data,
	 * tSize is the training-size
	 */
	public LanguageTrainer(String file,int tSize)
	{
		vocabulary = new HashSet<String>();
		languageNamesFile=file;
		numberOfLanguages=0;
		trainingSize=tSize;
		list = new ArrayList<Language>();
	}
	//Making stored data available
	/**
	 * @return The number of languages that the classifier supports
	 */
	public int getnumberOfLanguages()
	{
		return numberOfLanguages;
	}
	/**
	 * @return The total number of words from all the supported languages combined
	 */
	public int getSizeOfVocabulary()
	{
		//total number of words in all the languages combined
		return vocabulary.size();
	}
	/**
	 * @return The Language object with language-id i
	 */
	public Language getLanguage(int i)
	{
		//the language object holds the trained information about the corresponding language
		return list.get(i);
	}
	/**
	 * Starts training on all the languages
	 * following the sequence in which they are mentioned in languageNamesFile
	 */
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
			numberOfLanguages = list.size();
			System.out.println("Training complete.");
		}
		catch(Exception io)
		{
			System.err.println("\nError in reading the file containing the list of languages or the file is not found.\nPlease create the file "+languageNamesFile+" in the format mentioned in the README.\n");
			System.exit(0);
		}
	}
}
