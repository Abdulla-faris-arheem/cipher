package aca.ciphers;

import java.util.ArrayList;
//import java.util.Random;

public class Slidefair implements Cipher {
	public Slidefair()
	{
		key="DIGRAPH";
	}
	
	public Slidefair(String k)
	{
		key=k.toUpperCase();
	}
	
	private String key;
	
	public String encode(String plain)
	 {
		int period=key.length();
		String plain_u=plain.toUpperCase();
		if(plain_u.length()%2!=0)
		{
			plain_u+="X";
		}
		char[][] block=Incomp_column.build_block(plain_u, period*2, 0);
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<block.length;i++)
		{
			for(int j=0;j<block[0].length;j+=2)
			{
				if(block[i][j]=='\0')
				{
					break;
				}
				sb.append(get_pair_ct(block[i][j],block[i][j+1],key.charAt(j/2)));
			}
		}
		
		return sb.toString();
	 }
	
	private String get_pair_ct(char p1,char p2,char k_char)
	//currently only supports vigenere cipher
	{
		StringBuilder sb=new StringBuilder();
		int c1_pos=p1-'A';
		int c2_pos=p2-k_char;
		if(c2_pos<0)
		{
			c2_pos+=26;
		}
		if(c1_pos==c2_pos)
		{
			if(c1_pos==25)
			{
				sb.append('A');
				sb.append(k_char);
			}
			else
			{
				sb.append((char)(p1+1));
				sb.append((char)(p2+1));
			}
		}
		else
		{
			
			sb.append((char)('A'+c2_pos));
			
			int r_c2_pos=(c1_pos+(k_char-'A'))%26;
			sb.append((char)('A'+r_c2_pos));
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
}
