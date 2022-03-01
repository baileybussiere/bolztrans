package grammar;

public class Sentence
{
	public Phrase phrase;
	public int type;

	//0 declarative
	//1 interrogative
	//2 command

	public Sentence(Phrase p, int t)
	{
		this.phrase = p;
		this.type = t;
	}

	public Sentence(Phrase p)
	{
		this.phrase = p;
		this.type = 0;
	}

	public String print()
	{
		if (this.type == 0)
		{
			return this.phrase.print();
		}
		else if(this.type == 1)
		{
			return this.phrase.print() + " aa?";
		}
		else
		{
			String string;
			int b;
			if (this.phrase.obj.isEmpty())
				b = -1;
			else
			{
				b = 2;
				for (int i = 0; i < this.phrase.obj.size(); i++)
				{
					if (this.phrase.obj.get(i) instanceof Noun)
					{
						if (((Noun) this.phrase.obj.get(i)).person < b)
							b = ((Noun) this.phrase.obj.get(i)).person;
					}
				}
				if (this.phrase.obj.size() >= 3)
					b += 6;
				else if (this.phrase.obj.size() == 1)
				{
					if (this.phrase.obj.get(0) instanceof Noun)
						b += ((Noun) this.phrase.obj.get(0)).num + 3;
				}
				else
				{
					if (!(this.phrase.obj.get(0) instanceof Noun) && !(this.phrase.obj.get(1) instanceof Noun))
						b += 3;
					else if (this.phrase.obj.get(0) instanceof Noun && this.phrase.obj.get(1) instanceof Noun)
					{
						if (((Noun) this.phrase.obj.get(0)).num == 0 && ((Noun) this.phrase.obj.get(1)).num == 0)
							b += 3;
						else
							b += 6;
					}
					else if (this.phrase.obj.get(0) instanceof Noun)
					{
						if (((Noun) this.phrase.obj.get(0)).num == 0)
							b += 3;
						else
							b += 6;
					}
					else
					{
						if (((Noun) this.phrase.obj.get(1)).num == 0)
							b += 3;
						else
							b += 6;
					}
				}
			}
			string = this.phrase.verb.get(0).print((((Noun) this.phrase.subj.get(0)).num * 3) + ((Noun) this.phrase.subj.get(0)).person, b, this.phrase.verb.get(0).tense, true);
			if (this.phrase.verb.size() > 0)
			{
				for (int i = 1; i < this.phrase.verb.size(); i++)
				{
					string = this.phrase.verb.get(i).print((((Noun) this.phrase.subj.get(0)).num * 3) + ((Noun) this.phrase.subj.get(0)).person, b, this.phrase.verb.get(i).tense, true) + "ra ";
				}
			}
			if (!this.phrase.obj.isEmpty())
			{
				for (int i = 0; i < this.phrase.obj.size(); i++)
				{
					if (this.phrase.obj.get(i) instanceof Noun)
					{
						if (!((Noun) this.phrase.obj.get(i)).hidden)
						{
							if (i < this.phrase.obj.size() - 1)
								string = "ra " + string;
							else
								string = " " + string;
							string = ((Noun) this.phrase.obj.get(i)).print() + string;
						}
					}
					else if (this.phrase.obj.get(i) instanceof Verb)
					{
						if (i < this.phrase.obj.size() - 1)
							string = "ra " + string;
						else
							string = " " + string;
						string = ((Verb) this.phrase.obj.get(i)).getInf(((Verb) this.phrase.obj.get(i)).tense) + string;
					}
					else if (this.phrase.obj.get(i) instanceof Phrase)
					{
						if (i < this.phrase.obj.size() - 1)
							string = " ! " + string;
						else
							string = " " + string;
						string = ((Phrase) this.phrase.obj.get(i)).print() + string;
					}
				}
			}
			return string;
		}
	}
}
