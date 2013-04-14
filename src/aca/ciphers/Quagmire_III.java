package aca.ciphers;

import java.util.ArrayList;
//import java.util.Random;

import aca.util.Generic_Func;

public class Quagmire_III extends Quagmire_II {
	public Quagmire_III()
	{
		h_key="AUTOMOBILE";
		v_key="HIGHWAY";
	}
	
	public Quagmire_III(ArrayList<String> keys)
	{
		assert(keys.size()>=2);
		h_key=keys.get(0);
		v_key=keys.get(1);
	}
	
	private String h_key;//key for the horizontal 
	private String v_key;
	
	public String encode(String plain)
	 {
		char[] pt=build_keyed_alphabet(h_key,26,0);
		int period=v_key.length();
		String v_key_u=v_key.toUpperCase();
		char[][] ct_table=new char[period][26];
		for(int i=0;i<period;i++)
		{
			  char first_c=v_key_u.charAt(i);
			  char[] ct_line=build_keyed_alphabet(h_key,26,0);
			  int pos=Generic_Func.find_char(ct_line, first_c);
			  char[] new_ct=move_array(ct_line,pos,true);
			  for(int j=0;j<26;j++)
			  {
				  ct_table[i][j]=new_ct[j];
			  }
		}
		char[][] pt_block=Incomp_column.build_block(plain, v_key.length(), 0);
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
}
