package aca.ciphers;

import java.util.ArrayList;
//import java.util.Random;
import java.util.Hashtable;

//import aca.util.Generic_Func;

public class Morbit implements Cipher {
	
	public Morbit()
	{
		key="WISECRACK";
	}
	
	public Morbit(String k)
	{
		key=k;
	}
	
	private String key;
	
	public String encode(String plain)
	 {
		Morse_code m=new Morse_code();
		String plain_u=plain.toUpperCase();
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<plain_u.length();i++)
		{
			if(plain_u.charAt(i)==' ')
			{
				sb.append('x');
			}
			else
			{
			  sb.append(m.get_morse_code(plain_u.charAt(i)));
			  sb.append('x');
			}
			
		}
		String mc=sb.toString();
		if(mc.length()%2!=0)
		{
			mc+="x";
		}
		int[] k_in=Cadenus.generate_order(key);
		Hashtable<String,Integer> h_map=new Hashtable<String,Integer>();
		for(int i=0;i<9;i++)
		{
			String cur_s;
			if(i/3==0)
			{
			   cur_s=".";
			}
			else if(i/3==1)
			{
				cur_s="-";
			}
			else
			{
				cur_s="x";
			}
			if(i%3==0)
			{
				cur_s+=".";
			}
			else if(i%3==1)
			{
				cur_s+="-";
			}
			else
			{
				cur_s+="x";
			}
			h_map.put(cur_s, k_in[i]);
		}
		StringBuilder sb2=new StringBuilder();
		for(int j=0;j<mc.length();j+=2)
		{
			String sub_s=mc.substring(j,j+2);
			sb2.append(h_map.get(sub_s));
		}	
		return sb2.toString();
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
	    	ArrayList<Integer> result=new ArrayList<Integer>();
	    	result.add(9);
	    	return result;
	    }
	    
	    public int process_id()
		{
			return 2;
		}
}
