package grammar;

import java.util.ArrayList;

public class Word
{
	public ArrayList<String> english = new ArrayList<String>();
	public String base;
	
	public Word(String b)
	{
		this.base = b;
	}
	
	public String print()
	{
		return this.base;
	}
	
	public Word addTranslations(String s)
	{
		return this.addTranslations(new String[] {s});
	}
	
	public Word addTranslations(String[] s)
	{
		for (int i = 0; i < s.length; i++)
		{
			this.english.add(s[i]);
		}
		return this;
	}
	
	public Word addTranslation(String s)
	{
		return this.addTranslations(s);
	}
	
	public boolean match(String string)
	{
		for (int i = 0; i < this.english.size(); i++)
		{
			if (string.equals(this.english.get(i)))
				return true;
		}
		return false;
	}

	public Word inflect(int i)
	{
		return this;
	}
	
	public boolean equals(Word word)
	{
		if (word.base.equals(this.base))
			return true;
		else
			return false;
	}
	
	public boolean equals(String string)
	{
		if (string.equals(this.base))
			return true;
		else
			return false;
	}
}
