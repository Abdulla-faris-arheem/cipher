package aca.util;

import java.util.*;



public class Generic_Func {
	
	/*
	 * Split a number to its digits
	 */
	static String[] num_str={"zero","one","two","three","four","five","six","seven","eight","nine"};
    static String[] num_str2={"twenty","thirty","forty","fifty","sixty","seventy","eighty","ninety"};
    static String[] num_str3={"ten","eleven","twelve","thirteen","fourteen","fifteen","sixteen","seventeen","eighteen","nineteen"};
   
   
    
	public static ArrayList<Integer> split_number(int number)
	{
		ArrayList<Integer> result=new ArrayList<Integer>();
		if(number==0)
		{
			result.add(0);
			return result;
		}
		int base=10;
		int remain=number;
//		int cur_n=remain/base;
		while(remain>0)
		{
		  int cur_n=remain%base;
		  result.add(cur_n);
		  remain=remain/base;
		}
		Collections.reverse(result);
		return result;
	}
	
	/*
	 * Spell out a number using English words
	 * Assumption: the number is less than 1,000,000
	 */
	public static ArrayList<String> spell_number(int number)
	{
		ArrayList<String> result=new ArrayList<String>();
		if(number/1000000>0)
		{
			System.err.println("number out of range");
			return result;
		}
	/*	ArrayList<Integer> num_split=split_number(number);
		int num_size=num_split.size();
		if (num_size>=7)
		{
			//Error err=new Error("number out of range");
			System.err.println("number out of range");
			return result;
		}*/
	    //process 3-digit units
		//can be extended to handle multiple 3-digit units
		int left=number/1000;
		int right=number%1000;
		if(left>0)
		{
			ArrayList<String> left_rep=spell_number(left);
			for (int i=0;i<left_rep.size();i++)
			{
				result.add(left_rep.get(i));
			}
			result.add("thousand");
		}
		ArrayList<Integer> num_split=split_number(right);
		int total_size=num_split.size();
		if(total_size==3)
		{
			int first_digit=num_split.get(0);
			String cur_str=Generic_Func.num_str[first_digit];
			result.add(cur_str);
			result.add("hundred");
			int second_digit=num_split.get(1);
			int third_digit=num_split.get(2);
			if(second_digit!=0)
			{
				result.add("and");
				if(second_digit==1)
				{
					//int third_digit=num_split.get(2);
					result.add(Generic_Func.num_str3[third_digit]);
				}
				else
				{
					result.add(Generic_Func.num_str2[second_digit-2]);
				//	int third_digit=num_split.get(2);
					if(third_digit!=0)
					{
						result.add(Generic_Func.num_str[third_digit]);
					}
				}
			}
			else if(third_digit!=0)
			{
				result.add("and");
				result.add(Generic_Func.num_str[third_digit]);
			}
		}
		else if(total_size==2)
		{
			int second_digit=num_split.get(0);
			int third_digit=num_split.get(1);
			if(second_digit!=0)
			{
				if(second_digit==1)
				{
					result.add(Generic_Func.num_str3[third_digit]);
				}
				else
				{
					result.add(Generic_Func.num_str2[second_digit-2]);
					if(third_digit!=0)
					{
						result.add(Generic_Func.num_str[third_digit]);
					}
				}
			}
		}
		else if(total_size==1)
		{
			int third_digit=num_split.get(0);
			result.add(Generic_Func.num_str[third_digit]);
		}
		return result;
		
		
	}
	
	/*
	 * Generate a random permutation of [start,end)
	 */
	public static int[] generate_random_perm(int start,int end)
	{
		ArrayList<Integer> num_set=new ArrayList<Integer>();
		for(int i=start;i<end;i++)
		{
			num_set.add(i);
		}
		int[] result=new int[end-start];
		Random random_gen=new Random();
		int result_index=0;
		while(num_set.size()!=1)
		{
			int cur_index=random_gen.nextInt(num_set.size());
			result[result_index]=num_set.get(cur_index);
			result_index++;
			num_set.remove(cur_index);
		}
		result[result_index]=num_set.get(0);
		return result;
	}
	
	/*
	 * Predicting the next position of spiral matrix
	 */
	
	public static Pair<Integer> traverse_spiral(int cur_row,int cur_col,int square_len)
	{
		//int col_bound=square_len-1-cur_row;
		//int row_bound=square_len-1-cur_col;
		int midpoint=(square_len-1)/2;
		int next_col=cur_col;
		int next_row=cur_row;
		if(cur_row==square_len/2 && cur_col==midpoint)
		{
			next_col=-1;
			next_row=-1;
		}
		else if (cur_row<=midpoint)
		{
			//left boundary
			if(cur_col<cur_row-1)
			{
				//move upwards
				next_row--;
			}
			else if(cur_col<square_len-1-cur_row)
			{
				//going in the right horizontal direction
				next_col++;
			}
			else if(next_col!=midpoint)//if(cur_col!=square_len-1-cur_row)
			{
				
				//going downside
				next_row++;
			}
			/*else
			{
				next_col=-1;
				next_row=-1;
			}*/
		}
		else
		{
			if(cur_col>cur_row)
			{
				//going downside
				next_row++;
			}
			else if(cur_col>square_len-1-cur_row)
			{
				//going left
				next_col--;
			}
			else
			{
				//going up
				next_row--;
			}
		}
	/*	else if(cur_col==square_len-1-cur_row)
		{
			next_col=-1;
			next_row=-1;
		}
		else if(cur_col<)
		{
			next_row--;
		}
		else
		{
			next_row++;
		}*/
		Pair<Integer> p=new Pair<Integer>(next_row,next_col);
		return p;
	}
	
	public static int search_str(String[] s, String key)
	{
		for(int i=0;i<s.length;i++)
		{
			if(s[i].equals(key))
				return i;
		}
		return -1;
	}
	
	public static int find_char(char[] array, char c)
	{
		for(int i=0;i<array.length;i++)
		{
			if(array[i]==c)
			{
				return i;
			}
			
		}
		return -1;
	}
	
	public static int search_index(int[] array,int num,int start_index)
	{
		for(int i=start_index;i<array.length;i++)
		{
			if(array[i]==num)
				return i;
		}
		return -1;
	}
	
	/*
	 * Get a subset of m samples from n numbers (0-n-1,inclusive)
	 */
	public static int[] get_num_sample(int m,int n)
	{
		int[] total_array=new int[n];
		for(int i=0;i<n;i++)
		{
			total_array[i]=i;
		}
	    int[] result=new int[m];
	  //  int cur_m=m;
	    Random r=new Random();
	    for(int i=0;i<m;i++)
	    {
	    	int pos=i+r.nextInt(n-i); 
	    	result[i]=total_array[pos];
	    	swap_int(total_array,i,pos);
	    }
	    return result;
	}
	
	public static void swap_int(int[] array, int index1,int index2)
	{
		int a=array[index1];
		array[index1]=array[index2];
		array[index2]=a;
	}
	
	
	/*public static int binary_search_str(String[] s, String key)
	{
		return binary_search_str(s,key,0,s.length-1);
	}
	
	public static int binary_search_str(String[] s, String key, int start,int end)
	{
		if(start>end)
			return -1;
		int cur=(start+end)/2;
		if(s[cur]==key)
		{
			return cur;
		}
		else
		{
			
		}
	}*/

}
