package aca.ciphers;

import java.util.ArrayList;
import java.util.Random;

import aca.util.Generic_Func;

public class Headlines implements Cipher {
	
	public Headlines()
	{
		hat="APOTHECARY";
		key="CHEMIST";
		setting="DRUGS";
	}
	
	public Headlines(ArrayList<String> keys)
	{
		assert(keys.size()>3);
		hat=keys.get(0);
		key=keys.get(1);
		setting=keys.get(2);
	}
	
	private String hat;
	private String key;
	private String setting;
	
	public String encode(String plain)
	 {
		int[] key_block=Cadenus.generate_order(hat);
		char[] key_alphabet=build_keyed_alphabet(key);
		char[][] sub_block=build_sub_block(setting, key_alphabet,key_block);
		Random r=new Random();
		//int cipher_id=4;
		int cipher_id=r.nextInt(setting.length());
		String plain_u=plain.toUpperCase();
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<plain_u.length();i++)
		{
			sb.append(headline_cipher(sub_block,plain_u.charAt(i),cipher_id));
		}
		//Random r=new Random();
		//int key_len=1+r.nextInt(7);
		
		return sb.toString();
	 }
	
	private char headline_cipher(char[][] sub_block,char p,int cipher_id)
	{
		int pos=Generic_Func.find_char(sub_block[0], p);
		return sub_block[cipher_id+1][pos];
	}
	
	private char[][] build_sub_block(String setting, char[] key_alphabet, int[] key_block)
	{
		int set_len=setting.length();
		char[][] result=new char[set_len+1][26];
		char[] pt=read_pt(key_alphabet,key_block);
		for(int i=0;i<26;i++)
		{
			result[0][i]=pt[i];
		}
		//create ct: set_len*26
		String setting_u=setting.toUpperCase();
		for(int i=0;i<set_len;i++)
		{
			int pos=Generic_Func.find_char(pt,setting_u.charAt(i));
			int filled=0;
			int pt_col=0;
			while(filled<26)
			{
				result[i+1][pt_col]=pt[pos];
				if(pos==25)
				{
					pos=0;
				}
				else
				{
					pos++;
				}
				pt_col++;
				filled++;
			}
		}
		return result;
		
		
		
	}
	
	private char[] read_pt(char[] key_alphabet, int[] key_block)
	{
		//int cur_col=1;
		StringBuilder sb=new StringBuilder();
		for(int i=1;i<=key_block.length;i++)
		{
			int pos=Generic_Func.search_index(key_block, i,0);
			int cur_pos=pos;
			while(cur_pos<26)
			{
				sb.append(key_alphabet[cur_pos]);
				cur_pos+=key_block.length;
			}
		}
		return sb.toString().toCharArray();
		
		
	}
	
	public static char[] build_keyed_alphabet(String k)
	{
		char[] result=new char[26];
		String k_u=k.toUpperCase();
		boolean[] filled=new boolean[26];
		int pos=0;
		for(int i=0;i<k_u.length();i++)
		{
			result[pos]=k_u.charAt(i);
			filled[k_u.charAt(i)-'A']=true;
			pos++;
		}
		for(int i=0;i<26;i++)
		{
			if(!filled[i])
			{
				char cur_c=(char)('A'+i);
				result[pos]=cur_c;
				pos++;
				filled[i]=true;
			}
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
	    	return 3;
	    }
	    
	    public ArrayList<Integer> get_key_len()
	    {
	    //	ArrayList<>
	    	return null;
	    }
}
