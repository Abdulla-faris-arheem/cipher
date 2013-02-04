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

}
