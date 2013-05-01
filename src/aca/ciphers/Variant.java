package aca.ciphers;

import java.util.ArrayList;

public class Variant implements Cipher {
	
	public Variant()
	{
		
	}
	
	public Variant(String k)
	{
		key=k;
	}
	
	
	private String key="APPLE";
	
	public boolean key_need()
	  {
		    return need_key;
	 }
		    
		 public int get_key_num()
		 {
		    return 1;
		 }
		    
		 public ArrayList<Integer> get_key_len()
		 {
			 return null;
		 }
	
	
	
	private char look_up_table(char[][] table, char p_c,char k_c)
	{
		//to be modified
		int col=(int)(p_c-'a');
		int row=0;
		if(k_c!='A')
		{
		  row=25-(int)(k_c-'B');
		}
		return table[row][col];
	}
	
	 public String encode(String plain)
     {
		 //to be checked
		 int key_len=key.length();
		 int cur_index=0;
		 int plain_size=plain.length();
		 int key_index=0;
		 StringBuilder s=new StringBuilder();
		 while(cur_index<plain_size)
		 {
			s.append(look_up_table(Variant_table.variant,plain.charAt(cur_index),this.key.charAt(key_index)));
			if(key_index==key_len-1)
			{
				key_index=0;
			}
			else
			{
				key_index++;
			}
				cur_index++;
			}
			return s.toString();
     }
    
    /*
     * Decode the cipher text.
     */
    public String decode(String cipher)
    {
    	return null;
    }
    
    public static boolean need_key=true; //need to generate a string key
    
    public static int key_num=1;  
    
    public int process_id()
	{
		return 2;
	}
}
