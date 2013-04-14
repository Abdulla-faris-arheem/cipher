package aca.ciphers;

import java.util.ArrayList;
import java.util.Random;

import aca.util.Generic_Func;

public class Gromark implements Cipher {
	
	
	public Gromark()
	{
		key="ENIGMA";
	}
	
	public Gromark(String k)
	{
		key=k;
	}
	
	private String key;
	
	public static char[][] build_trans_block(int[] key_num,String key)
	{
		int col=key_num.length;
		int row=26/col;
		if(row*col!=26)
		{
			row+=1;
		}
		char[][] result=new char[row][col];
		boolean[] filled=new boolean[26];
		for(int i=0;i<key_num.length;i++)
		{
			result[0][i]=key.toUpperCase().charAt(i);
			int pos=result[0][i]-'A';
			filled[pos]=true;
		}
		int cur_row=1;
		int cur_col=0;
		for(int i=0;i<26;i++)
		{
			if(!filled[i])
			{
				result[cur_row][cur_col]=(char)('A'+i);
				filled[i]=true;
				if(cur_col==col-1)
				{
					cur_col=0;
					cur_row++;
				}
				else
				{
					cur_col++;
				}
			}
		}
		return result;		
	}
	
	protected char[] build_keyed_alphabet(char[][] trans_block,int[] key_num)
	{
		char[] result=new char[26];
		//int cur_row=0;
		//int cur_col=0;
		int i=0;
		int key_col=1;
		while(key_col<=key_num.length)
		{
			int col_index=Generic_Func.search_index(key_num, key_col,0);
			if(col_index==-1)
			{
				System.err.println("Error in finding the index");
			}
			char[] key_alpha_piece=read_alpha(trans_block,col_index);
			for(int j=0;j<key_alpha_piece.length;j++)
			{
				result[i]=key_alpha_piece[j];
				i++;
			}
			key_col++;
		}
		return result;
	}
	
	private char[] read_alpha(char[][] trans_block,int col_num)
	{
		ArrayList<Character> c=new ArrayList<Character>();
		for(int i=0;i<trans_block.length;i++)
		{
			if(trans_block[i][col_num]!='\0')
			{
				c.add(trans_block[i][col_num]);
			}
		}
		char[] result_array=new char[c.size()];
		for(int i=0;i<c.size();i++)
		{
			result_array[i]=c.get(i);
		}
		return result_array;
	}
	
	public String encode(String plain)
	 {
		int[] key_num=Cadenus.generate_order(key);
		char[][] trans_block=build_trans_block(key_num,key);
		char[] keyed_alphabet=build_keyed_alphabet(trans_block,key_num);
		Random r=new Random();
		//int primer=23452;
		int primer=10000+r.nextInt(90000);//generate a 5-digit number randomly
		int[] num_k=generate_num_k(plain,primer);
		
		return generate_ct(keyed_alphabet,trans_block,num_k,plain);
	 }
	
	
	private String generate_ct(char[] keyed_alphabet,char[][] trans_block, int[] num_k, String plain)
	{
		String plain_l=plain.toLowerCase();
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<5;i++)
		{
			sb.append(num_k[i]);
		}
		assert(num_k.length==plain.length());
		for(int i=0;i<num_k.length;i++)
		{
			int ka_pos=num_k[i];
			int pt_pos=plain_l.charAt(i)-'a';
			int pos=pt_pos+ka_pos>=26?pt_pos+ka_pos-26:pt_pos+ka_pos;	
			sb.append(keyed_alphabet[pos]);
		}
		sb.append(num_k[num_k.length-1]);
		return sb.toString();
	}
	
	protected int[] generate_num_k(String plain,int primer)
	{
		int[] result=new int[plain.length()];
		String p_s=Integer.toString(primer);
		char[] p_array=p_s.toCharArray();
		int array_len=p_array.length;
	//	assert(p_array.length==5);
		for(int i=0;i<array_len;i++)
		{
			result[i]=p_array[i]-'0';
		}
		for(int i=array_len;i<result.length;i++)
		{
			int first=i-array_len;
			int second=i-array_len+1;
			int add_result=result[first]+result[second];
			add_result=add_result>=10?add_result-10:add_result;
			result[i]=add_result;
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
}
