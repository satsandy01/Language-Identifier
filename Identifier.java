import java.io.*;
import java.util.*;

public class Identifier
{
	static final String languageList="languages.txt";
	//file that holds the name of languages and their corresponding word-files
	public static void main(String args[]) throws IOException
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		//args[0] = training-size
		int trainingSize;
		if(args.length==0)
		{
			System.out.println("Using default training size of 5000 words/language.\nYou can specify one as command-line argument during execution.");
			trainingSize=5000;
		}
		else
			trainingSize=Integer.parseInt(args[0]);
		if(args.length>1)
		{
			System.out.println("Too many arguements.");
			System.exit(0);
		}
		LanguageTrainer trainer = new LanguageTrainer(languageList, trainingSize);
		trainer.startTraining();
		Classifier languageIdentifier = new Classifier(trainer);
		int more=1;
		while(more==1)
		{
			System.out.print("\nEnter name of document: ");
			String documentName=in.readLine();
			Document doc = new Document(documentName);
			doc.breakIntoWords();
			String predictedLanguage = languageIdentifier.classify(doc.words);
			System.out.println("--> The language is probably "+predictedLanguage+".\n");
			System.out.print("More?<1 for yes/0 for no>: ");
			more=Integer.parseInt(in.readLine());
		}
	}
}
