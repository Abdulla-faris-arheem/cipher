package aca.util;

public class Preprocessor {
	
	/*
	 * Remove all non-alphabetic character. Lowercase everything.
	 */
	public String keep_alphabe(String s)
	{
		StringBuilder b=new StringBuilder();
		for(char a:s.toCharArray())
		{
			if(Character.isLetter(a))
			{
				b.append(a);
			}
		}
		return b.toString().toLowerCase();
	}
	
	/*
	 * Leave only 'a'-'z'. All lowercased.
	 */
	public String get_plain_no_blank(String s)
	{
		String low_s=s.toLowerCase();
		StringBuilder b=new StringBuilder();
		for(char a:low_s.toCharArray())
		{
			if(a>='a' && a<='z')
			{
				b.append(a);
			}
		}
		return b.toString();
	}
	
	public String get_plain_with_blank(String s)
	{
		String low_s=s.toLowerCase();
		StringBuilder b=new StringBuilder();
		for(char a:low_s.toCharArray())
		{
			if(a==' ' || (a>='a' && a<='z'))
			{
				b.append(a);
			}
		}
		return b.toString();
	}

}
