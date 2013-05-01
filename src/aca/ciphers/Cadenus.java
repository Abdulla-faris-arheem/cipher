package aca.ciphers;

import java.util.ArrayList;
//import java.util.Random;
import java.util.Arrays;
//import java.util.StringBuilder;

//import aca.util.Generic_Func;

public class Cadenus implements Cipher {
	
	public Cadenus()
	{
		
	}
	
	public Cadenus(String k)
	{
		key=k.toUpperCase();
		period=key.length();
	}
	
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
		 ArrayList<Integer> result=new ArrayList<Integer>();
		 result.add(key_len);
	    return result;
	 }
	 
	 public void set_key_len(int key_l)
	 {
		 this.key_len=key_l;
	 }
	 
	 
	
	private String key="EASY";
	private int period=4;
	
	public static boolean is_valid(String text)
	{
		if(text.length()<=25)
		{
			return false;
		}
		if(text.length()%25==0)
			return true;
		else
		{
			return false;
		}
	}
	
	public static char[] key_array={'A','Z','Y','X','W','U','T','S','R','Q','P','O',
		'N','M','L','K','J','I','H','G','F','E','D','C','B'};
	
	public String add_comp(String plain)
	{
		int len=plain.length();
		int n=len%25;
		if(n==0)
		{
			return plain;
		}
		else
		{
		    StringBuilder sb=new StringBuilder();
		    sb.append(plain);
		    for(int i=0;i<25-n;i++)
		    {
		    	sb.append('x');
		    }
		    return sb.toString();
		}
	}
	
	  public String encode(String plain)
	  {
		  //Assume plain has a length which is multiply of 25
		  //Need to generate a key of length x, 25x=length of plain
	//	  if(!is_valid(plain))
		//	  return null;
		  //add 'X' till the length is a multiply of 25
		 // if(plain.endsWith("mess"))
		/*  if(plain.startsWith("thisiswhatdictionariesusedtolooklike"))
		 {
		    System.err.println("test");	  
		 }*/
		  String new_plain=add_comp(plain);
		//  int key_len=plain.length()/25;
		 // key=generate_key_len(key_len);
		  if (period!=key.length() || new_plain.length()/25!=period)
		  {
			  return null;
		  }
		  char[][] matrix=generate_matrix(key,new_plain);
		  int[] order=generate_order(key);
		  Integer[] order_i=new Integer[order.length];
		  for(int i=0;i<order.length;i++)
		  {
			  order_i[i]=Integer.valueOf(order[i]);
		  }
		  char[][] cipher_text=new char[25][period];
		  int cur_col=1;
		  while(cur_col<=period)
		  {
			 int col_index=Arrays.asList(order_i).indexOf(cur_col);
			 char k=key.charAt(col_index);
			 if(k=='V')
			 {
				 k='W';
			 }
			 int k_index=get_index(k);
			// int k_index=Arrays.asList(key_array).indexOf(k);
			 for(int i=0;i<25;i++)
			 {
				 cipher_text[i][cur_col-1]=matrix[k_index][col_index];
				 if(k_index==24)
				 {
					 k_index=0;
				 }
				 else
				 {
					 k_index++;
				 }
			 }
			 cur_col++;
		  }
		  StringBuilder sb=new StringBuilder();
		  for(int row=0;row<25;row++)
		  {
			  for(int col=0;col<period;col++)
			  {
				  sb.append(cipher_text[row][col]);
			  }
		  }
		  return sb.toString().toUpperCase();
		 // int[] order=Generic_Func.generate_random_perm(1, period);
		  
		  
	  }
	  
	  int get_index(char k)
	  {
		  if(k=='A')
		  {
			  return 0;
		  }
		  else
		  {
			  if(k<'V')
			  {
				  return 24-(k-'B');
			  }
			  else
			  {
				  return 25-(k-'B');
			  }
		  }
	  }
	  
	  char[][] generate_matrix(String key,String plain)
	  {
		  int column_len=key.length();
		  char[][] result=new char[25][column_len];
		  int current_row=0;
		  int current_col=0;
		  for(int i=0;i<plain.length();i++)
		  {
			  result[current_row][current_col]=plain.charAt(i);
			  if(current_col==column_len-1)
			  {
				  current_col=0;
				  current_row++;
			  }
			  else
			  {
				  current_col++;
			  }
			 // current_row++;
			  
		  }
		  return result;
	  }
	  
	  public static int[] generate_order(String key)
	  {
		  String low_key=key.toLowerCase();
		  int[] num=new int[key.length()];
		  int[] ori_num=new int[key.length()];
		  for(int i=0;i<key.length();i++)
		  {
			  num[i]=low_key.charAt(i)-'a';
			  ori_num[i]=num[i];
		  }
		  Arrays.sort(num);
		  int[] result=new int[key.length()];
		  for(int i=0;i<ori_num.length;i++)
		  {
			  for(int j=0;j<num.length;j++)
			  {
				  if(ori_num[i]==num[j])
				  {
					  result[i]=j+1;
					  num[j]=-1;
					  break;
				  }
				  
			  }
		  }
		  return result;
		  
	  }
	  
	  public int process_id()
		{
			return 2;
		}
	    
	    /*
	     * Decode the cipher text.
	     */
	    public String decode(String cipher)
	    {
	    	return null;
	    }
	    
	    private boolean need_key=true;
	    
	    public int key_num=1; 
	    
	    public int key_len=1;

}
