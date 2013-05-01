package aca.ciphers;

import java.util.ArrayList;
//import java.util.Random;

import aca.util.Generic_Func;

public class Quagmire_I implements Cipher {
	
	public Quagmire_I()
	{
		h_key="SPRINGFEV";
		v_key="FLOWER";
	}
	
	public Quagmire_I(ArrayList<String> keys)
	{
		assert(keys.size()>=2);
		h_key=keys.get(0);
		v_key=keys.get(1);
	}
	
	private String h_key;//key for the horizontal 
	private String v_key;
	
	public String encode(String plain)
	 {
		int period=v_key.length();
		char[] pt=build_keyed_alphabet(h_key,26,0);
		int a_pos=Generic_Func.find_char(pt, 'A');
		char[][] ct_table=build_ct(v_key,a_pos);
		char[][] pt_block=Incomp_column.build_block(plain, -1,period, 0);
		StringBuilder sb=new StringBuilder();
		
		for(int i=0;i<pt_block.length;i++)
		{
			for(int j=0;j<pt_block[0].length;j++)
			{
				if(pt_block[i][j]!='\0')
				{
				  sb.append(get_cipher_char(pt, pt_block[i][j],j,ct_table));
				}
			}
		}
		return sb.toString();
	 }
	
	protected char get_cipher_char(char[] pt,char p,int row, char[][] ct_table)
	{
		int col=Generic_Func.find_char(pt, p);
		return ct_table[row][col];
	}
	
	protected char[][] build_ct(String v_key,int pos)
	{
		int row=v_key.length();
		char[][] result=new char[row][26];
		for(int i=0;i<row;i++)
		{
			result[i][pos]=v_key.charAt(i);
		}
		for(int i=0;i<row;i++)
		{
		 for(int j=1;j<26;j++)
		 {
			int cur_pos=pos+j;
			if(cur_pos>=26)
			{
				cur_pos-=26;
			}
			char cur_c=(char)(result[i][pos]+j);
			if(cur_c>'Z')
			{
				cur_c=(char)(cur_c-26);
			}
			result[i][cur_pos]=cur_c;
		 }
		}
		return result;
	}
	
	
	
	protected char[] build_keyed_alphabet(String k,int total,int offset)
	{
		char[] result=new char[total];
		String k_u=k.toUpperCase();
		boolean[] filled=new boolean[total];
		int cur_pos=0;
		for(int i=0;i<k_u.length();i++)
		{
			char cur_c=k_u.charAt(i);
			int pos=cur_c-'A';
			if(filled[pos])
				continue;
			filled[cur_c-'A']=true;
			result[cur_pos]=cur_c;
			cur_pos++;
		}
		for(int i=0;i<total;i++)
		{
			char cur_c=(char)('A'+i);
			if(filled[i])
				continue;
			result[cur_pos]=cur_c;
			filled[i]=true;
			cur_pos++;
		}
		//move by offset
		if(offset!=0)
		{
		 for(int i=0;i<result.length;i++)
		 {
			char temp=result[(i+offset)%total];
			result[(i+offset)%26]=result[i];
			result[i]=temp;
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
