import java.io.*;
import java.util.*;

public class Classifier
{
	LanguageTrainer trainer;
	/**
	 * an instance of LanguageTrainer for the Classifier object
	 * using which it gets all the statistical information available after training
	 */
	public Classifier(LanguageTrainer t)
	{
		trainer = t;
	}
	public Language classify(Document doc) throws IOException
	{
		doc.breakIntoWords();
		System.out.print("Scoring...");
		double p[]=new double[trainer.getNumberofLanguages()]; 
		//p[i] = log conditional probability of the test string being the ith language
		for(int i=0;i<p.length;i++)
		{
			p[i]=Math.log(0.10);
		}
		int V=trainer.getSizeOfVocabulary();
		//V = total size of the vocabulary
		for(int i=0;i<p.length;i++) //for every language
		{
			Language lang = trainer.getLanguage(i);
			//i is the line-number of the language in the language-details file
			//lang has all the statistical information about words in the ith language
			int C = lang.getTotalFrequencyCount();
			//sum total of the frequencies of all the words in the ith language
			for(String word : doc.words)
			{
				int counts = lang.getWordFrequency(word);
				//get the frequency of word
				double pWord=0;
				if(counts>0)
				{
					double x=(double)(counts+1.0)/(double)(C+V);
					pWord=Math.log(x);
					//log conditional probability : log(P(word|language))
				}
				else
					pWord=-Math.log(C+V);
				p[i]+=pWord;
			}
		}
		System.out.print("Done\n");
		//Finding the language with the highest likelihood and returning its name
		int foundLang=0;
		for(int i=0;i<p.length;i++)
		{
			if(p[i]>p[foundLang])
				foundLang=i;
		}
		return trainer.getLanguage(foundLang);
	}
}
