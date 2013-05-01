package aca.ciphers;

import java.util.ArrayList;
import java.util.Arrays;
//import java.util.Random;

import aca.util.Generic_Func;

/*
 * Turning grille cipher
 */
public class Grille implements Cipher {
	
	public Grille()
	{
		position=Generic_Func.get_num_sample(4,16);
		Arrays.sort(position);
		
		
	}
	
	public Grille(int[] holes)
	{
		for(int i=0;i<4;i++)
		{
			position[i]=holes[i];
		}
		Arrays.sort(position);
	}
	
	
	
	private int[] position=new int[4];
//	private char[][] grille=new char[4][4];
	
	public String encode(String plain)
	 {
		//Random r=new Random();
		//int key_len=1+r.nextInt(7);
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<plain.length();i+=16)
		{
			int end_pos=i+16>plain.length()?plain.length():i+16;
			String cur_p=plain.substring(i,end_pos);
			char[][] grille=fill_grille(position,cur_p);
			sb.append(read_grille(grille));
		}
		return sb.toString();
	 }
	
	public char[][] fill_grille(int[] position,String plain)
	{
		assert(position.length==4);
		assert(plain.length()<=16);
		int total_len=plain.length();
		char[][] result=new char[4][4];
		int[] cur_holes=new int[4];
		for (int i=0;i<4;i++)
		{
			
			cur_holes[i]=position[i];
			//System.err.println(cur_holes[i]);
		}
		int str_pos=0;//string position
		for(int i=0;i<4;i++)
		{
			//compute each position and fill the hole
			//fill the hole
			for(int j=0;j<4;j++)
			{
			  int cur_row=(cur_holes[j])/4;
			  int cur_col=(cur_holes[j])%4;
			  if(str_pos<total_len)
			  {
				  result[cur_row][cur_col]=plain.charAt(str_pos);
				  str_pos++;
			  }
			  else
			  {
				  break;
			  }
			  int temp=cur_row;
			  //turn 90 degrees clockwise
			  cur_row=cur_col;
			  cur_col=4-1-temp;
			  int pos=cur_row*4+cur_col;
			  cur_holes[j]=pos;
			}
			if(str_pos>=total_len)
			{
				break;
			}
			Arrays.sort(cur_holes);
		/*	if(i<plain.length())
			{
			  result[cur_row][cur_col]=plain.charAt(i);
			}*/
			/*for(int j=0;j<3;j++)
			{
			  int temp=cur_row;
			  //turn 90 degrees clockwise
			  cur_row=cur_col;
			  cur_col=4-1-temp;
			  int cur_pos=i+4*(j+1);
			  if(cur_pos<plain.length())
			  {
			    result[cur_row][cur_col]=plain.charAt(cur_pos);
			  }
			}*/
			
		}
		return result;
	}
	
	public String read_grille(char[][] grille)
	{
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<4;i++)
		{
			for(int j=0;j<4;j++)
			{
				if(grille[i][j]!='\0')
				{
				 sb.append(grille[i][j]);
				}
			}
		}
		return sb.toString().toUpperCase();
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
