package aca.ciphers;

import java.util.ArrayList;
import java.util.HashMap;
//import java.util.Random;

import aca.util.Pair;

public class Playfair implements Cipher {
	
	public Playfair()
	{
		key="LOGARITHM";
	}
	
	public Playfair(String k)
	{
		key=k;
	}
	
	private String key;
	
	public String encode(String plain)
	 {
		char[][] square=new char[5][5];
		Two_square.build_square(square, key);
		HashMap<Character,Pair<Integer>> h_map=BIFID.build_map(square);
	    String plain_u=plain.toUpperCase();
	    StringBuilder sb=new StringBuilder();
	    if(plain_u.length()%2!=0)
	    {
	    	plain_u+="X";
	    }
	    for(int i=0;i<plain_u.length();i+=2)
	    {
	    	sb.append(get_sub_ct(plain_u.substring(i, i+2),h_map,square));
	    }
		return sb.toString();
	 }
	
	protected String get_sub_ct(String digraph, HashMap<Character,Pair<Integer>> map, char[][] square)
	{
		assert(digraph.length()==2);
		char c1=digraph.charAt(0);
		if(c1=='J')
		{
			c1='I';
		}
		char c2=digraph.charAt(1);
		if(c2=='J')
		{
			c2='I';
		}
		StringBuilder sb=new StringBuilder();
		Pair<Integer> rc1=map.get(Character.valueOf(c1));
		Pair<Integer> rc2=map.get(Character.valueOf(c2));
		if(rc1.get_first()==rc2.get_first())//same row
		{
			if(rc1.get_second()==square[0].length-1)
			{
				sb.append(square[rc1.get_first()][0]);
			}
			else
			{
				sb.append(square[rc1.get_first()][rc1.get_second()+1]);
			}
			if(rc2.get_second()==square[0].length-1)
			{
				sb.append(square[rc2.get_first()][0]);
			}
			else
			{
				sb.append(square[rc2.get_first()][rc2.get_second()+1]);
			}
			
		}
		else if(rc1.get_second()==rc2.get_second())
		{
			if(rc1.get_first()==square.length-1)
			{
				sb.append(square[0][rc1.get_second()]);
			}
			else
			{
				sb.append(square[rc1.get_first()+1][rc1.get_second()]);
			}
			if(rc2.get_first()==square.length-1)
			{
				sb.append(square[0][rc2.get_second()]);
			}
			else
			{
				sb.append(square[rc2.get_first()+1][rc2.get_second()]);
			}
		}
		else
		{
			sb.append(square[rc1.get_first()][rc2.get_second()]);
			sb.append(square[rc2.get_first()][rc1.get_second()]);
			
		}
		return sb.toString();
	}
	    
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
	    	return 1;
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
