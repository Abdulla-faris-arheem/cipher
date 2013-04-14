package aca.ciphers;

import java.util.ArrayList;
import java.util.Random;

import aca.util.Generic_Func;

public class Incomp_column implements Cipher {
	
	public Incomp_column()
	{
		Random random_gen=new Random();
		period=1+random_gen.nextInt(14);
		key=Generic_Func.generate_random_perm(1, period+1);
	}
	
	public Incomp_column(int[] k)
	{
		period=k.length;
		key=new int[period];
		for(int i=0;i<k.length;i++)
		{
			key[i]=k[i];
		}
	}
	
	private int[] key;
	private int period;//key length
	
	public String encode(String plain)
	 {
		char[][] block=build_block(plain,period,0);
		String cipher_text=read_block(block,key);		
		return cipher_text;
	 }
	
	public static char[][] build_block(String plain, int period, int direction)
	{
		String plain_u=plain.toUpperCase();
		int row=plain_u.length()/period;
		if(plain_u.length()%period!=0)
		{
			row++;
		}
		char[][] result=new char[row][period];
		if(direction==1)//1 for vertical, 0 for horizontal
		{
			int cur_row=0;
			int cur_col=0;
			for(int i=0;i<plain_u.length();i++)
			{
				result[cur_row][cur_col]=plain_u.charAt(i);
				if(cur_row == row-1)
				{
					cur_row=0;
					cur_col++;
				}
				else
				{
					cur_row++;
				}
			}
		}
		else
		{
			int cur_row=0;
			int cur_col=0;
			for(int i=0;i<plain_u.length();i++)
			{
				result[cur_row][cur_col]=plain_u.charAt(i);
				if(cur_col == period-1)
				{
					cur_row++;
					cur_col=0;
				}
				else
				{
					cur_col++;
				}
			}
		}
		return result;
	}
	
	public static String read_block(char[][] block, int[] order)
	{
		int cur_find=1;
		int row_num=block.length;
		StringBuilder sb=new StringBuilder();
		while(cur_find<=order.length)
		{
			int pos=Generic_Func.search_index(order, cur_find,0);
			//read vertically
			int cur_row=0;
			while(cur_row<row_num && block[cur_row][pos]!='\0')
			{
				sb.append(block[cur_row][pos]);
				cur_row++;
			}
			cur_find++;
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
}
