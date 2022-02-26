package grammar;

public class Article extends Word
{
	String baseDual;
	String basePlural;
	
	public Article(String base)
	{
		super(base);
		this.baseDual = this.base + "n";
		this.basePlural = this.base + "d";
	}
	
	public String print(int num)
	{
		if (num == 0)
			return this.base;
		else if (num == 1)
			return this.baseDual;
		else
			return this.basePlural;
	}
}
