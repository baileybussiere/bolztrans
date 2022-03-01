package main;

import java.util.ArrayList;
import java.util.Scanner;

import grammar.Adverb;
import grammar.Article;
import grammar.Conjunction;
import grammar.Noun;
import grammar.Phrase;
import grammar.Preposition;
import grammar.Sentence;
import grammar.Verb;
import grammar.Word;
import statics.Dictionary;

public class Main
{
	Scanner inputScanner = new Scanner(System.in);
	Dictionary dict = new Dictionary();
	static boolean debug = false;

	// W = Write, T = Translate, S = new Sentence, F = new Phrase, N = new Noun, V = new Verb, A = new Adverb, C = new Conjunction, P = new Preposition, L = new list (Object[]), + = add Modifier, H = hidden,
	char[] keywords = {'W', 'T', 'S', 'F', 'N', 'V', 'A', 'C', 'P', 'L', '+', 'H', 'J'};

	public static void main(String[] args)
	{
		if(args.length == 1)
		{
			if(args[0] == "-d")
			{
				debug = true;
			}
		}
		new Main();
	}

	public Main()
	{
		boolean exit = false;
		while(!exit)
		{
			System.out.print(">");
			String[] str = inputScanner.nextLine().split(" ");
			if(str[0].equals("exit"))
			{
				exit = true;
			}
			else
			{
				str = clean(str);
				Object s = parse(str);
				if (s instanceof String)
					System.out.println("\n" + (String) s);
				else if (s instanceof Noun)
					System.out.println("\n" + ((Noun) s).print());
			}
		}
	}

	private String[] clean(String[] inp)
	{
		if (debug) System.out.println("cleaning");
		ArrayList<String> output = new ArrayList<String>();
		for (int i = 0; i < inp.length; i++)
		{
			if(debug) System.out.print(".");
			int q = 0;
			for (int j = 0; j < inp[i].length(); j++)
			{
				if(debug) System.out.print(".");
				if (inp[i].charAt(j) == '{')
				{
					if (inp[i].length() == j + 1)
					{
						output.add(inp[i].trim());
						q = j + 1;
					}
					else if (inp[i].length() > j + 1)
					{
						output.add(inp[i].substring(q, j + 1).trim());
						q = j + 1;
					}
				}
				else if (inp[i].charAt(j) == '}')
				{
					if (j == 0 && inp[i].length() == 1)
					{
						output.add("}");
						q = j + 1;
					}
					else if (j == 0)
					{
						output.add("}");
						q = j + 1;
					}
					else if (j > 0)
					{
						output.add(inp[i].substring(q, j).trim());
						output.add("}");
						q = j + 1;
					}
				}
				else if (!inp[i].substring(q).contains("{") && !inp[i].substring(q).contains("}"))
				{
					output.add(inp[i].substring(q).trim());
					q = j + inp[i].substring(q).trim().length();
					j = q;
				}
			}
		}
		for (int i = 0; i < output.size(); i++)
		{
			if (output.get(i).equals(" ") || output.get(i).isEmpty() || output.get(i) == null)
			{
				output.remove(i);
				i--;
			}
		}
		String[] outputArray = new String[output.size()];
		int g = 0;
		for (int i = 0; i < output.size(); i++)
		{
			outputArray[g] = output.get(i);
			g++;
		}
		if(debug) System.out.print("\n");
		return outputArray;
	}

	private Object parse(String[] input)
	{
		if(debug) System.out.print(".");
		int ins = 0, outs = 0;
		char keyword = ' ';
		ArrayList<Object> args = new ArrayList<Object>();
		ArrayList<Object> mods = new ArrayList<Object>();
		boolean fHidden = false;
		int start = 0, end = 0;
		if (input[0].charAt(0) == '+')
		{
			input[0] = input[0].substring(1);
		}
		for (int i = 0; i < input.length; i++)
		{
			if (keyword == ' ')
			{
				for (int j = 0; j < keywords.length; )
				{
					if (input[i].charAt(0) == keywords[j])
					{
						if (input[i].length() == 2)
						{
							if (input[i].charAt(1) == '{')
							{
								if (keyword == ' ')
								{
									keyword = keywords[j];
									j = keywords.length;
								}
							}
							else
							{
								System.out.println("Invalid Input: Keyword missing bracket \"{\".");
								return "_INVALID_";
							}
						}
						else
						{
							System.out.println("Invalid Input: Keyword contains invalid modifiers.");
							return "_INVALID_";
						}
					}
					else
					{
						j++;
					}
				}
			}
			else
			{
				if (input[i].contains("{"))
				{
					if (ins == 0)
						start = i;
					ins++;
				}
				else if (input[i].contains("}"))
				{
					outs++;
					if (ins == outs)
					{
						end = i;
						String[] subsection = new String[end - start];
						for (int n = start; n < end; n++)
						{
							subsection[n - start] = input[n];
						}
						if (subsection[0].charAt(0) != '+')
							args.add(parse(subsection));
						else if (subsection[0].charAt(1) == 'H')
							fHidden = true;
						else
							mods.add(parse(subsection));
						ins = 0; outs = 0;
						start = 0; end = 0;
					}
				}
				else if (ins == 0)
				{
					if (input[i].contains("\""))
					{
						args.add(input[i]);
					}
					else if (input[i].matches("[0-9]"))
					{
						args.add(Integer.parseInt(input[i]));
					}
					else
						args.add(dict.lookup(input[i]));
				}
			}
		}
		
		for (int i = 0; i < args.size(); i++)
		{
			if(debug) System.out.print(args.get(i).getClass() + ": ");
			if (args.get(i) instanceof Word)
			{
				if (((Word) args.get(i)).english.size() > 0)
					if(debug) System.out.println(((Word) args.get(i)).english.get(0));
				else
					if(debug) System.out.println(args.get(i));
			}
			else
				if(debug) System.out.println(args.get(i));
		}
		if(debug) System.out.println("--");
		for (int i = 0; i < mods.size(); i++)
		{
			if(debug) System.out.print(mods.get(i).getClass() + ": ");
			if (mods.get(i) instanceof Word)
			{
				if (((Word) mods.get(i)).english.size() > 0)
					if(debug) System.out.println(((Word) mods.get(i)).english.get(0));
				else
					if(debug) System.out.println(mods.get(i));
			}
			else
				if(debug) System.out.println(mods.get(i));
		}
		if(debug) System.out.println("exit");
		
		
		if (args.size() == 0)
		{
			System.out.println("Invalid Input: Empty arguments.");
			return "_INVALID_";
		}
		else
		{
			if (keyword == 'T')
			{
				if (args.size() > 1)
				{
					System.out.println("Invalid input: Too many arguments passed to T{}.");
					return "_INVALID_";
				}
				else if (args.get(0) instanceof Sentence)
				{
					Sentence sentence = (Sentence) args.get(0);
					if (mods.size() != 0)
					{
						System.out.println("Invalid Input: T{} cannot take modifiers.");
						return "_INVALID_";
					}
					else return sentence.print();
				}
				else
				{
					System.out.println("Invalid input: T{} takes a single Sentence as an argument.");
					return "_INVALID_";
				}
			}
			else if (keyword == 'W')
			{
				String str = "";
				for (int i = 0; i < args.size(); i++)
				{
					if (args.get(i) instanceof Noun)
					{
						Noun n = (Noun) args.get(i);
						str = str + " " + n.base + " (n. sing.),";
						str = str + " " + n.plural + " (n. plur.);";
					}
					else if (args.get(i) instanceof Verb)
					{
						Verb v = (Verb) args.get(i);
						str = str + " " + v.base + " (v. pres.),";
						str = str + " " + v.basePast + " (v. past.),";
						str = str + " " + v.baseFut + " (v. fut.);";
					}
					else if (args.get(i) instanceof Adverb)
					{
						Adverb adv = (Adverb) args.get(i);
						str = str + " " + adv.print() + " (adv.);";
					}
					else if (args.get(i) instanceof Conjunction)
					{
						Conjunction c = (Conjunction) args.get(i);
						str = str + " " + c.print() + " (conj.);";
					}
					else if (args.get(i) instanceof Article)
					{
						Article a = (Article) args.get(i);
						str = str + " " + a.print(0) + " (art. sing.);";
						str = str + " " + a.print(1) + " (art. dual);";
						str = str + " " + a.print(2) + " (art. plur.);";
					}
					else
					{
						System.out.println("Invalid Input: W{} is used for translating individual words.");
						return "_INVALID_";
					}
				}
				return str.trim();
			}
			else if (keyword == 'S')
			{
				if (args.get(0) instanceof Phrase)
				{
					if (args.size() > 1)
					{
						if (args.get(1) instanceof Integer)
						{
							if ((Integer) args.get(1) < 3)
							{
								return new Sentence((Phrase) args.get(0), (Integer) args.get(1));
							}
							else
							{
								System.out.println("Invalid Input: Integer argument for S{} out of bounds (0 declarative, 1 interrogative, 2 command).");
								return "_INVALID_";
							}
						}
					}
					else
						return new Sentence((Phrase) args.get(0));
				}
				else
				{
					System.out.println("Invalid Input: S{} takes a phrase as its first argument.");
					return "_INVALID_";
				}
			}
			else if (keyword == 'F')
			{
				Phrase f1 = null;
				if (args.size() == 1)
				{
					if (args.get(0) instanceof Verb[])
					{
						f1  = new Phrase((Verb[]) args.get(0));
					}
					else if (args.get(0) instanceof Verb)
					{
						f1  = new Phrase(new Verb[] {(Verb) args.get(0)});
					}
					else if (args.get(0) instanceof Object[])
					{
						f1  = new Phrase((Object[]) args.get(0));
					}
					else if (args.get(0) instanceof Noun)
					{
						f1  = new Phrase(new Object[]{(Noun) args.get(0)});
					}
					else if (args.get(0) instanceof Phrase)
					{
						f1  = new Phrase(new Object[]{(Phrase) args.get(0)});
					}
					else
					{
						System.out.println("Invalid Input: Invalid argument passed to F{}");
						return "_INVALID_";
					}
					if (mods.size() == 0)
						return f1;
					else if (mods.size() > 2)
					{
						System.out.println("Mods Ignored: Too many modifiers passed to F{}");
						return f1;
					}
					else
					{
						if (mods.get(0) instanceof Conjunction)
						{
							if (mods.size() == 1)
								f1.addConj((Conjunction) mods.get(0));
							if (mods.size() == 2)
							{
								if (mods.get(1) instanceof Phrase)
									f1.addMod((Phrase) mods.get(1));
								else
									System.out.println("Mod Ignored: Duplicate modifier passed to F{}");
							}
						}
						else if (mods.get(0) instanceof Phrase)
						{
							if (mods.size() == 1)
								f1.addMod((Phrase) mods.get(0));
							if (mods.size() == 2)
							{
								if (mods.get(1) instanceof Conjunction)
									f1.addConj((Conjunction) mods.get(1));
								else
									System.out.println("Mod Ignored: Duplicate modifier passed to F{}");
							}
						}
						return f1;
					}
				}
				else if (args.size() == 2)
				{
					if ((args.get(0) instanceof Object[] || args.get(0) instanceof Noun || args.get(0) instanceof Phrase) && (args.get(1) instanceof Verb[] || args.get(1) instanceof Verb))
					{
						Object[] r1;
						Verb[] v1;
						if (args.get(0) instanceof Noun)
							r1 = new Object[] {(Noun) args.get(0)};
						else if (args.get(0) instanceof Phrase)
							r1 = new Object[] {(Phrase) args.get(0)};
						else
							r1 = (Object[]) args.get(0);
						if (args.get(1) instanceof Verb)
							v1 = new Verb[] {(Verb) args.get(1)};
						else
							v1 = (Verb[]) args.get(1);
						f1  = new Phrase(r1, v1);
					}
					else if ((args.get(1) instanceof Object[] || args.get(1) instanceof Noun || args.get(1) instanceof Phrase) && (args.get(0) instanceof Verb[] || args.get(0) instanceof Verb))
					{
						Object[] r1;
						Verb[] v1;
						if (args.get(1) instanceof Noun)
							r1 = new Object[] {(Noun) args.get(1)};
						else if (args.get(1) instanceof Phrase)
							r1 = new Object[] {(Phrase) args.get(1)};
						else
							r1 = (Object[]) args.get(1);
						if (args.get(0) instanceof Verb)
							v1 = new Verb[] {(Verb) args.get(0)};
						else
							v1 = (Verb[]) args.get(0);
						f1  = new Phrase(r1, v1);
					}
					else
					{
						System.out.println("Invalid Input: Invalid argument(s) passed to F{}");
						return "_INVALID_";
					}
					if (mods.size() == 0)
						return f1;
					else if (mods.size() > 2)
					{
						System.out.println("Mods Ignored: Too many modifiers passed to F{}");
						return f1;
					}
					else
					{
						if (mods.get(0) instanceof Conjunction)
						{
							if (mods.size() == 1)
								f1.addConj((Conjunction) mods.get(0));
							if (mods.size() == 2)
							{
								if (mods.get(1) instanceof Phrase)
									f1.addMod((Phrase) mods.get(1));
								else
									System.out.println("Mod Ignored: Duplicate modifier passed to F{}");
							}
						}
						else if (mods.get(0) instanceof Phrase)
						{
							if (mods.size() == 1)
								f1.addMod((Phrase) mods.get(0));
							if (mods.size() == 2)
							{
								if (mods.get(1) instanceof Conjunction)
									f1.addConj((Conjunction) mods.get(1));
								else
									System.out.println("Mod Ignored: Duplicate modifier passed to F{}");
							}
						}
						return f1;
					}
				}
				else if (args.size() == 3)
				{
					if ((args.get(0) instanceof Object[] || args.get(0) instanceof Noun || args.get(0) instanceof Phrase) && (args.get(1) instanceof Verb[] || args.get(1) instanceof Verb) && (args.get(2) instanceof Object[] || args.get(2) instanceof Noun || args.get(2) instanceof Phrase))
					{
						Object[] r1;
						Object[] r3;
						Verb[] v1;
						if (args.get(0) instanceof Noun)
							r1 = new Object[] {(Noun) args.get(0)};
						else if (args.get(0) instanceof Phrase)
							r1 = new Object[] {(Phrase) args.get(0)};
						else
							r1 = (Object[]) args.get(0);
						if (args.get(2) instanceof Noun)
							r3 = new Object[] {(Noun) args.get(2)};
						else if (args.get(2) instanceof Phrase)
							r3 = new Object[] {(Phrase) args.get(2)};
						else
							r3 = (Object[]) args.get(2);
						if (args.get(1) instanceof Verb)
							v1 = new Verb[] {(Verb) args.get(1)};
						else
							v1 = (Verb[]) args.get(1);
						f1 = new Phrase(r1, v1, r3);
					}
					else
					{
						System.out.println("Invalid Input: Invalid argument(s) passed to F{}");
						return "_INVALID_";
					}
					if (mods.size() == 0)
						return f1;
					else if (mods.size() > 2)
					{
						System.out.println("Mods Ignored: Too many modifiers passed to F{}");
						return f1;
					}
					else
					{
						if (mods.get(0) instanceof Conjunction)
						{
							if (mods.size() == 1)
								f1.addConj((Conjunction) mods.get(0));
							if (mods.size() == 2)
							{
								if (mods.get(1) instanceof Phrase)
									f1.addMod((Phrase) mods.get(1));
								else
									System.out.println("Mod Ignored: Duplicate modifier passed to F{}");
							}
						}
						else if (mods.get(0) instanceof Phrase)
						{
							if (mods.size() == 1)
								f1.addMod((Phrase) mods.get(0));
							if (mods.size() == 2)
							{
								if (mods.get(1) instanceof Conjunction)
									f1.addConj((Conjunction) mods.get(1));
								else
									System.out.println("Mod Ignored: Duplicate modifier passed to F{}");
							}
						}
						return f1;
					}	
				}
				else
				{
					System.out.println("Invalid Input: Wrong number of arguments passed to F{}");
					return "_INVALID_";
				}
			}
			else if (keyword == 'N')
			{
				Noun n1 = null;
				if (args.get(0) instanceof Noun)
				{
					if (args.size() == 2)
					{
						if (args.get(1) instanceof Integer)
						{
							if ((Integer) args.get(1) < 3)
							{
								n1 = ((Noun) args.get(0)).inflect((Integer) args.get(1));
							}
						}
						else
							n1 = ((Noun) args.get(0)).inflect(0);
						if (fHidden)
							n1.hide();
					}
					else if (args.size() > 2)
					{
						System.out.println("Invalid Input: Too many arguments passed to N{}");
						return "_INVALID_";
					}
					else if (fHidden)
						n1 = ((Noun) args.get(0)).inflect(0).hide();
					else
						n1 = ((Noun) args.get(0)).inflect(0);
				}
				else if (args.get(0) instanceof String)
				{
					if (args.size() == 2)
					{
						if (args.get(1) instanceof Integer)
						{
							if ((Integer) args.get(1) < 3)
							{
								n1 = new Noun((String) args.get(0)).inflect((Integer) args.get(1));
							}
						}
						else
							n1 = new Noun((String) args.get(0)).inflect(0);
					}
					else if (args.size() > 2)
					{
						System.out.println("Invalid Input: Too many arguments passed to N{}");
						return "_INVALID_";
					}
					else
						n1 = new Noun((String) args.get(0)).inflect(0);
				}
				else if (args.get(0) instanceof Article && args.size() > 1)
				{
					if (args.get(1) instanceof Noun)
					{
						if (args.size() == 3)
						{
							if (args.get(2) instanceof Integer)
							{
								if ((Integer) args.get(2) < 3)
								{
									n1 = (((Noun) args.get(1)).inflect((Integer) args.get(2))).addModifier((Article) args.get(0));
								}
							}
							else
								n1 = (((Noun) args.get(1)).inflect(0)).addModifier((Article) args.get(0));
							if (fHidden)
								n1.hide();
						}
						else if (args.size() > 3)
						{
							System.out.println("Invalid Input: Too many arguments passed to N{}");
							return "_INVALID_";
						}
						else if (fHidden)
							n1 = ((Noun) args.get(1)).inflect(0).hide();
						else
							n1 = (((Noun) args.get(1)).inflect(0)).addModifier((Article) args.get(0));
					}
				}
				if (mods.size() == 0)
					return n1;
				else if (mods.size() > 3)
				{
					System.out.println("Mods Ignored: Too many modifiers passed to N{}");
					return n1;
				}
				else
				{
					boolean nFlag = false;
					boolean pFlag = false;
					if (mods.get(0) instanceof Noun)
					{
						n1.addModifier((Noun) mods.get(0));
						nFlag = true;
					}
					else if (mods.get(0) instanceof Noun[])
					{
						n1.addModifier((Noun[]) mods.get(0));
						nFlag = true;
					}
					else if (mods.get(0) instanceof Phrase)
					{
						n1.addModifier((Phrase) mods.get(0));
						pFlag = true;
					}
					else if (mods.get(0) instanceof Preposition)
					{
						n1.addModifier((Preposition) mods.get(0));
					}
					else if (mods.get(0) instanceof Preposition[])
					{
						n1.addModifier((Preposition[]) mods.get(0));
					}
					if (mods.size() >= 2)
					{
						if (mods.get(1) instanceof Noun && !nFlag)
							n1.addModifier((Noun) mods.get(1));
						else if (mods.get(1) instanceof Noun[] && !nFlag)
							n1.addModifier((Noun[]) mods.get(1));
						else if (mods.get(1) instanceof Phrase && !pFlag)
							n1.addModifier((Phrase) mods.get(1));
						else if (mods.get(1) instanceof Preposition && (nFlag || pFlag))
							n1.addModifier((Preposition) mods.get(1));
						else if (mods.get(1) instanceof Preposition[] && (nFlag || pFlag))
							n1.addModifier((Preposition[]) mods.get(1));
						if (mods.size() == 3)
						{
							if (mods.get(2) instanceof Noun && !nFlag)
								n1.addModifier((Noun) mods.get(2));
							else if (mods.get(2) instanceof Noun[] && !nFlag)
								n1.addModifier((Noun[]) mods.get(2));
							else if (mods.get(2) instanceof Phrase && !pFlag)
								n1.addModifier((Phrase) mods.get(2));
							else if (mods.get(2) instanceof Preposition && nFlag && pFlag)
								n1.addModifier((Preposition) mods.get(2));
							else if (mods.get(2) instanceof Preposition[] && nFlag && pFlag)
								n1.addModifier((Preposition[]) mods.get(2));
							else
							{
								System.out.println("Mod Ignored: Duplicate modifier passed to N{}");
								return n1;
							}
						}
					}
					return n1;
				}
			}
			else if (keyword == 'V')
			{
				Verb v1 = null;
				if (args.get(0) instanceof Verb)
				{
					if (args.size() == 2)
					{
						if (args.get(1) instanceof Integer)
						{
							if ((Integer) args.get(1) < 3)
							{
								v1 = ((Verb) args.get(0)).inflect((Integer) args.get(1));
							}
						}
						else
							v1 = ((Verb) args.get(0)).inflect(0);
					}
					else if (args.size() > 2)
					{
						System.out.println("Invalid Input: Too many arguments passed to V{}");
						return "_INVALID_";
					}
					else
						v1 = ((Verb) args.get(0)).inflect(0);
				}
				else if (args.get(0) instanceof String)
				{
					if (args.size() == 2)
					{
						if (args.get(1) instanceof Integer)
						{
							if ((Integer) args.get(1) < 3)
							{
								v1 = new Verb((String) args.get(0)).inflect((Integer) args.get(1));
							}
						}
						else
							v1 = new Verb((String) args.get(0)).inflect(0);
					}
					else if (args.size() > 2)
					{
						System.out.println("Invalid Input: Too many arguments passed to V{}");
						return "_INVALID_";
					}
					else
						v1 = new Verb((String) args.get(0)).inflect(0);
				}
				if (mods.size() == 0)
					return v1;
				else if (mods.size() > 3)
				{
					System.out.println("Mod Ignored: Too many modifiers passed to V{}");
					return v1;
				}
				else
				{
					boolean vFlag = false;
					boolean aFlag = false;
					boolean pFlag = false;
					for (int i = 0; i < mods.size(); i++)
					{
						if (mods.get(i) instanceof Verb)
						{
							if (!vFlag)
							{
								v1.addModifier((Verb) mods.get(i));
								vFlag = true;
							}
							else
							{
								System.out.println("Mod Ignored: Duplicate modifiers passed to V{}");
							}
						}
						else if (mods.get(i) instanceof Verb[])
						{
							if (!vFlag)
							{
								v1.addModifier((Verb[]) mods.get(i));
								vFlag = true;
							}
							else
							{
								System.out.println("Mod Ignored: Duplicate modifiers passed to V{}");
							}
						}
						else if (mods.get(i) instanceof Adverb)
						{
							if (!aFlag)
							{
								v1.addModifier((Adverb) mods.get(i));
								aFlag = true;
							}
							else
							{
								System.out.println("Mod Ignored: Duplicate modifiers passed to V{}");
							}
						}
						else if (mods.get(i) instanceof Adverb[])
						{
							if (!aFlag)
							{
								v1.addModifier((Adverb[]) mods.get(i));
								aFlag = true;
							}
							else
							{
								System.out.println("Mod Ignored: Duplicate modifiers passed to V{}");
							}
						}
						else if (mods.get(i) instanceof Preposition)
						{
							if (!pFlag)
							{
								v1.addModifier((Preposition) mods.get(i));
								pFlag = true;
							}
							else
							{
								System.out.println("Mod Ignored: Duplicate modifiers passed to V{}");
							}
						}
						else if (mods.get(i) instanceof Preposition[])
						{
							if (!pFlag)
							{
								v1.addModifier((Preposition[]) mods.get(i));
								pFlag = true;
							}
							else
							{
								System.out.println("Mod Ignored: Duplicate modifiers passed to V{}");
							}
						}
						else
						{
							System.out.println("Mod Ignored: Invalid modifier passed to V{}");
						}
					}
					return v1;
				}
			}
			else if (keyword == 'C')
			{
				if (args.get(0) instanceof Conjunction)
				{
					return (Conjunction) args.get(0);
				}
				else 
				{
					System.out.println("Invalid Input: C{} takes a conjunction");
				}
			}
			else if (keyword == 'P')
			{
				if (args.get(0) instanceof Preposition)
				{
					if (mods.size() == 1)
					{
						if (mods.get(0) instanceof Noun)
							return ((Preposition) args.get(0)).create((Noun) mods.get(0));
						else
						{
							System.out.println("Invalid Input: only nouns can modify P{}");
							return "_INVALID_";
						}
					}
					else if (mods.size() == 0)
					{
						return ((Preposition) args.get(0)).createEmpty();
					}
					else
					{
						System.out.println("Invalid Input: Too many modifiers passed to P{}");
						return "_INVALID_";
					}
				}
				else 
				{
					System.out.println("Invalid Input: P{} takes a preposition");
					return "_INVALID_";
				}
			}
			else if (keyword == 'L')
			{
				int listLength = args.size();
				Object[] list;
				if (args.get(0) instanceof Noun)
					list = new Noun[listLength];
				else if (args.get(0) instanceof Verb)
					list = new Verb[listLength];
				else if (args.get(0) instanceof Adverb)
					list = new Adverb[listLength];
				else if (args.get(0) instanceof Preposition)
					list = new Preposition[listLength];
				else if (args.get(0) instanceof Conjunction)
					list = new Conjunction[listLength];
				else if (args.get(0) instanceof Phrase)
					list = new Phrase[listLength];
				else
				{
					System.out.println("Invalid List: Invalid type of list. Valid types are Noun, Verb, Adverb, Preposition, Conjunction, Phrase");
					return (new String[] {"_INVALID_"});
				}
				for (int x = 0; x < listLength; x++)
				{
					if (args.get(x) instanceof Word || args.get(x) instanceof Phrase)
					{
						if (args.get(0).getClass() == args.get(x).getClass())
						{
							list[x] = args.get(0).getClass().cast(args.get(x));
						}
						else
						{
							System.out.println("Invalid Input: Lists must be homogenous.");
							return "_INVALID_";
						}
					}
					else
					{
						System.out.println("Invalid Input: L{} can only take words or phrases");
						return "_INVALID_";
					}
				}
				if (args.get(0) instanceof Noun)
					return (Noun[]) list;
				else if (args.get(0) instanceof Verb)
					return (Verb[]) list;
				else if (args.get(0) instanceof Adverb)
					return (Adverb[]) list;
				else if (args.get(0) instanceof Preposition)
					return (Preposition[]) list;
				else if (args.get(0) instanceof Conjunction)
					return (Conjunction[]) list;
				else if (args.get(0) instanceof Phrase)
					return (Phrase[]) list;
			}
			else if (keyword == 'J')
			{
				if (args.get(0) instanceof Verb)
				{
					for (int comm = 0; comm < 2; comm++)
					{
						if (comm == 0)
							System.out.println("~~~~~~~~INDICATIVE~~~~~~~~");
						else if (comm == 1)
							System.out.println(" ~~~~~~~~COMMAND~~~~~~~~ ");
						for (int a = 0; a < 3; a++)
						{
							if (a == 0)
								System.out.println("-----present tense-----");
							else if (a == 1)
								System.out.println("-----future tense-----");
							else
								System.out.println(" -----past tense-----");
							for (int b = 0; b < 3; b++)
							{
								for (int c = 0; c < 3; c++)
								{
									for (int d = 0; d < 3; d++)
									{
										for (int e = 0; e < 3; e++)
										{
											String subj;
											String obj;
											if (b == 0)
												subj = (c + 1) + "s";
											else if (b == 1)
												subj = (c + 1) + "d";
											else
												subj = (c + 1) + "p";
											if (d == 0)
												obj = (e + 1) + "s";
											else if (d == 1)
												obj = (e + 1) + "d";
											else
												obj = (e + 1) + "p";
											if (comm == 0)
												System.out.println(obj + " " + ((Verb) args.get(0)).conj(b*3 + c, d*3 + e, a, true) + " " + subj);
											else if (a == 0)
												System.out.println(obj + " " + ((Verb) args.get(0)).conj(b*3 + c, d*3 + e, a, false) + " " + subj);
										}
									}
									String subj;
									if (b == 0)
										subj = (c + 1) + "s";
									else if (b == 1)
										subj = (c + 1) + "d";
									else
										subj = (c + 1) + "p";
									if (comm == 0)
										System.out.println("X " + ((Verb) args.get(0)).conj(b*3 + c, -1, a, true) + " " + subj);
									else if (a == 0)
										System.out.println("X " + ((Verb) args.get(0)).conj(b*3 + c, -1, a, false) + " " + subj);
								}
							}
						}
					}
				}
			}
		}
		return keyword;
	}
}
