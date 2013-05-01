package aca.ciphers;

import java.util.ArrayList;
import java.util.Random;

//import aca.util.Generic_Func;

public class Railfence implements Cipher {
	
	public Railfence()
	{
		Random r=new Random();
		row_num=2+r.nextInt(5);
	}
	
	public Railfence(int r)
	{
		row_num=r;
	}
	
	private int row_num;
	
	public String encode(String plain)
	 {
		char[][] zigzag_block=Redefence.build_zigzag_block(plain, row_num);
		String ct=read_zigzag(zigzag_block);
		
		return ct.toUpperCase();
	 }
	
	private String read_zigzag(char[][] block)
	{
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<block.length;i++)
		{
			for(int j=0;j<block[0].length;j++)
			{
				if(block[i][j]!='\0')
					sb.append(block[i][j]);
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
	    
	    public int process_id()
		{
			return 0;
		}
}
