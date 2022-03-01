package grammar;

public class Conjunction extends Word
{
	public Conjunction(String conj)
	{
		super(conj);
	}

	public Conjunction copy()
        {
                return new Conjunction(this.base);
        }
}
