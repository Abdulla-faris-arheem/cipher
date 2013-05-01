package aca.ciphers;

import java.util.ArrayList;
//import java.util.Random;

import aca.util.Generic_Func;

public class Nicodemus implements Cipher {
	
	public Nicodemus()
	{
		key="CAT";
	}
	
	
	public Nicodemus(String k)
	{
		key=k;
	}
	
	private String key;
	
	public String encode(String plain)
	 {
		int[] key_order=Cadenus.generate_order(key);
		char[][] block=Incomp_column.build_block(plain, -1,key.length(), 0);
		char[][] r_block=reorder_block(block,key_order);
		vige_cipher(r_block,key_order);
		String cipher_text=read_block(r_block);
		return cipher_text;
	 }
	
	
	
	public void vige_cipher(char[][] r_block,int[] key_order)
	{
		int row=r_block.length;
		int col=r_block[0].length;
		for(int j=0;j<col;j++)
		{
			int pos=Generic_Func.search_index(key_order, j+1, 0);
			char cur_kc=key.toUpperCase().charAt(pos);
			for(int i=0;i<row;i++)
			{
				if(r_block[i][j]!='\0')
				{
					r_block[i][j]=Vigenere.get_vigenere_char(cur_kc, r_block[i][j]);
				}
			}
		}
	}
	
	public String read_block(char[][] block)
	{
		int col=block[0].length;
		StringBuilder sb=new StringBuilder();
		int start_row=0;
		int total_row=block.length;
		while(start_row<total_row)
		{
			int end_row=start_row+5>=total_row?total_row:start_row+5;
			//int read=remain_row<5?remain_row:5;
			for(int i=0;i<col;i++)
			{
			  for(int j=start_row;j<end_row;j++)
			  {
				  if(block[j][i]!='\0')
				  {
				    sb.append(block[j][i]);
				  }
			  }
			}
			start_row=end_row;
		}
		return sb.toString();
	}
	
	public char[][] reorder_block(char[][] block, int[] key_order)
	{
		int row=block.length;
		int col=block[0].length;
		char[][] result=new char[row][col];
		int total=key_order.length;
		int cur_num=1;
		while(cur_num<=total)
		{
			int pos=Generic_Func.search_index(key_order, cur_num, 0);
			for(int i=0;i<row;i++)
			{
				result[i][cur_num-1]=block[i][pos];
			}
			cur_num++;
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
	    	return 1;
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
