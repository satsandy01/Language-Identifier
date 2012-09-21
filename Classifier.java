import java.io.*;
import java.util.*;

public class Classifier
{
	LanguageTrainer trainer;
	public Classifier(LanguageTrainer t)
	{
		trainer = t;
	}
	public String classify(String[] words) throws IOException
	{
		System.out.print("Scoring...");
		double p[]=new double[trainer.getNumberofLanguages()]; 
		//p[i+1] = log conditional probability of the test string being the ith language (language-ids as defined above)
		for(int i=0;i<p.length;i++)
		{
			p[i]=Math.log(0.10);
		}
		int V=trainer.getSizeOfVocabulary();
		//pWord[i+1] = log conditional probability of the word being the ith language (language-ids as defined above)
		for(String word : words) //for every word in the test string
		{
			for(int i=0;i<p.length;i++) //for every language
			{
				int counts=0;
				Language lang = trainer.getLanguage(i); //i is the line-number of the language in the language-details file
				counts = lang.getWordFrequency(word);
				int C = lang.getTotalFrequencyCount();
				double pWord=0;
				if(counts>0)
				{
					double x=(double)(counts+1.0)/(double)(C+V);
					pWord=Math.log(x);
				}
				else
					pWord=-Math.log(C+V);
				p[i]+=pWord;
			}
		}
		System.out.print("Done\n");
		int foundLang=0;
		for(int i=0;i<p.length;i++)
		{
			if(p[i]>p[foundLang])
				foundLang=i;
		}
		return (trainer.getLanguage(foundLang)).name;
	}
}
