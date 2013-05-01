package aca.ciphers;

import java.util.ArrayList;
import java.util.Random;
import java.util.HashMap;
import java.io.*;

public class Null implements Cipher {
	//25-letter plaintext maximum
	public Null()
	{
		
	}
	
	public Null(ArrayList<String> words)
	{
		build_word_map(words);
	}
	
	private HashMap<Character,ArrayList<String>> word_map=new HashMap<Character,ArrayList<String>>();
	
	public String encode(String plain)
	 {
		if(word_map.size()==0)
		{
			System.err.println("No word list to select");
			return null;
		}
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<plain.length();i++)
		{
			String cipher_word=get_word(plain.charAt(i));
			sb.append(cipher_word.toUpperCase());
			sb.append(" ");
		}
		return sb.toString().trim();
	 }
	
	private String get_word(char c)
	{
		char c_u=Character.toUpperCase(c);
		Random r=new Random();
		if(!word_map.containsKey(c_u))
		{
			System.err.println("No proper cipher word found");
			return null;
		}
		else
		{
			ArrayList<String> candidates=word_map.get(Character.valueOf(c_u));
			int select=r.nextInt(candidates.size());
			return candidates.get(select);
		}
	}
	
	public void add_word_map(Character b,String word)
	{
		Character b_u=Character.toUpperCase(b);
	    if(word_map.containsKey(b_u))
	    {
	    	ArrayList<String> cur_words=word_map.get(b_u);
	    	cur_words.add(word);
	    	word_map.put(b_u, cur_words);
	    }
	    else
	    {
	    	ArrayList<String> cur_words=new ArrayList<String>();
	    	cur_words.add(word);
	    	word_map.put(b_u, cur_words);
	    }
	}
	
	public void build_word_map(ArrayList<String> word_list)
	{
		for(int i=0;i<word_list.size();i++)
		{
			String word=word_list.get(i);
			int len=word.length();
			if(len%2==0)
			{
				continue;
			}
			char c=word.charAt(len/2);
			add_word_map(Character.valueOf(c),word);
		}
	}
	
	@SuppressWarnings("resource")
	public void build_word_map2(String words_file)
	{
		try{
			BufferedReader bf=new BufferedReader(new FileReader(words_file));
			String line;
			while((line=bf.readLine())!=null)
			{
				int len=line.trim().length();
				if(len%2==0)
				{
					continue;
				}
				char c=line.trim().charAt(len/2);
				add_word_map(Character.valueOf(c),line.trim());
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();		
	    }
	}
	
	/*public String get_word(char b)
	{
		
	}*/
	    
	    /*
	     * Decode the cipher text.
	     */
	    public String decode(String cipher)
	    {
	    	return null;
	    }
	    
	    public boolean key_need()
	    {
	    	return true;
	    }
	    
	    public int get_key_num()
	    {
	    	return 2;
	    }
	    
	    public ArrayList<Integer> get_key_len()
	    {
	    	return null;
	    }
	    
	    public int process_id()
		{
			return 2;
		}
}
