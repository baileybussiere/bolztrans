package grammar;

import java.util.ArrayList;

public class Verb extends Word
{
	public ArrayList<Adverb> modifierAdvs = new ArrayList<Adverb>();
	public ArrayList<Verb> modifierVerbs = new ArrayList<Verb>();
	public ArrayList<Preposition> modifierPreps = new ArrayList<Preposition>();
	public String baseFut;
	public String basePast;
	public int tense;
	public boolean emptyPrep = false;
	public boolean nullOMarking = false;

	public Verb(String inf)
	{
		super(inf);
		this.setInfs();
	}

	public Verb copy()
	{
		Verb v = new Verb(this.base).setInfs(this.baseFut, this.basePast);
		if(nullOMarking)
			v.setNullObjMarking();
		return v;
	}

	public Verb addTranslations(String s)
	{
		super.addTranslations(s);
		return this;
	}
	
	public Verb addTranslations(String[] s)
	{
		super.addTranslations(s);
		return this;
	}
	
	public Verb addTranslation(String s)
	{
		super.addTranslation(s);
		return this;
	}

	private void setInfs()
	{
		String inf = this.base;
		this.baseFut = inf.replaceFirst("a", "i");
		this.basePast = inf.replaceFirst("a", "u");
	}
	
	public Verb setNullObjMarking()
	{
		this.nullOMarking = true;
		return this;
	}

	public Verb setInfs(String a, String b)
	{
		if (!a.isEmpty())
			this.baseFut = a;
		if (!b.isEmpty())
			this.basePast = b;
		return this;
	}

	//0 1s
	//1 2s
	//2 3s
	//3 1d
	//4 2d
	//5 3d
	//6 1p
	//7 2p
	//8 3p
	
	
	
	//a is subj, b is obj, c is tense, d is 

	public String conj(int a, int b, int c, boolean d)
	{
		boolean f = false;
		String s;
		if (d)
		{
			if (c == 0)
				s = this.base;
			else if (c == 1)
				s = this.baseFut;
			else if (c == 2)
				s = this.basePast;
			else
				return this.base;
			if (b == -1 || this.nullOMarking) {}
			else if (b % 3 == 0)
				s = "u-" + s;
			else if (b % 3 == 1)
				s = "i-" + s;
			else
				s = "a-" + s;
			if (a % 3 == 0)
			{
				if (b == -1)
					s = "ha-" + s;
				else if (!this.nullOMarking)
					s = "z" + s;
			}
			else if (a % 3 == 1)
			{
				if (b == -1)
					s = "hi-" + s;
				else if (!this.nullOMarking)
					s = "l" + s;
			}
			else
			{
				if (b == -1)
					s = "hu-" + s;
				else if (this.nullOMarking)
					s = "k" + s;
			}
		}
		else
		{
			s = this.base;
			if (b == -1 || this.nullOMarking) {}
			else if (b % 3 == 0)
			{
				s = s + "na";
				f = true;
			}
			else if (b % 3 == 1)
			{
				s = s + "mi";
				f = true;
			}
			else
			{
				s = s + "lu";
				f = true;
			}

		}
		if (a < 3 && b < 3) {}
		else if (a < 3)
		{
			if (s.lastIndexOf('u') == s.length() - 1 || s.lastIndexOf('i') == s.length() - 1)
			{
				s = s.substring(0, s.length() - 1);
				s = s + "a";
			}
			if (b / 3 == 1)
			{
				s = s + "w";
			}
			else
			{
				s = s + "y";
			}
		}
		else if (a / 3 == 1)
		{
			if (f)
				s = s.substring(0, s.length() - 2);
			if (b < 3)
				s = s + "za";
			else if (b / 3 == 1)
				s = s + "zu";
			else
				s = s + "zi";
		}
		else
		{
			if (b < 3)
				s = s + "z";
			else if (b / 3 == 1)
				s = s + "s";
			else
				s = s + "x";
		}
		return s;
	}

	public String getInf(int tense)
	{
		if (tense == 0)
			return this.base;
		else if (tense == 1)
			return this.baseFut;
		else
			return this.basePast;
	}

	public String print(int subj, int obj, int tense, boolean command)
	{
		String string;
		string = this.conj(subj, obj, tense, !command);
		for (int i = 0; i < this.modifierAdvs.size(); i++)
			string = this.modifierAdvs.get(i).print() + " " + string;
		if (this.modifierPreps.size() > 0)
		{
			int emptyCounter = 0;
			int nonECounter = 0;
			for (int i = 0; i < this.modifierPreps.size(); i++)
			{
				if (this.modifierPreps.get(i).isEmpty && emptyCounter == 0)
				{
					string = string + " " + this.modifierPreps.get(i).print();
					emptyCounter++;
				}
				else if (this.modifierPreps.get(i).isEmpty)
					string = string + "-kara " + this.modifierPreps.get(i).print();
				else if (nonECounter == 0)
				{
					string = this.modifierPreps.get(i).print() + " " + string;
					nonECounter++;
				}
				else
					string = this.modifierPreps.get(i).print() + "-kara " + string;
			}
		}
		for (int i = 0; i < this.modifierVerbs.size(); i++)
			string = this.modifierVerbs.get(i).getInf(this.modifierVerbs.get(i).tense) + " " + string;
		return string;
	}

	public Verb inflect(int tense)
	{
		Verb verb = new Verb(this.base).setInfs(this.baseFut, this.basePast);
		verb.tense = tense;
		return verb;
	}

	public Verb addModifier(Verb verb)
	{
		this.modifierVerbs.add(verb);
		return this;
	}
	
	public Verb addModifier(Verb[] verbs)
	{
		for (int i = 0; i < verbs.length; i++)
			this.modifierVerbs.add(verbs[i]);
		return this;
	}

	public Verb addModifier(Adverb adv)
	{
		this.modifierAdvs.add(adv);
		return this;
	}
	
	public Verb addModifier(Adverb[] advs)
	{
		for (int i = 0; i < advs.length; i++)
			this.modifierAdvs.add(advs[i]);
		return this;
	}
	
	public Verb addModifier(Preposition prep)
	{
		this.modifierPreps.add(prep);
		if (prep.isEmpty)
			this.emptyPrep = true;
		return this;
	}
	
	public Verb addModifier(Preposition[] prep)
	{
		for (int i = 0; i < prep.length; i++)
		{
			this.modifierPreps.add(prep[i]);
			if (prep[i].isEmpty)
				this.emptyPrep = true;
		}
		return this;
	}
}
