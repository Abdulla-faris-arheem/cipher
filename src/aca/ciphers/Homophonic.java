package aca.ciphers;

import java.util.ArrayList;
import java.util.Random;

public class Homophonic implements Cipher {
	
	
	public Homophonic()
	{
		key="GOLF";
	}
	
	public Homophonic(String k)
	{
		key=k;
	}
	
	private String key;
	
	public String encode(String plain)
	 {
		String key_u=key.toUpperCase();
		String[][] code_matrix=new String[4][25];
		for(int i=0;i<4;i++)
		{
			char cur_c=key_u.charAt(i);
			if(cur_c=='J'){
				cur_c='I';
			}
			int pos=cur_c-'A';
			if(pos>8)//>'J'
			{
				pos--;
			}
			//int cur_num=25*i+1;
			for(int j=0;j<25;j++)
			{
				int cur_num=25*i+j+1;
				String cur_str;
				if(cur_num==100)
				{
					cur_str="00";
				}
				else
				{
					cur_str=(cur_num<10?"0":"")+cur_num;
				}
				code_matrix[i][pos]=cur_str;
				if(pos==24)
				{
					pos=0;
				}
				else
				{
					pos++;
				}
			}
			
		}
		String cipher_text=get_c_text(code_matrix,plain);
		return cipher_text;
	 }
	
	private String get_c_text(String[][] code_matrix, String plain)
	{
		StringBuilder sb=new StringBuilder();
		String plain_u=plain.toUpperCase();
		for(int i=0;i<plain_u.length();i++)
		{
			int pos=plain_u.charAt(i)-'A';
			if(pos>8)
			{
				pos--;
			}
			Random r=new Random();
			int num=r.nextInt(4);
			sb.append(code_matrix[num][pos]);
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
	    	ArrayList<Integer> result=new ArrayList<Integer>();
	    	result.add(4);
	    	return result;
	    }
}
