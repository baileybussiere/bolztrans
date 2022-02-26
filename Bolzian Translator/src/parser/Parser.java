package parser;

import java.util.ArrayList;

import grammar.Phrase;
import grammar.Sentence;

public class Parser
{
	public ArrayList<String> buffer = new ArrayList<String>();
	public ArrayList<Phrase> nouns = new ArrayList<Phrase>();
	public ArrayList<Phrase> verbs = new ArrayList<Phrase>();
	public Sentence currSent;
	
	public Sentence parse(String input)
	{
		String[] main = input.split(" ");
		int index = 0;
		for (; index < main.length; index++)
		{
			if (buffer.size() == 0)
			{
				buffer.add(main[index]);
			}
			else
			{
				
			}
		}
		return null;
	}
}
