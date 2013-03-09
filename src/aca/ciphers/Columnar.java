package aca.ciphers;

import java.util.*;

import aca.util.Generic_Func;

public class Columnar implements Cipher {
	
	  public Columnar()
	  {
		  Random random_gen=new Random();
			period=1+random_gen.nextInt(14);
			key=Generic_Func.generate_random_perm(1, period+1);
	  }
	  
	  public Columnar(int p)
	  {
		  period=p;
		  key=Generic_Func.generate_random_perm(1, period+1);
	  }
	  
	  public Columnar(int[] k)
	  {
		  key=new int[k.length];
		  for(int i=0;i<k.length;i++)
		  {
			  key[i]=k[i];
		  }
	  }
	  
	  public boolean key_need()
	  {
		    return need_key;
	 }
		    
		 public int get_key_num()
		 {
		    return 0;
		 }
		    
		 public ArrayList<Integer> get_key_len()
		 {
			 return null;
		 }
	
	  public String encode(String plain)
	  {
		  char[][] matrix=create_matrix(plain);
		  String result=read_cipher(matrix,plain.length());
		  return result;
	  }
	  
	  char[][] create_matrix(String plain)
	  {
		  int row=plain.length()/key.length;
		  if(plain.length()%key.length!=0)
		  {
			  row+=1;
		  }
		  char[][] matrix=new char[row][key.length];
		  int cur_row=0;
		  int cur_col=0;
		  for(int i=0;i<row*key.length;i++)
		  {
			  if(i>=plain.length())
			  {
				  matrix[cur_row][cur_col]='x';
			  }
			  else
			  {
			     matrix[cur_row][cur_col]=plain.charAt(i);
			  }
			  if(cur_col==key.length-1)
			  {
				  cur_col=0;
				  cur_row++;
			  }
			  else
			  {
				  cur_col++;
			  }
			
		  }
		  return matrix;
	  }
	  
	  String read_cipher(char[][] matrix,int total_length)
	  {
		  int start=1;
		  int col=matrix[0].length;
		  int row=matrix.length;
		  StringBuilder sb=new StringBuilder();
		  while(start<=col)
		  {
			 int cur_row=0;
			 int col_pos=get_pos(key,start);
			 while(cur_row<row)
			 {
			 //  if(cur_row*col+col_pos<total_length)
			  // {
				 sb.append(matrix[cur_row][col_pos]);
				 cur_row++;
			   //}
			   //else
			   //{
				//   break;
			   //}
			 }
			 start+=1;
		  }
		  return sb.toString().toUpperCase();
	  }
	  
	  int get_pos(int[] key,int k)
	  {
		  for(int i=0;i<key.length;i++)
		  {
			  if(key[i]==k)
			  {
				  return i;
			  }
		  }
		  return -1;
	  }
	  
	  
	  
	  private int period=3;
	  private int[] key;
	    
	    /*
	     * Decode the cipher text.
	     */
	    public String decode(String cipher)
	    {
	    	return null;
	    }
	    
	    public boolean need_key=false; //need to generate a string key
}
