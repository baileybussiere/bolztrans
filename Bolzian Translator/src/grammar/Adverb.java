package grammar;

public class Adverb extends Word
{
	//non-comparative adjectives, mainly time-related
	public Noun noun;

	public Adverb(String s)
	{
		super(s);
	}
	public Adverb copy()
	{
		return new Adverb(this.base);
	}
}
