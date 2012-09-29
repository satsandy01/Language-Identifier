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
	/**
	 * Scores each language for the words in the document with log conditional probabilities (Multinomial Naive Bayes)
	 * 
	 * @param doc The document to be classified
	 * @return The Language object corresponding to maximum likelihood
	 */
	public Language classify(Document doc)
	{
		System.out.print("Scoring...");
		int n = trainer.getnumberOfLanguages();
		double p[]=new double[n]; 
		//p[i] = log conditional probability of the test string being the ith language
		for(int i=0;i<p.length;i++)
		{
			p[i]=-Math.log(n);
		}
		int V=trainer.getSizeOfVocabulary();
		ArrayList<String> words = doc.getWords();
		//V = total size of the vocabulary
		for(int i=0;i<p.length;i++) //for every language
		{
			Language lang = trainer.getLanguage(i);
			//i is the line-number of the language in the language-details file
			//lang has all the statistical information about words in the ith language
			int C = lang.getTotalFrequencyCount();
			//sum total of the frequencies of all the words in the ith language
			for(String word : words)
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
