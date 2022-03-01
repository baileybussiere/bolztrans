package grammar;

import java.util.Dictionary;

public class Preposition extends Word
{
	public String contractedForm;
	public Noun noun;
	public boolean isEmpty;

	public Preposition(String base)
	{
		super(base);
		this.contractedForm = this.base;
	}

	public Preposition copy()
        {
                return new Preposition(this.base).setContractedForm(this.base);
        }

	public Preposition setContractedForm(String s)
	{
		this.contractedForm = s;
		return this;
	}

	public Preposition create(Noun noun)
	{
		Preposition p = new Preposition(this.base).setContractedForm(this.contractedForm);
		p.noun = noun;
		return p;
	}
	
	public Preposition createEmpty()
	{
		Preposition p = new Preposition(this.base).setContractedForm(this.contractedForm);
		p.isEmpty = true;
		return p;
	}

	public String print()
	{
		String s = this.base;
		if (!isEmpty)
		{
			if (this.noun.equals("bagbu"))
			{
				if (this.base.equals("nu") && this.noun.num != 2)
					s = "bambu";
				else if (this.base.equals("nu") && this.noun.num == 2)
					s = "bangub";
				else if (this.noun.num != 2)
					s = this.noun.print() + this.contractedForm;
			}
			else if (this.noun.equals("huQu"))
			{
				if (this.base.equals("nu") && this.noun.num != 2)
					s = "humpu";
				else if (this.base.equals("nu") && this.noun.num == 2)
					s = "humpa";
				else
					s = this.noun.print() + this.contractedForm;
			}
			else
				s = this.noun.print() + " " + s;
		}
		return s;
	}
}
