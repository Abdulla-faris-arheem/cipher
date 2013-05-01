package aca.ciphers;

import java.util.ArrayList;
import java.util.Random;

import aca.util.Generic_Func;

public class Redefence implements Cipher {
	
	public Redefence()
	{
		Random r=new Random();
		int key_len=2+r.nextInt(5);
	//	num_key=new int[key_len];
		num_key=Generic_Func.generate_random_perm(1, key_len+1);
		
	}
	
	public Redefence(int[] num_k)
	{
		num_key=new int[num_k.length];
		for(int i=0;i<num_k.length;i++)
		{
			num_key[i]=num_k[i];
		}
	}
	
	private int[] num_key;
	
	public String encode(String plain)
	 {
	//	int row=num_key.length;
		//write zigzag
		char[][] zigzag_block=build_zigzag_block(plain,num_key.length);
		String ct=read_zigzag(zigzag_block,num_key,true);
		return ct.toUpperCase();
	 }
	
	protected String read_zigzag(char[][] block, int[] num_key,boolean row)
	{
		StringBuilder sb=new StringBuilder();
		if(row)
		{
			for(int i=1;i<=num_key.length;i++)
			{
				int pos=Generic_Func.search_index(num_key, i, 0);
				for(int j=0;j<block[pos].length;j++)
				{
					if(block[pos][j]!='\0')
						sb.append(block[pos][j]);
				}
				
			}
		}
		else//column
		{
			for(int i=1;i<=num_key.length;i++)
			{
				int pos=Generic_Func.search_index(num_key, i, 0);
				for(int j=0;j<block.length;j++)
				{
					if(block[j][pos]!='\0')
						sb.append(block[j][i]);
				}
				
			}
		}
		return sb.toString();
	}
	
	public static char[][] build_zigzag_block(String plain,int row)
	{
		char[][] result=new char[row][plain.length()];
		int position=0;
		boolean down=true;
		for(int i=0;i<plain.length();i++)
		{
			result[position][i]=plain.charAt(i);
			if(down)
			{
				if(position==row-1)
				{
					down=false;
					position--;
				}
				else
				{
					position++;
				}
			}
			else
			{
				if(position==0)
				{
					down=true;
					position++;
				}
				else
				{
				  position--;
				}
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
