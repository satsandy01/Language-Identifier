import java.io.*;

/**
 * This class has to be run to start the Language-Identifier program
 * 
 * Steps:
 * Get the document name or from the user,
 * read the file,
 * pre-process and break the string into tokens(words),
 * classify the document and get the predicted language
 */
public class Identifier
{
	static final String languageList="resources/languages.txt";
	//file that holds the name of languages and their corresponding word-files
	public static void main(String args[]) throws IOException
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		//args[0] = training-size, if specified explicitly
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
		trainer.startTraining(); //training starts
		Classifier languageIdentifier = new Classifier(trainer); //pass the trainer with all the trained data to the classifier
		int more=1;
		while(more==1)
		{
			System.out.print("\nEnter name of document (including path): ");
			String documentName=in.readLine();
			Document doc = new Document(documentName);
			if(doc.breakIntoWords())
			{
				Language predictedLanguage = languageIdentifier.classify(doc);
				System.out.println("--> The language is probably "+predictedLanguage.getName()+".\n");
			}
			else
			{
				System.out.println(documentName+" is empty or not found!\n");
			}
			System.out.print("More?<1 for yes/0 for no>: ");
			more=Integer.parseInt(in.readLine());
		}
	}
}
