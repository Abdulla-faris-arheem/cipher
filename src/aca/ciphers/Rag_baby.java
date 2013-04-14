package aca.ciphers;

import java.util.ArrayList;
//import java.util.Random;

import aca.util.Generic_Func;

public class Rag_baby implements Cipher {
	
	public Rag_baby()
	{
	  key="GROSBEAK";	
	}
	
	public Rag_baby(String k)
	{
		key=k;
	}
	
	private String key;
	
	public String encode(String plain)
	 {
		char[] alphabet=build_key_alphabet(key);
		//Random r=new Random();
		//int key_len=1+r.nextInt(7);
		int word_start=1;
		int cur_num=word_start;
		int[] num_k=new int[plain.length()];
		String plain_u=plain.toUpperCase();
		for(int i=0;i<plain_u.length();i++)
		{
			char cur_c=plain_u.charAt(i);
			if(cur_c==' ')
			{
				if(word_start<24)
				{
				  word_start++;
				}
				else
				{
					word_start=1;
				}
				cur_num=word_start;
			}
			else if(cur_c>='A' && cur_c<='Z')
			{
				num_k[i]=cur_num;
				if(cur_num<24)
				{
				 cur_num++;
				}
				else
				{
					cur_num=word_start;
				}
			}
			
		}
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<plain_u.length();i++)
		{
			char cur_c=plain_u.charAt(i);
			if(cur_c>='A' && cur_c<='Z')
			{
				if(cur_c=='J')
				{
					cur_c='I';
				}
				if(cur_c=='X')
				{
					cur_c='W';
				}
				int pos=Generic_Func.find_char(alphabet, cur_c);
				int ct_pos=(pos+num_k[i])%24;
				sb.append(alphabet[ct_pos]);
			}
		}
		return sb.toString();
	 }
	
	private char[] build_key_alphabet(String k)
	{
		//I/J and W/X combined
		char[] result=new char[24];
		String k_u=k.toUpperCase();
		boolean[] filled=new boolean[26];
		int cur_pos=0;
		for(int i=0;i<k_u.length();i++)
		{
			char cur_c=k_u.charAt(i);
			if(cur_c=='J')
			{
				cur_c='I';
			}
			if(cur_c=='W')
			{
				cur_c='X';
			}
			int pos=cur_c-'A';
			if(filled[pos])
				continue;
			filled[cur_c-'A']=true;
			result[cur_pos]=cur_c;
			cur_pos++;
		}
		for(int i=0;i<26;i++)
		{
			char cur_c=(char)('A'+i);
			if(cur_c=='J' || cur_c=='X')
				continue;
			if(filled[i])
				continue;
			result[cur_pos]=cur_c;
			filled[i]=true;
			cur_pos++;
		}
		return result;
		
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
	    
	    public boolean include_space()
	    {
	    	return true;
	    }
}
