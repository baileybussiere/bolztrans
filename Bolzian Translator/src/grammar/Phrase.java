package grammar;

import java.util.ArrayList;

public class Phrase
{
	public ArrayList<Verb> verb = new ArrayList<Verb>();
	public ArrayList<Object> subj = new ArrayList<Object>();
	public ArrayList<Object> obj = new ArrayList<Object>();

	public int type;
	//0 full clause; can modify another phrase or a noun if empty preposition is present
	//1 objectless clause; can modify another phrase or a noun
	//2 subjectless clause; can modify a noun
	//3 appositive phrase; can modify a noun
	//4 verbal clause; can modify a noun

	public Phrase modifier;
	public Conjunction conj; //this acts on Phrase

	public Phrase(Object[] subj, Verb[] verb, Object[] obj)
	{
		for (int i = 0; i < verb.length; i++)
			this.verb.add(verb[i]);
		for (int i = 0; i < subj.length; i++)
			this.subj.add(subj[i]);
		for (int i = 0; i < obj.length; i++)
			this.obj.add(obj[i]);
		this.type = 0;
	}

	public Phrase(Object[] subj, Verb[] verb)
	{
		for (int i = 0; i < subj.length; i++)
			this.subj.add(subj[i]);
		for (int i = 0; i < verb.length; i++)
			this.verb.add(verb[i]);
		this.type = 1;
	}

	public Phrase(Verb[] verb, Object[] obj)
	{
		for (int i = 0; i < verb.length; i++)
			this.verb.add(verb[i]);
		for (int i = 0; i < obj.length; i++)
			this.obj.add(obj[i]);
		this.type = 2;
	}

	public Phrase(Object[] obj)
	{
		for (int i = 0; i < obj.length; i++)
			this.obj.add(obj[i]);
		this.type = 3;
	}

	public Phrase(Verb[] verb)
	{
		for (int i = 0; i < verb.length; i++)
		this.verb.add(verb[i]);
		this.type = 4;
	}

	public Phrase addConj(Conjunction c)
	{
		this.conj = c;
		return this;
	}
	
	public Phrase addMod(Phrase p)
	{
		this.modifier = p;
		return this;
	}

	public Phrase addSubj(Object subj)
	{
		this.subj.add(subj);
		return this;
	}

	public Phrase addObj(Object obj)
	{
		this.obj.add(obj);
		return this;
	}

	public Phrase addVerb(Verb verb)
	{
		this.verb.add(verb);
		return this;
	}

	public String print()
	{
		String string;
		int b;
		if (this.obj.isEmpty())
			b = -1;
		else
		{
			b = 2;
			for (int i = 0; i < this.obj.size(); i++)
			{
				if (this.obj.get(i) instanceof Noun)
				{
					if (((Noun) this.obj.get(i)).person < b)
						b = ((Noun) this.obj.get(i)).person;
				}
			}
			if (this.obj.size() >= 3)
				b += 6;
			else if (this.obj.size() == 1)
			{
				if (this.obj.get(0) instanceof Noun)
					b += ((Noun) this.obj.get(0)).num * 3;
			}
			else
			{
				if (!(this.obj.get(0) instanceof Noun) && !(this.obj.get(1) instanceof Noun))
					b += 3;
				else if (this.obj.get(0) instanceof Noun && this.obj.get(1) instanceof Noun)
				{
					if (((Noun) this.obj.get(0)).num == 0 && ((Noun) this.obj.get(1)).num == 0)
						b += 3;
					else
						b += 6;
				}
				else if (this.obj.get(0) instanceof Noun)
				{
					if (((Noun) this.obj.get(0)).num == 0)
						b += 3;
					else
						b += 6;
				}
				else
				{
					if (((Noun) this.obj.get(1)).num == 0)
						b += 3;
					else
						b += 6;
				}
			}
		}
		int a = 2;
		for (int i = 0; i < this.subj.size(); i++)
		{
			if (this.subj.get(i) instanceof Noun)
			{
				if (((Noun) this.subj.get(i)).person < a)
					a = ((Noun) this.subj.get(i)).person;
			}
		}
		if (this.subj.size() >= 3)
			a += 6;
		else if (this.subj.size() == 1)
		{
			if (this.subj.get(0) instanceof Noun)
				a += ((Noun) this.subj.get(0)).num * 3;
		}
		else
		{
			if (this.subj.size() > 0)
			{
				if (!(this.subj.get(0) instanceof Noun) && !(this.subj.get(1) instanceof Noun))
					a += 3;
				else if (this.subj.get(0) instanceof Noun && this.subj.get(1) instanceof Noun)
				{
					if (((Noun) this.subj.get(0)).num == 0 && ((Noun) this.subj.get(1)).num == 0)
						a += 3;
					else
						a += 6;
				}
				else if (this.subj.get(0) instanceof Noun)
				{
					if (((Noun) this.subj.get(0)).num == 0)
						a += 3;
					else
						a += 6;
				}
				else
				{
					if (((Noun) this.subj.get(1)).num == 0)
						a += 3;
					else
						a += 6;
				}
			}
		}
		string = "";
		if (this.verb.size() > 0)
		{
			string = this.verb.get(0).print(a, b, this.verb.get(0).tense, false);
			for (int i = 1; i < this.verb.size(); i++)
			{
				string = this.verb.get(i).print(a, b, this.verb.get(i).tense, false) + "-ra " + string;
			}
		}
		for (int i = 0; i < this.subj.size(); i++)
		{
			if (this.subj.get(i) instanceof Noun)
			{
				if (!(((Noun) this.subj.get(i)).hidden))
				{
					string = string + " " + ((Noun) this.subj.get(i)).print();
					if (i < this.subj.size() - 1)
						string = string + "-ra";
				}
			}
			else if (this.subj.get(i) instanceof Verb)
			{
				string = string + " " + ((Verb) this.subj.get(i)).getInf(((Verb) this.subj.get(i)).tense);
				if (i < this.subj.size() - 1)
					string = string + "-ra";
			}
			else if (this.subj.get(i) instanceof Phrase)
			{
				string = string + " " + ((Phrase) this.subj.get(i)).print();
				if (i < this.subj.size() - 1)
					string = string + " nara";
			}
		}
		if (!this.obj.isEmpty())
		{
			for (int i = 0; i < this.obj.size(); i++)
			{
				if (this.obj.get(i) instanceof Noun)
				{
					if (!((Noun) this.obj.get(i)).hidden)
					{
						if (i == 0)
							string = " " + string;
						else
							string = "-ra " + string;
						string = ((Noun) this.obj.get(i)).print() + string;
					}
				}
				else if (this.obj.get(i) instanceof Verb)
				{
					if (i == 0)
						string = " " + string;
					else
						string = "-ra " + string;
					string = ((Verb) this.obj.get(i)).getInf(((Verb) this.obj.get(i)).tense) + string;
				}
				else if (this.obj.get(i) instanceof Phrase)
				{
					if (i == 0)
						string = " " + string;
					else
						string = " nara " + string;
					string = ((Phrase) this.obj.get(i)).print() + string;
				}
			}
		}
		if (this.modifier != null)
			string = this.modifier.print() + " " + this.conj.print() + " " + string;
		return string;
	}

	public String print(Noun noun)
	{
		return this.print(noun, false);
	}

	public String print(Noun noun, boolean obj)
	{
		String string = "";
		int b;
		if (!obj) //the governing noun is the subject
		{
			if (this.obj.isEmpty())
				b = -1;
			else
			{
				b = 2;
				for (int i = 0; i < this.obj.size(); i++)
				{
					if (this.obj.get(i) instanceof Noun)
					{
						if (((Noun) this.obj.get(i)).person < b)
							b = ((Noun) this.obj.get(i)).person;
					}
				}
				if (this.obj.size() >= 3)
					b += 6;
				else if (this.obj.size() == 1)
				{
					if (this.obj.get(0) instanceof Noun)
						b += ((Noun) this.obj.get(0)).num * 3;
				}
				else if (!this.obj.isEmpty())
				{
					if (!(this.obj.get(0) instanceof Noun) && !(this.obj.get(1) instanceof Noun))
						b += 3;
					else if (this.obj.get(0) instanceof Noun && this.obj.get(1) instanceof Noun)
					{
						if (((Noun) this.obj.get(0)).num == 0 && ((Noun) this.obj.get(1)).num == 0)
							b += 3;
						else
							b += 6;
					}
					else if (this.obj.get(0) instanceof Noun)
					{
						if (((Noun) this.obj.get(0)).num == 0)
							b += 3;
						else
							b += 6;
					}
					else
					{
						if (((Noun) this.obj.get(1)).num == 0)
							b += 3;
						else
							b += 6;
					}
				}
			}
			if (!this.verb.isEmpty())
			{
				string = this.verb.get(0).print((noun.num * 3) + noun.person, b, this.verb.get(0).tense, false);
				if (this.verb.size() > 0)
				{
					for (int i = 1; i < this.verb.size(); i++)
					{
						string = this.verb.get(i).print((noun.num * 3) + noun.person, b, this.verb.get(0).tense, false) + "-ra " + string;
					}
				}
			}
			if (!this.obj.isEmpty())
			{
				String string2 = "";
				for (int i = 0; i < this.obj.size(); i++)
				{
					if (this.obj.get(i) instanceof Noun)
					{
						if (!((Noun) this.obj.get(i)).hidden)
						{
							if (i < this.obj.size() - 1)
								string2 = "-ra " + string2;
							else
								string2 = " " + string2;
							string2 = ((Noun) this.obj.get(i)).print() + string2;
						}
					}
					else if (this.obj.get(i) instanceof Verb)
					{
						if (i < this.obj.size() - 1)
							string2 = "-ra " + string2;
						else
							string2 = " " + string2;
						string2 = ((Verb) this.obj.get(i)).getInf(((Verb) this.obj.get(i)).tense) + string2;
					}
					else if (this.obj.get(i) instanceof Phrase)
					{
						if (i < this.obj.size() - 1)
							string2 = " nara " + string2;
						else
							string2 = " " + string2;
						string2 = ((Phrase) this.obj.get(i)).print() + string2;
					}
				}
				string = string2 + string;
			}
		}
		else //the governing noun is not the subject
		{
			if (this.subj.isEmpty())
				b = -1;
			else
			{
				b = 2;
				for (int i = 0; i < this.subj.size(); i++)
				{
					if (this.subj.get(i) instanceof Noun)
					{
						if (((Noun) this.subj.get(i)).person < b)
							b = ((Noun) this.subj.get(i)).person;
					}
				}
				if (this.subj.size() >= 3)
					b += 6;
				else if (this.subj.size() == 1)
				{
					if (this.subj.get(0) instanceof Noun)
						b += ((Noun) this.subj.get(0)).num * 3;
				}
				else if (!this.subj.isEmpty())
				{
					if (!(this.subj.get(0) instanceof Noun) && !(this.subj.get(1) instanceof Noun))
						b += 3;
					else if (this.subj.get(0) instanceof Noun && this.subj.get(1) instanceof Noun)
					{
						if (((Noun) this.subj.get(0)).num == 0 && ((Noun) this.subj.get(1)).num == 0)
							b += 3;
						else
							b += 6;
					}
					else if (this.subj.get(0) instanceof Noun)
					{
						if (((Noun) this.subj.get(0)).num == 0)
							b += 3;
						else
							b += 6;
					}
					else
					{
						if (((Noun) this.subj.get(1)).num == 0)
							b += 3;
						else
							b += 6;
					}
				}
			}
			int q;
			if (this.obj.isEmpty())
				q = -1;
			else
			{
				q = 2;
				for (int i = 0; i < this.obj.size(); i++)
				{
					if (this.obj.get(i) instanceof Noun)
					{
						if (((Noun) this.obj.get(i)).person < q)
							q = ((Noun) this.obj.get(i)).person;
					}
				}
				if (this.obj.size() >= 3)
					q += 6;
				else if (this.obj.size() == 1)
				{
					if (this.obj.get(0) instanceof Noun)
						q += ((Noun) this.obj.get(0)).num * 3;
				}
				else if (!this.obj.isEmpty())
				{
					if (!(this.obj.get(0) instanceof Noun) && !(this.obj.get(1) instanceof Noun))
						q += 3;
					else if (this.obj.get(0) instanceof Noun && this.obj.get(1) instanceof Noun)
					{
						if (((Noun) this.obj.get(0)).num == 0 && ((Noun) this.obj.get(1)).num == 0)
							q += 3;
						else
							q += 6;
					}
					else if (this.obj.get(0) instanceof Noun)
					{
						if (((Noun) this.obj.get(0)).num == 0)
							q += 3;
						else
							q += 6;
					}
					else
					{
						if (((Noun) this.obj.get(1)).num == 0)
							q += 3;
						else
							q += 6;
					}
				}
			}
			boolean objFlag = false;
			if (!this.obj.isEmpty())
			{
				objFlag = true;
				String string2 = "";
				for (int i = 0; i < this.obj.size(); i++)
				{
					if (this.obj.get(i) instanceof Noun)
					{
						if (!((Noun) this.obj.get(i)).hidden)
						{
							if (i < this.obj.size() - 1)
								string2 = "-ra " + string2;
							else
								string2 = " " + string2;
							string2 = ((Noun) this.obj.get(i)).print() + string2;
						}
					}
					else if (this.obj.get(i) instanceof Verb)
					{
						if (i < this.obj.size() - 1)
							string2 = "-ra " + string2;
						else
							string2 = " " + string2;
						string2 = ((Verb) this.obj.get(i)).getInf(((Verb) this.obj.get(i)).tense) + string2;
					}
					else if (this.obj.get(i) instanceof Phrase)
					{
						if (i < this.obj.size() - 1)
							string2 = " nara " + string2;
						else
							string2 = " " + string2;
						string2 = ((Phrase) this.obj.get(i)).print() + string2;
					}
				}
				string = string2;
			}
			if (!this.verb.isEmpty())
			{
				if (!objFlag)
					q = (noun.num * 3) + noun.person;
				string = this.verb.get(0).print(b, q, this.verb.get(0).tense, false) + " " + string;
				if (this.verb.size() > 0)
				{
					for (int i = 1; i < this.verb.size(); i++)
					{
						string = this.verb.get(i).print(b, q, this.verb.get(0).tense, false) + "-ra " + string;
					}
				}
			}
			if (!this.subj.isEmpty())
			{
				String string2 = "";
				for (int i = 0; i < this.subj.size(); i++)
				{
					if (this.subj.get(i) instanceof Noun)
					{
						if (!((Noun) this.subj.get(i)).hidden)
						{
							if (i < this.subj.size() - 1)
								string2 = "-ra " + string2;
							else
								string2 = " " + string2;
							string2 = ((Noun) this.subj.get(i)).print() + string2;
						}
					}
					else if (this.subj.get(i) instanceof Verb)
					{
						if (i < this.subj.size() - 1)
							string2 = "-ra " + string2;
						else
							string2 = " " + string2;
						string2 = ((Verb) this.subj.get(i)).getInf(((Verb) this.subj.get(i)).tense) + string2;
					}
					else if (this.subj.get(i) instanceof Phrase)
					{
						if (i < this.subj.size() - 1)
							string2 = " nara " + string2;
						else
							string2 = " " + string2;
						string2 = ((Phrase) this.subj.get(i)).print() + string2;
					}
				}
				string = string + string2;
			}
		}
		if (this.modifier != null)
			string = this.modifier.print() + " " + this.modifier.conj + " " + string;
		if (this.conj != null)
			string = string + " " + this.conj.print();
		if (this.verb.isEmpty())
			string = string + " -";
		return string;
	}
}
