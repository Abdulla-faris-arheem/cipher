package aca.ciphers;

import java.util.ArrayList;
import java.util.Random;
import java.util.HashMap;

import aca.util.Generic_Func;
import aca.util.Pair;

public class Swagman implements Cipher {
	public Swagman()
	{
		Random r=new Random();
		int period=4+r.nextInt(5);
		num_key=Generic_Func.generate_random_perm(1, period+1);
	}
	
	public Swagman(int[] num_k)
	{
		num_key=new int[num_k.length];
		for(int i=0;i<num_k.length;i++)
		{
			num_key[i]=num_k[i];
		}
	}
	
	int[] num_key;
	
	public String encode(String plain)
	 {
		int period=num_key.length;
		int col=plain.length()/period;
		if(plain.length()%period!=0)
		{
			col++;
		}
		String plain_u=plain.toUpperCase();
	    char[][] pt=Incomp_column.build_block(plain_u, period,col, 0);
	    int[][] key_square=make_key_square(num_key);
	  //  int[][] key_square={{3,2,1,4,5},{1,5,3,2,4},{2,4,5,3,1},{5,3,4,1,2},{4,1,2,5,3}};
	    StringBuilder sb=new StringBuilder();
	    for(int i=0;i<col;i+=period)
	    {
	    	int end=i+period>col?col:i+period;
	    	sb.append(get_sub_ct(pt,i,end,key_square));
	    }
		
		return sb.toString();
	 }
	
	/**
	 * Get the cipher text of substring of a square (or part of the square)
	 * @param pt plain text square (can have null values)
	 * @param start_col start column
	 * @param end_col end column 
	 * @param key_square square containing numbers to do the transposition
	 * @return cipher text
	 */
	private String get_sub_ct(char[][] pt, int start_col,int end_col,int[][] key_square)
	{
		
		int row=pt.length;
		char[][] result_ct=new char[row][end_col-start_col];
		StringBuilder sb=new StringBuilder();
		for(int i=start_col;i<end_col;i++)
		{
			//find the correct position for each column
			for(int j=0;j<row;j++)
			{
				if(pt[j][i]=='\0')
					continue;
				result_ct[key_square[j][i-start_col]-1][i-start_col]=pt[j][i];
				/*if(pt[j][i]!='\0')
				{
				  sb.append(pt[j][i]);
				}*/
			}
		}
		for(int i=0;i<end_col-start_col;i++)
		{
			for(int j=0;j<row;j++)
			{
				if(result_ct[j][i]!='\0')
				{
					sb.append(result_ct[j][i]);
				}
			}
		}
		return sb.toString();
	}
	
	
	private void Init_cand(int[] num_key,HashMap<Pair<Integer>,ArrayList<Integer>> c)
	{
		int total=num_key.length;
		for(int i=1;i<total;i++)
		{
		  for(int j=0;j<total;j++)
		  {
			Pair<Integer> p=new Pair<Integer>(i,j);
			ArrayList<Integer> cur_c=new ArrayList<Integer>();
			for(int k=1;k<=total;k++)
			{
				if(k==num_key[j])
					continue;
				else{
					cur_c.add(k);
				}
			}
			c.put(p, cur_c);
		  }
		}
	}
	
	private int[][] make_key_square(int[] num_key)
	{
		int size=num_key.length;
		int[][] result=new int[size][size];
		HashMap<Pair<Integer>,ArrayList<Integer>> candidates=new HashMap<Pair<Integer>, ArrayList<Integer>> ();
		Init_cand(num_key,candidates);
		for(int i=0;i<size;i++)
		{
			result[0][i]=num_key[i];
		}
		//boolean[] filled=new boolean[size];
		//filled[num_key[0]-1]=true;
	    boolean s=solve_num(1,0,result);
	    if(!s)
	    {
	    	System.err.println("Unable to make the square");
	    }
		
	/*	for(int i=1;i<size;i++)
		{
			for(int j=0;j<size;j++)
			{
				//result[i][j]=generate_next(result,i,j);
				ArrayList<Integer> c=candidates.get(new Pair<Integer>(i,j));
				if(c.size()==1)
				{
					if(valid_entry(i,j,c.get(0),result))
					{
						result[i][j]=c;
					}
					else
					{
						//backtracking
						
					}
				}
			}
		}*/
		return result;
	}
	
	private boolean solve_num(int row,int col,int[][] result)
	{
		int sq=result.length;
		if(row==sq || col==sq)
			return true;
	//	if(result[row][col]!=0)
		//	return true;
		ArrayList<Integer> cands=new ArrayList<Integer>();
		/*	for(int i=0;i<filled.length;i++)
		{
			if(!filled[i] && is_valid(i+1,result,row,col))
			{
				cands.add(i+1);
			}
			/*if(!filled[i])
			{
				cands.add(i+1);
			}*/
		//}
		for(int i=1;i<=sq;i++)
		{
			if(is_valid(i,result,row,col))
			{
				cands.add(i);
			}
		}
		Random r=new Random();
		while(cands.size()!=0)
		{
			int select=r.nextInt(cands.size());
			result[row][col]=cands.get(select);
			 boolean next=false;
			 if(col<sq-1)
			 {
				 // next=solve_num(row,col+1,next_filled,candidates,result);
				 next=solve_num(row,col+1,result);
			 }
			 else
			{
				 // next=solve_num(row+1,0,next_filled,candidates,result);
				  next=solve_num(row+1,0,result);
			}
			 if(next)
			 {
				 result[row][col]=cands.get(select);
				 return true;
			 }
			 else
			 {
				 cands.remove(select);
			 }
		}
		return false;
	
		
	
		
	}
	
	private boolean is_valid(int target, int[][] square,int row,int col)
	{
		for(int i=0;i<row;i++)
		{
			if(square[i][col]==target)
				return false;
		}
		for(int i=0;i<col;i++)
		{
			if(square[row][i]==target)
				return false;
		}
		return true;
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
			return 2;
		}
}
