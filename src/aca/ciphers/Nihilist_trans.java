package aca.ciphers;

import java.util.ArrayList;
import java.util.Random;

import aca.util.Generic_Func;

public class Nihilist_trans implements Cipher {
	
	public Nihilist_trans()
	{
		Random r=new Random();
		period=1+r.nextInt(10);
		key=Generic_Func.generate_random_perm(1, period+1);
	}
	
	public Nihilist_trans(int[] k)
	{
		key=new int[k.length];
		period=k.length;
		for(int i=0;i<k.length;i++)
		{
			key[i]=k[i];
		}
	}
	
	private int[] key;
	private int period;
	
	public String encode(String plain)
	 {
        int sq_num=period*period;
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<plain.length();i+=sq_num)
		{
			int end_num=i+sq_num>=plain.length()?plain.length():i+sq_num;
			sb.append(cipher_sub(plain,i,end_num,period));
		}
		
		return sb.toString().toUpperCase();
	 }
	
	private String cipher_sub(String plain, int start,int end,int sq)
	{
		char[][] square=new char[sq][sq];
		fill_square(square,plain,start,end);
		char[][] square2=transpose_square(key,square,false);
		char[][] square3=transpose_square(key,square2,true);
		String sub_cipher=read_square(square3,true);
		return sub_cipher;
	}
	
	public String read_square(char[][] square, boolean row)
	{
		StringBuilder sb=new StringBuilder();
		if(row)
		{
		  for(int i=0;i<square.length;i++)
		  {
			  for(int j=0;j<square[0].length;j++)
			  {
				  if(square[i][j]!='\0')
				  {
				    sb.append(square[i][j]);
				  }
			  }
		  }
		}
		else
		{
			for(int i=0;i<square[0].length;i++)
			  {
				  for(int j=0;j<square.length;j++)
				  {
					  if(square[j][i]!='\0')
					  {
					    sb.append(square[j][i]);
					  }
				  }
			  }
		}
		return sb.toString();
	}
	
	/**
	 * Transpose a square according to a integer key. 
	 * 
	 * @param key a consecutive integer sequence, e.g. [2,1,3,4] The key length should be the same as
	 * the square row and column
	 * @param square the square to be transposed
	 * @param col_flag transposition according to row or column. Row for false and column for true.
	 * @return
	 */
	public char[][] transpose_square(int[] key,char[][] square, boolean col_flag)
	{
		int row=square.length;
		int col=square[0].length;
	    char[][] result=new char[row][col];
	    if(!col_flag)
	    {
	     for(int i=0;i<key.length;i++)
	     {
	    	for(int j=0;j<row;j++)
	    	{
	    		result[j][i]=square[j][key[i]-1];
	    	}
	     }
	    }
	    else
	    {
	    	 for(int i=0;i<key.length;i++)
		     {
		    	for(int j=0;j<col;j++)
		    	{
		    		result[i][j]=square[key[i]-1][j];
		    	}
		     }
	    }
	    return result;
	}
	
	public static void fill_square(char[][] square,String str, int start,int end)
	{
		int cur_row=0;
		int cur_col=0;
		//int total_row=square.length;
		int total_col=square[0].length;
	    for(int i=start;i<end;i++)
	    {
	    	square[cur_row][cur_col]=str.charAt(i);
	    	if(cur_col==total_col-1)
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
