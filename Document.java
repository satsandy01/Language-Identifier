import java.io.*;
import java.util.*;
public class Document
{
	private String filename;
	private ArrayList<String> words; //array of words after tokenizing the document
	public Document(String f)
	{
		filename = f;
		words = new ArrayList<String>();
	}
	public ArrayList<String> getWords()
	{
		return words;
	}
	public boolean breakIntoWords()
	{
		try
		{
			BufferedReader filein = new BufferedReader(new FileReader(filename));
			String line="";
			String content="";
			System.out.print("Reading document...");
			while((line=filein.readLine())!=null)
			{
				if(line.equals("")||line==null)
					continue;
				content += (line+" ");
			}
			System.out.print("Done\n");
			//Removing punctuation marks from the document string
			System.out.print("Pre-processing the document to remove punctuation marks...");
			content=content.replace(',', ' ');
			content=content.replace('\"', ' ');
			content=content.replace('.', ' ');
			content=content.replace(';', ' ');
			content=content.replace(':', ' ');
			content=content.replace('!', ' ');
			content=content.replace('?', ' ');
			content=content.replace('(', ' ');
			content=content.replace(')', ' ');
			content=content.replace('&', ' ');
			content=content.replace('/', ' ');
			content=content.replaceAll("  ", " ");
			System.out.print("Done\n");
			words = new ArrayList<String>(Arrays.asList(content.split(" ")));
			if(words.size()==0 || (words.size()==1 && words.get(0) ==""))
				return false;
			return true;
		}
		catch(IOException io)
		{
			return false;
		}
	}
}
