package grammar;

import java.util.ArrayList;

public class Noun extends Word
{
	//dual same as singular

	public ArrayList<Noun> modifierNouns = new ArrayList<Noun>();
	public Phrase modifierPhrase;
	public ArrayList<Preposition> modifierPreps = new ArrayList<Preposition>();
	public Article article;

	public String plural;
	public int num = 0;
	public int person = 2;

	public boolean hidden = false;

	public Noun(String n)
	{
		super(n);
		this.base = n;
		this.setPlural();
	}

	public Noun copy()
	{
		return (new Noun(this.base)).setPlural(this.plural).setPerson(this.person);
	}

	public Noun addModifier(Article article)
	{
		this.article = article;
		return this;
	}
	
	public Noun addTranslations(String s)
	{
		super.addTranslations(s);
		return this;
	}
	
	public Noun addTranslations(String[] s)
	{
		super.addTranslations(s);
		return this;
	}
	
	public Noun addTranslation(String s)
	{
		super.addTranslation(s);
		return this;
	}
	
	public Noun hide()
	{
		this.hidden = true;
		return this;
	}

	private void setPlural()
	{
		char[] consonants = this.base.replaceAll("[aiu]", "").toCharArray();
		char[] vowels = this.base.replaceAll("[^aiu]", "").toCharArray();
		if (consonants.length == 3 && vowels.length == 2)
			this.plural = String.valueOf(consonants[0]) + String.valueOf(vowels[0]) + String.valueOf(consonants[1]) + String.valueOf(vowels[1]) + String.valueOf(consonants[2]);
	}

	public Noun setPlural(String n)
	{
		this.plural = n;
		return this;
	}

	public Noun setPerson(int p)
	{
		this.person = p;
		return this;
	}

	public Noun inflect(int i)
	{
		this.num = i;
		return this;
	}

	public Noun addModifier(Noun noun)
	{
		this.modifierNouns.add(noun);
		return this;
	}
	
	public Noun addModifier(Noun[] noun)
	{
		for (int i = 0; i < noun.length; i++)
			this.modifierNouns.add(noun[i]);
		return this;
	}

	public Noun addModifier(Phrase phrase)
	{
		boolean ok = true;
		if (phrase.type == 0)
		{
			for (int i = 0; i < phrase.verb.size(); i++)
			{
				if (!phrase.verb.get(i).emptyPrep)
					ok = false;
			}
		}
		if (!ok)
		{
			System.out.println("Mod Ignored: Full transitive phrase cannot modify a noun unless all verbs have empty prepositions");
			return this;
		}
		else
			this.modifierPhrase = phrase;
		return this;
	}
	
	public Noun addModifier(Preposition prep)
	{
		this.modifierPreps.add(prep);
		return this;
	}
	
	public Noun addModifier(Preposition[] prep)
	{
		for (int i = 0; i < prep.length; i++)
			this.modifierPreps.add(prep[i]);
		return this;
	}

	@Override
	public String print()
	{
		String string;
		if (this.num == 0 || this.num == 1)
			string = this.base;
		else
			string = this.plural;
		for (int i = 0; i < this.modifierNouns.size(); i++)
			string = this.modifierNouns.get(i).print() + " " + string;
		if (this.modifierPhrase != null)
		{
			if (this.modifierPhrase.subj == null)
				string = this.modifierPhrase.print(this) + string;
			else
				string = this.modifierPhrase.print(this, true) + string;
		}
		for (int i = 0; i < this.modifierPreps.size(); i++)
			string = this.modifierPreps.get(i).print() + " " + string;
		if (this.article != null)
			string = this.article.print(this.num) + " " + string;
		return string;
	}
}
