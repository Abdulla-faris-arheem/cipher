package aca.ciphers;

import java.util.ArrayList;
import java.util.HashMap;

import aca.util.Pair;

public class Two_square implements Cipher {
	
	//private String key1="DIALOGUE";
	
	//private String key2="BIOGRAPHY";
	
	public Two_square()
	{
		String key1="DIALOGUE";
		String key2="BIOGRAPHY";
		keys.add(key1);
		keys.add(key2);
		build_square(square1,key1);
		build_square(square2,key2);
	}
	
	public boolean key_need()
	  {
		    return need_key;
	 }
		    
		 public int get_key_num()
		 {
		    return 2;
		 }
		    
		 public ArrayList<Integer> get_key_len()
		 {
			 return null;
		 }
	
	/**
	 * Build a 5*5 Polybius square using a keyword. The letters are written in horizontal order.
	 * I/J is combined.
	 * 
	 * @param square the polybius square which will be filled
	 * @param key the keyword
	 */
	public static void build_square(char[][] square, String key)
	{
		boolean[] filled=new boolean[26];
		String key_u=key.toUpperCase();
		int cur_row=0;
		int cur_col=0;
		for(int i=0;i<key.length();i++)
		{
			char cur_c=key_u.charAt(i);
			if(cur_c=='J')
			{
				cur_c='I';
			}
			int pos=cur_c-'A';
			if(!filled[pos])
			{
				square[cur_row][cur_col]=cur_c;
				filled[pos]=true;
			}
			else
			{
				continue;
			}
			if(cur_col==4)
			{
				cur_row++;
				cur_col=0;
			}
			else
			{
				cur_col++;
			}
			
		}
		for(int i=0;i<26;i++)
		{
			if(i==9)
			{
				continue;
			}
			if(!filled[i])
			{
				square[cur_row][cur_col]=(char)('A'+i);
				filled[i]=true;
			}
			else
			{
				continue;
			}
			if(cur_col==4)
			{
				cur_row++;
				cur_col=0;
			}
			else
			{
				cur_col++;
			}
		}
	}
	
	
	
	private ArrayList<String> keys=new ArrayList<String>();
	private char[][] square1=new char[5][5];
	private char[][] square2=new char[5][5];
	
	public Two_square(ArrayList<String> keys)
	{
		if(keys.size()<2)
		{
			System.err.println("Not enough keys");
			return;
		}
		for(int i=0;i<keys.size();i++)
		{
			this.keys.add(keys.get(i));
		}
		String key1=keys.get(0);
		String key2=keys.get(1);
		build_square(square1,key1);
		build_square(square2,key2);
	}
	
	 public String encode(String plain)
     {
		String p=plain.toUpperCase();
		if(plain.length()%2==1)
		{
			p+="X";
		}
		HashMap<Character,Pair<Integer>> h1=BIFID.build_map(square1);
		HashMap<Character,Pair<Integer>> h2=BIFID.build_map(square2);
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<p.length();i+=2)
		{
			sb.append(cipher_digraph(p.substring(i, i+2),h1,h2));
		}
    	return sb.toString(); 
     }
	 
	 private String cipher_digraph(String pl,HashMap<Character,Pair<Integer> > h1, HashMap<Character,Pair<Integer>> h2)
	 {
		 assert(pl.length()==2);
		 StringBuilder sb=new StringBuilder();
		 char c1=pl.charAt(0);
		 char c2=pl.charAt(1);
		 if(c1=='J')
		 {
			 c1='I';
		 }
		 if(c2=='J')
		 {
			 c2='I';
		 }
		 if(!h1.containsKey(Character.valueOf(c1)) || !h2.containsKey(Character.valueOf(c2)))
		 {
			 System.err.println("Error in finding keys in hashmap");
		 }
		 Pair<Integer> p1=h1.get(Character.valueOf(c1));
		 Pair<Integer> p2=h2.get(Character.valueOf(c2));
		 
		 if(p1.get_first()==p2.get_first())
		 {
			 sb.append(c2);
			 sb.append(c1);
			 return sb.toString();
		 }
		 else
		 {
			 sb.append(square2[p1.get_first()][p2.get_second()]);
			 sb.append(square1[p2.get_first()][p1.get_second()]);
			 return sb.toString();
		 }
	}
    
    /*
     * Decode the cipher text.
     */
    public String decode(String cipher)
    {
    	return null;
    }
    
    public boolean need_key=true; //need to generate a string key
    
    public static int key_num=2;   
    
    public int process_id()
	{
		return 2;
	}

}
