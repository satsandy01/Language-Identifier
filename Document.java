import java.io.*;
import java.util.*;
public class Document
{
	private String filename;
	ArrayList<String> words; //array of words after tokenizing the document
	public Document(String f)
	{
		filename = f;
		words = new ArrayList<String>();
	}
	public void breakIntoWords() throws IOException
	{
		BufferedReader filein = new BufferedReader(new FileReader(filename));
		String line="";
		String content="";
		System.out.print("Reading document...");
		while((line=filein.readLine())!=null)
		{
			if(line=="\n")
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
	}
}
