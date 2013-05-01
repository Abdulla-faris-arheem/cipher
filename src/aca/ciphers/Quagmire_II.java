package aca.ciphers;

import java.util.ArrayList;
//import java.util.Random;

import aca.util.Generic_Func;

public class Quagmire_II extends Quagmire_I {
	
	public Quagmire_II()
	{
		h_key="SPRINGFEV";
		v_key="FLOWER";
	}
	
	public Quagmire_II(ArrayList<String> keys)
	{
		assert(keys.size()>=2);
		h_key=keys.get(0);
		v_key=keys.get(1);
	}
	
	private String h_key;//key for the horizontal 
	private String v_key;
	
	
	public String encode(String plain)
	 {
		String v_key_u=v_key.toUpperCase();
	//	String h_key_u=h_key.toUpperCase();
		char[][] ct_table=new char[v_key_u.length()][26];
		for(int i=0;i<v_key_u.length();i++)
		{
	//	  int mv_pos=0;
		  char first_c=v_key_u.charAt(i);
		  char[] ct_line=build_keyed_alphabet(h_key,26,0);
		  int pos=Generic_Func.find_char(ct_line, first_c);
		  char[] new_ct=move_array(ct_line,pos,true);
		  for(int j=0;j<26;j++)
		  {
			  ct_table[i][j]=new_ct[j];
		  }
		  
		}
		char[] pt=new char[26];
		for(int i=0;i<26;i++)
		{
			pt[i]=(char)('A'+i);
		}
		char[][] pt_block=Incomp_column.build_block(plain, -1, v_key.length(), 0);
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
	
	/**
	 * Move the array so the first_char is in the first position
	 * 
	 * @param array array 
	 * @param first_char the char we want in the first position
	 */
	/*private void adjust_array(char[] array, char first_char)
	{
		int pos=Generic_Func.find_char(array, first_char);
		move_array(array,pos,true);
	}*/
	
	/**
	 * Move the array
	 * 
	 * @param array the array to move
	 * @param offset position moved
	 * @param left left is true, right is false 
	 */
	protected char[] move_array(char[] array,int offset,boolean left)
	{
		int total=array.length;
		char[] new_array=new char[total];
		for(int i=0;i<total;i++)
		{
			int new_pos;
			if(!left)
			{
				new_pos=(i+offset)%total;
			}
			else
			{
				new_pos=(i-offset)%total;
				if(new_pos<0)
				{
					new_pos+=total;
				}
			}
			new_array[new_pos]=array[i];
		}
		return new_array;
	/*	 for(int i=0;i<total;i++)
		 {
			if(!left)
			{
			  char temp=array[(i+offset)%total];
			  array[(i+offset)%total]=array[i];
			  array[i]=temp;
			}
			else
			{
				 char temp=array[(i-offset+total)%total];
				  array[(i-offset+total)%total]=array[i];
				  array[i]=temp;
			}
		 }*/
		
	}
	
	
	
//	private void 
	    
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
