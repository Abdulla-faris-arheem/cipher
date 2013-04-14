package aca.ciphers;

import java.util.ArrayList;
import java.util.Random;
import java.util.HashMap;

import aca.util.Generic_Func;

public class Pollux implements Cipher {
	
	public Pollux()
	{
		generate_digit_table();
		build_map();
	}
	
	public Pollux(char[] array)
	{
		assert(array.length==10);
		for(int i=0;i<10;i++)
		{
			digit_table[i]=array[i];
		}
		build_map();
	}
	
	private void generate_digit_table()
	{
		int[] perm=Generic_Func.generate_random_perm(0, 10);
		for(int i=0;i<perm.length;i++)
		{
			if(i<4)
			{
				digit_table[perm[i]]='.';
			}
			else if(i<7)
			{
				digit_table[perm[i]]='x';
			}
			else
			{
				digit_table[perm[i]]='-';
			}
		}
	}
	
	private void build_map()
	{
		for(int i=0;i<digit_table.length;i++)
		{
			Character cur_c=Character.valueOf(digit_table[i]);
			if(map.containsKey(cur_c))
			{
				ArrayList<Integer> cur_l=map.get(cur_c);
				cur_l.add((i+1)%10);
				map.put(cur_c,cur_l);
			}
			else
			{
				ArrayList<Integer> cur_l=new ArrayList<Integer>();
				cur_l.add((i+1)%10);
				map.put(cur_c,cur_l);
			}
			
		}
	}
	
	private char[] digit_table=new char[10];
	private HashMap<Character,ArrayList<Integer>> map=new HashMap<Character,ArrayList<Integer>>();
	
	public String encode(String plain)
	 {
		String plain_u=plain.toUpperCase();
		StringBuilder sb=new StringBuilder();
		Morse_code m=new Morse_code();
		for(int i=0;i<plain_u.length();i++)
		{
			if(plain_u.charAt(i)!=' ')
			{
			   sb.append(m.get_morse_code(plain_u.charAt(i)));
			   sb.append('x');
			}
			else
			{
			   sb.append('x');
			}
		}
		String morse_c=sb.toString();
		StringBuilder sb2=new StringBuilder();
		for(int i=0;i<morse_c.length();i++)
		{
			sb2.append(get_ct(morse_c.charAt(i),map));
		}
		return sb2.toString();
	 }
	
	private int get_ct(char c,HashMap<Character,ArrayList<Integer>> a)
	{
		ArrayList<Integer> l=a.get(Character.valueOf(c));
		Random r=new Random();
		int select=r.nextInt(l.size());
		return l.get(select);
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
	    	return false;
	    }
	    
	    public int get_key_num()
	    {
	    	return 0;
	    }
	    
	    public ArrayList<Integer> get_key_len()
	    {
	    	return null;
	    }
}
