package statics;

import java.util.ArrayList;

import grammar.Adverb;
import grammar.Article;
import grammar.Conjunction;
import grammar.Noun;
import grammar.Preposition;
import grammar.Verb;
import grammar.Word;

public class Dictionary
{
	// ' glottal stop
	// | lateral click
	// Q bilingual click
	// ! retroflex click
	// x sh
	// h x
	public static Word v_be = new Verb("ta").setInfs("da", "tta").setNullObjMarking().addTranslations(new String[] {"be", "is", "are", "were", "was", "am"});
	public static Word v_have = new Verb("baga").addTranslations(new String[] {"has", "have", "had"});
	public static Word v_can = new Verb("|a").setInfs("lla", "|u").setNullObjMarking().addTranslations(new String[] {"can", "able to", "able"});
	public static Word v_own = new Verb("pka").addTranslations(new String[] {"own", "owns", "owned"});
	public static Word v_eat = new Verb("sa!a").addTranslations(new String[] {"eat", "eats", "ate"});
	public static Word v_devour = new Verb("s!a").addTranslations(new String[] {"devour", "devours", "devoured"});
	public static Word v_taste = new Verb("zda").addTranslations(new String[] {"taste", "tastes", "tasted"});
	public static Word v_make = new Verb("'a").setInfs("ti'a", "tu'a").addTranslations(new String[] {"make", "makes", "made"});
	public static Word v_speak = new Verb("dassa").addTranslations(new String[] {"speak", "speaks", "spoke"});
	public static Word v_yell = new Verb("tsasa").addTranslations(new String[] {"yell", "yells", "yelled"});
	public static Word v_whisper = new Verb("dzasa").addTranslations(new String[] {"whisper", "whispers", "whispered"});
	public static Word v_break = new Verb("garla").addTranslations(new String[] {"break", "breaks", "broke"});
	public static Word v_destroy = new Verb("krala").addTranslations(new String[] {"destroy", "destroys", "destroyed"});
	public static Word v_deconstruct = new Verb("grala").addTranslations(new String[] {"deconstruct", "deconstructs", "deconstructed"});
	public static Word v_kill = new Verb("paxta").addTranslations(new String[] {"kill", "kills", "killed"});
	public static Word v_murder = new Verb("pxata").addTranslations(new String[] {"murder", "murders", "murdered"});
	public static Word v_euthanize = new Verb("byata").addTranslations(new String[] {"euthanize", "euthanizes", "euthanized"});
	public static Word v_go = new Verb("Qakwa").setInfs("Qixa", "Quka").addTranslations(new String[] {"go", "went", "goes"});
	public static Word v_run = new Verb("mkawa").setInfs("mkiha", "mkuwa").addTranslations(new String[] {"run", "runs", "ran"});
	public static Word v_walk = new Verb("mgawa").setInfs("mgiha", "mkuwa").addTranslations(new String[] {"walk", "walks", "walked"});
	public static Word v_like = new Verb("ga|a").addTranslations(new String[] {"like", "likes", "liked"});
	public static Word v_love = new Verb("kla").addTranslations(new String[] {"love", "loves", "loved"});
	public static Word v_favor = new Verb("gla").addTranslations(new String[] {"favor", "favors", "favored", "favour", "favours", "favoured"});
	public static Word v_want = new Verb("swa").setInfs("sya", "zwa").addTranslations(new String[] {"want", "wants", "wanted"});
	public static Word v_read = new Verb("zabwa").addTranslations(new String[] {"read", "reads"});
	public static Word v_allow = new Verb("takxa").addTranslations(new String[] {"allow", "allows", "allowed"});
	public static Word v_free  = new Verb("tkaxa").addTranslations(new String[] {"free", "reads", "freed"});
	public static Word v_let  = new Verb("dgaxa").addTranslations(new String[] {"let", "lets"});
	public static Word v_give  = new Verb("kaska").addTranslations(new String[] {"give", "gives", "gave"});
	public static Word v_save  = new Verb("dagga").addTranslations(new String[] {"save", "saves", "saved"});
	public static Word v_guard  = new Verb("tkaga").addTranslations(new String[] {"guard", "guards", "guarded"});
	public static Word v_keep  = new Verb("dgaga").addTranslations(new String[] {"keep", "keeps", "kept"});
	public static Word v_jump  = new Verb("xatya").addTranslations(new String[] {"jump", "jumps", "jumped"});
	public static Word v_leap  = new Verb("xtaya").addTranslations(new String[] {"leap", "leaps", "lept"});
	public static Word v_hop  = new Verb("ydaga").addTranslations(new String[] {"hop", "hops", "hopped"});
	public static Word v_listen  = new Verb("labba").addTranslations(new String[] {"listen", "listens", "listened"});
	public static Word v_hear  = new Verb("lbaba").addTranslations(new String[] {"hear", "hears", "heard"});
	public static Word v_eavesdrop  = new Verb("lpaba").addTranslations(new String[] {"eavesdrop", "eavesdrops", "eavesdropped"});
	public static Word v_know  = new Verb("palda").addTranslations(new String[] {"know", "knows", "knew"});
	public static Word v_understand  = new Verb("plada").addTranslations(new String[] {"understand", "understands", "understood"});
	public static Word v_meet  = new Verb("blada").addTranslations(new String[] {"meet", "meets", "met"});
	public static Word v_look  = new Verb("palya").addTranslations(new String[] {"look", "looks", "looked"});
	public static Word v_glance  = new Verb("blaya").addTranslations(new String[] {"glance", "glances", "glanced"});
	public static Word v_see  = new Verb("playa").addTranslations(new String[] {"see", "sees", "saw"});
	public static Word v_play = new Verb("'asma").addTranslations(new String[] {"have fun", "has fun", "had fun", "play", "plays", "played"});

	public static Word _not = new Adverb("'ar").addTranslation("not");
	public static Word _only = new Adverb("'asta").addTranslation("only");
	public static Word _always = new Adverb("kizdu").addTranslation("always");
	public static Word _never = new Adverb("yawni").addTranslation("never");
	public static Word _completely = new Adverb("'us").addTranslation("completely");
	public static Word _just = new Adverb("day").addTranslation("just");
	public static Word _alot = new Adverb("|u").addTranslation("a lot");
	public static Word _rarely = new Adverb("!ar").addTranslation("rarely");
	public static Word _allegedly = new Adverb("suba").addTranslation("allegedly");
	public static Word _already = new Adverb("huh").addTranslation("already");

	public static Word tree = new Noun("'adra").addTranslations(new String[] {"tree", "trees"});
	public static Word bush = new Noun("'idru").addTranslations(new String[] {"bush", "bushes"});
	public static Word god = new Noun("skali").setPlural("sagil").addTranslations(new String[] {"god", "gods"});
	public static Word i = new Noun("bagbu").setPlural("bakub").setPerson(0).addTranslations(new String[] {"I", "me", "myself"});
	public static Word you = new Noun("hu!u").setPlural("hu!a").setPerson(1).addTranslations(new String[] {"you", "thou", "thee", "yourself", "thyself"});
	public static Word secret = new Noun("xubli").addTranslations(new String[] {"secret", "secrets"});
	public static Word time = new Noun("twara").addTranslations("time");
	public static Word human = new Noun("bru").setPlural("buk").addTranslations(new String[] {"human", "humans", "people"});
	public static Word man = new Noun("branu").setPlural("bakun").addTranslations(new String[] {"man", "men"});
	public static Word woman = new Noun("brini").setPlural("bikin").addTranslations(new String[] {"woman", "women"});
	public static Word boy = new Noun("bralu").setPlural("bakul").addTranslations(new String[] {"boy", "boys"});
	public static Word girl = new Noun("brili").setPlural("bikil").addTranslations(new String[] {"girl", "girls"});
	public static Word relative = new Noun("diwwa").addTranslations(new String[] {"family", "relative", "family member", "relatives"});
	public static Word parent = new Noun("'iha").setPlural("'i'ah").addTranslations(new String[] {"parent", "parents"});
	public static Word father = new Noun("'ahu").setPlural("'a'uh").addTranslations(new String[] {"father", "dad", "papa", "fathers", "dads"});
	public static Word mother = new Noun("'ihi").setPlural("'i'ih").addTranslations(new String[] {"mother", "mom", "mama", "mothers", "moms"});
	public static Word son = new Noun("'axxu").addTranslations(new String[] {"son", "sons"});
	public static Word daughter = new Noun("'ixxi").addTranslations(new String[] {"daughter", "daughters"});
	public static Word sibling = new Noun("pxu").addTranslations(new String[] {"sibling", "siblings"});
	public static Word brother = new Noun("buxka").addTranslations(new String[] {"brother", "brothers", "bro"});
	public static Word sister = new Noun("bixki").addTranslations(new String[] {"sister", "sisters", "sis"});
	public static Word friend = new Noun("igdla").setPlural("igdal").addTranslations(new String[] {"friend", "friends", "freind", "freinds"});
	public static Word boyfriend = new Noun("agdlu").setPlural("agdul").addTranslations(new String[] {"boyfriend", "boyfriends", "boyfreind", "boyfreinds"});
	public static Word girlfriend = new Noun("igdli").setPlural("igdil").addTranslations(new String[] {"girlfriend", "girlfriends", "girlfreind", "girlfreinds"});
	public static Word name = new Noun("killa").addTranslations(new String[] {"name", "names"});
	public static Word face = new Noun("yawba").addTranslations(new String[] {"face", "faces"});
	public static Word head = new Noun("yawbu").addTranslations(new String[] {"head", "heads"});
	public static Word neck = new Noun("yawbi").addTranslations(new String[] {"neck", "necks"});
	public static Word leg = new Noun("pli").setPlural("pil").addTranslations(new String[] {"leg", "legs"});
	public static Word foot = new Noun("pilla").addTranslations(new String[] {"foot", "feet"});
	public static Word toe = new Noun("pilli").addTranslations(new String[] {"toes", "toe"});
	public static Word arm = new Noun("gru").setPlural("gur").addTranslations(new String[] {"arm", "arms"});
	public static Word hand = new Noun("gurra").addTranslations(new String[] {"hand", "hands"});
	public static Word finger = new Noun("gurri").addTranslations(new String[] {"finger", "fingers"});
	public static Word mouth = new Noun("aa").setPlural("ana").addTranslations(new String[] {"mouth", "mouths"});
	public static Word nose = new Noun("hahti").addTranslations(new String[] {"nose", "noses"});
	public static Word eye = new Noun("uu").setPlural("unu").addTranslations(new String[] {"eye", "eyes"});
	public static Word hair = new Noun("daxki").addTranslations(new String[] {"hair", "hairs"});
	public static Word beard = new Noun("taxki").addTranslations(new String[] {"beard", "beards"});
	public static Word ear = new Noun("ii").setPlural("imi").addTranslations(new String[] {"ear", "ears"});
	public static Word flower = new Noun("alla").setPlural("alal").addTranslations(new String[] {"flower", "flowers"});
	public static Word mountain = new Noun("bayda").addTranslations(new String[] {"mountain", "mountains"});
	public static Word hill = new Noun("biydu").addTranslations(new String[] {"hill", "hills"});
	public static Word rock = new Noun("kanka").addTranslations(new String[] {"rock", "rocks", "stone", "stones"});
	public static Word pebble = new Noun("kinku").addTranslations(new String[] {"pebble", "pebbles"});
	
	public static Word zero = new Noun("nun").setPlural("nun").addTranslations("zero");
	public static Word one = new Noun("kat").setPlural("kat").addTranslations("one");
	public static Word two = new Noun("xuh").setPlural("xuh").addTranslations("two");
	public static Word three = new Noun("dir").setPlural("dir").addTranslations("three");
	public static Word four = new Noun("wadd").setPlural("wadd").addTranslations("four");
	public static Word five = new Noun("muk").setPlural("muk").addTranslations("five");
	public static Word six = new Noun("zin").setPlural("zin").addTranslations("six");
	public static Word seven = new Noun("bint").setPlural("bint").addTranslations("seven");
	public static Word eight = new Noun("traz").setPlural("traz").addTranslations("eight");
	public static Word nine = new Noun("'al").setPlural("'al").addTranslations("nine");
	public static Word ten = new Noun("raab").setPlural("raab").addTranslations("ten");
	public static Word eleven = new Noun("yun").setPlural("yun").addTranslations("eleven");
	public static Word twelve = new Noun("katna").addTranslations("twelve");
	public static Word twentyfour = new Noun("xuhni").addTranslations("twenty-four");
	public static Word thirtysix = new Noun("dirwa").addTranslations("thirty-six");
	public static Word fortyeight = new Noun("wadana").setPlural("wadan").addTranslations("forty-eight");
	public static Word sixty = new Noun("muhin").setPlural("mukkan").addTranslations("sixty");
	public static Word seventytwo = new Noun("zi'nu").addTranslations("seventy-two");
	public static Word eightyfour = new Noun("bitna").addTranslations("eighty-four");
	public static Word ninetysix = new Noun("tran").setPlural("trazan").addTranslations("ninety-six");
	public static Word hundredandeight = new Noun("'alni").addTranslations("one hundred and eight");
	public static Word hundredandtwenty = new Noun("rabna").addTranslations("one hundred and twenty");
	public static Word hundredandthirtytwo = new Noun("yu'un").setPlural("iyun").addTranslations("one hundred and thirty-two");
	public static Word hundredandfortyfour = new Noun("|unta").addTranslations("one hundred and forty-four");

	public static Word bread = new Noun("gunit").setPlural("gunit").addTranslations("bread");
	public static Word water = new Noun("!attu").setPlural("!atud").addTranslations("water");
	public static Word corn = new Noun("mayza").addTranslations(new String[] {"corn", "maize"});
	public static Word milk = new Noun("kadni").addTranslations("milk");
	public static Word chocolate = new Noun("haktu").setPlural("hakut").addTranslations("chocolate");
	public static Word tomato = new Noun("tapli").addTranslations("tomato");
	public static Word boar = new Noun("bratna").setPlural("bartan").addTranslations("boar");
	public static Word pig = new Noun("bridnu").setPlural("birdun").addTranslations("pig");

	public static Word red = new Noun("lit'a").addTranslations("red");
	public static Word orange = new Noun("lut'i").addTranslations("orange");
	public static Word yellow = new Noun("bzantu").setPlural("baznut").addTranslations("yellow");
	public static Word green = new Noun("bzinta").setPlural("biznat").addTranslations("green");
	public static Word blue = new Noun("sawwa").addTranslations("blue");
	public static Word purple = new Noun("siwwi").setPlural("siwu").addTranslations("purple");
	public static Word black = new Noun("tunda").addTranslations("black");
	public static Word white = new Noun("datni").addTranslations("white");
	public static Word gray = new Noun("tidni").addTranslations("gray");

	public static Word wolf = new Noun("Qarti").addTranslations("wolf");
	public static Word dog = new Noun("Qarda").addTranslations("dog");
	public static Word bear = new Noun("zbuni").addTranslations("bear");
	public static Word fox = new Noun("ksini").addTranslations("fox");
	public static Word werseKite = new Noun("pladu").addTranslations("werse-kite");
	public static Word fish = new Noun("ndaru").addTranslations("fish");
	public static Word whale = new Noun("'urma").addTranslations("whale");

	public static Word money = new Noun("xahta").addTranslations("money");
	public static Word limit = new Noun("rdini").addTranslations("limit");
	public static Word oldness = new Noun("tarri").setPlural("tarri").addTranslations("oldness");
	public static Word mistake = new Noun("xuxtu").addTranslations("mistake");
	public static Word greatness = new Noun("drili").setPlural("dirli").addTranslations("greatness");
	public static Word piece = new Noun("klabi").addTranslations(new String[] {"piece", "pieces"});
	
	public static Word to_ = new Preposition("ni").setContractedForm("-n").addTranslations("to");
	public static Word from_ = new Preposition("ra").setContractedForm("-r").addTranslations("from");
	public static Word towards_ = new Preposition("da").setContractedForm("-d").addTranslations(new String[] {"towards", "toward"});
	public static Word of_ = new Preposition("nu").addTranslation("of");
	public static Word under_ = new Preposition("la").addTranslations(new String[] {"under", "below", "underneath"});
	public static Word over_ = new Preposition("lu").addTranslations(new String[] {"over", "above"});
	public static Word with_ = new Preposition("dda").addTranslations("with");
	
	public static Word _that_ = new Article("sa").addTranslations(new String[] {"that", "those"});
	public static Word _this_ = new Article("xi").addTranslations(new String[] {"this", "these"});
	public static Word _yonder_ = new Article("xu").addTranslations(new String[] {"yonder"});
	
	public static Word inv = new Word("_INVALID_").addTranslation("_INVALID_");

	public ArrayList<Word> entries = new ArrayList<Word>();
	
	public Dictionary()
	{
		entries.add(v_be);
		entries.add(v_have);
		entries.add(v_can);
		entries.add(v_own);
		entries.add(v_eat);
		entries.add(v_devour);
		entries.add(v_taste);
		entries.add(v_make);
		entries.add(v_speak);
		entries.add(v_yell);
		entries.add(v_whisper);
		entries.add(v_break);
		entries.add(v_destroy);
		entries.add(v_deconstruct);
		entries.add(v_kill);
		entries.add(v_murder);
		entries.add(v_euthanize);
		entries.add(v_go);
		entries.add(v_walk);
		entries.add(v_run);
		entries.add(v_like);
		entries.add(v_love);
		entries.add(v_favor);
		entries.add(v_want);
		entries.add(v_read);
		entries.add(v_allow);
		entries.add(v_free);
		entries.add(v_let);
		entries.add(v_give);
		entries.add(v_save);
		entries.add(v_guard);
		entries.add(v_keep);
		entries.add(v_jump);
		entries.add(v_leap);
		entries.add(v_hop);
		entries.add(v_listen);
		entries.add(v_hear);
		entries.add(v_eavesdrop);
		entries.add(v_know);
		entries.add(v_understand);
		entries.add(v_meet);
		entries.add(v_look);
		entries.add(v_glance);
		entries.add(v_see);
		entries.add(v_play);
		
		entries.add(_not);
		entries.add(_only);
		entries.add(_always);
		entries.add(_never);
		entries.add(_completely);
		entries.add(_just);
		entries.add(_alot);
		entries.add(_rarely);
		entries.add(_allegedly);
		entries.add(_already);
		
		entries.add(tree);
		entries.add(bush);
		entries.add(god);
		entries.add(i);
		entries.add(you);
		entries.add(secret);
		entries.add(time);
		entries.add(human);
		entries.add(man);
		entries.add(woman);
		entries.add(boy);
		entries.add(girl);
		entries.add(relative);
		entries.add(parent);
		entries.add(father);
		entries.add(mother);
		entries.add(son);
		entries.add(daughter);
		entries.add(sibling);
		entries.add(brother);
		entries.add(sister);
		entries.add(friend);
		entries.add(boyfriend);
		entries.add(girlfriend);
		entries.add(name);
		entries.add(face);
		entries.add(head);
		entries.add(neck);
		entries.add(leg);
		entries.add(foot);
		entries.add(toe);
		entries.add(arm);
		entries.add(hand);
		entries.add(finger);
		entries.add(mouth);
		entries.add(nose);
		entries.add(eye);
		entries.add(ear);
		entries.add(hair);
		entries.add(beard);
		entries.add(flower);
		entries.add(mountain);
		entries.add(hill);
		entries.add(rock);
		entries.add(pebble);
		
		entries.add(one);
		entries.add(two);
		entries.add(three);
		entries.add(four);
		entries.add(five);
		entries.add(six);
		entries.add(seven);
		entries.add(eight);
		entries.add(nine);
		entries.add(ten);
		entries.add(twelve);
		entries.add(twentyfour);
		entries.add(thirtysix);
		entries.add(fortyeight);
		entries.add(sixty);
		entries.add(seventytwo);
		entries.add(eightyfour);
		entries.add(ninetysix);
		entries.add(hundredandeight);
		entries.add(hundredandtwenty);
		entries.add(hundredandthirtytwo);
		entries.add(hundredandfortyfour);
		
		entries.add(bread);
		entries.add(water);
		entries.add(corn);
		entries.add(milk);
		entries.add(chocolate);
		entries.add(tomato);
		entries.add(boar);
		entries.add(pig);
		
		entries.add(red);
		entries.add(orange);
		entries.add(yellow);
		entries.add(green);
		entries.add(blue);
		entries.add(purple);
		entries.add(black);
		entries.add(white);
		entries.add(gray);
		
		entries.add(wolf);
		entries.add(dog);
		entries.add(bear);
		entries.add(fox);
		entries.add(werseKite);
		entries.add(fish);
		entries.add(whale);
		
		entries.add(money);
		entries.add(limit);
		entries.add(oldness);
		entries.add(mistake);
		entries.add(greatness);
		entries.add(piece);
		
		entries.add(to_);
		entries.add(towards_);
		entries.add(from_);
		entries.add(of_);
		entries.add(over_);
		entries.add(under_);
		entries.add(with_);
		
		entries.add(_this_);
		entries.add(_that_);
		entries.add(_yonder_);
		
		entries.add(inv);
	}
	
	public Word lookup(String string)
	{
		for (int i = 0; i < entries.size(); i++)
		{
			if (entries.get(i) instanceof Noun)
			{
				for (int j = 0; j < ((Noun) entries.get(i)).english.size(); j++)
				{
					if (string.equals(((Noun) entries.get(i)).english.get(j)))
					{
						return (Noun) entries.get(i);
					}
				}
			}
			else if (entries.get(i) instanceof Verb)
			{
				for (int j = 0; j < ((Verb) entries.get(i)).english.size(); j++)
				{
					if (string.equals(((Verb) entries.get(i)).english.get(j)))
					{
						return (Verb) entries.get(i);
					}
				}
			}
			else if (entries.get(i) instanceof Adverb)
			{
				for (int j = 0; j < ((Adverb) entries.get(i)).english.size(); j++)
				{
					if (string.equals(((Adverb) entries.get(i)).english.get(j)))
					{
						return (Adverb) entries.get(i);
					}
				}
			}
			else if (entries.get(i) instanceof Preposition)
			{
				for (int j = 0; j < ((Preposition) entries.get(i)).english.size(); j++)
				{
					if (string.equals(((Preposition) entries.get(i)).english.get(j)))
					{
						return (Preposition) entries.get(i);
					}
				}
			}
			else if (entries.get(i) instanceof Conjunction)
			{
				for (int j = 0; j < ((Conjunction) entries.get(i)).english.size(); j++)
				{
					if (string.equals(((Conjunction) entries.get(i)).english.get(j)))
					{
						return (Conjunction) entries.get(i);
					}
				}
			}
			else if (entries.get(i) instanceof Article)
			{
				for (int j = 0; j < ((Article) entries.get(i)).english.size(); j++)
				{
					if (string.equals(((Article) entries.get(i)).english.get(j)))
					{
						return (Article) entries.get(i);
					}
				}
			}
		}
		return inv;
	}
}
