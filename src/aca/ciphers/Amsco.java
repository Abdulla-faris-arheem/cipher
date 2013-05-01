package aca.ciphers;

import java.util.*;

import aca.util.Generic_Func;

public class Amsco implements Cipher {
	
	public static boolean need_key=false;
	
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
	
	public Amsco()
	{
		Random random_gen=new Random();
		period=1+random_gen.nextInt(14);
		key=Generic_Func.generate_random_perm(1, period+1);
	}
	
	public Amsco(int[] k)
	{
		key=new int[k.length];
		for(int i=0;i<k.length;i++)
		{
			key[i]=k[i];
		}
		period=k.length;
	}
	
	public void set_di_begin(boolean di)
	{
		di_begin=di;
	}
	
	private int period=5;
	private int[] key;
	private boolean di_begin=true;
	
	protected String[][] create_matrix(String plain)
	{
		ArrayList<String> s=new ArrayList<String>();
		boolean dig;
		if(di_begin)
		{
			dig=true;
		}
		else
		{
			dig=false;
		}
		int string_index=0;
		int plain_size=plain.length();
		while(string_index<plain_size)
		{
			if(dig)
			{
				if(string_index==plain_size-1)
				{
					s.add(plain.substring(string_index,string_index+1));
					string_index++;
				}
				else
				{
					s.add(plain.substring(string_index,string_index+2));
					string_index+=2;
				}
				dig=false;
			}
			else
			{
				s.add(plain.substring(string_index,string_index+1));
				string_index++;
				dig=true;
			}
		}
		int col=period;
		int row=s.size()/col;
		if(s.size()%col!=0)
		{
			row++;
	    }
		String[][] matrix=new String[row][col];
		int s_index=0;
		int cur_row=0;
		int cur_col=0;
		while(s_index<s.size())
		{
			matrix[cur_row][cur_col]=s.get(s_index);
			s_index++;
			if(cur_col<period-1)
			{
				cur_col++;
			}
			else
			{
				cur_row++;
				cur_col=0;
			}
		}
		return matrix;
	}
	
	/*
	 * Read the matrix according to the key
	 */
	protected String encode_matrix(String[][] matrix)
	{
		int row=matrix.length;
	//	int col=matrix[0].length;
		int cur_index=1;
		int col_index;
		StringBuilder s=new StringBuilder();
		Integer[] tk=new Integer[this.key.length];
		for(int i=0;i<tk.length;i++)
		{
			tk[i]=Integer.valueOf(this.key[i]);
		}
		while(cur_index<=this.period)
		{
			
			col_index=Arrays.asList(tk).indexOf(cur_index);
			for(int i=0;i<row;i++)
			{
				if(matrix[i][col_index]!=null)
				{
					s.append(matrix[i][col_index]);
				}
			}
			cur_index++;
		}
		return s.toString();
	}

	@Override
	public String encode(String plain) {
		//Random random_gen=new Random();
		//boolean di_begin=random_gen.nextBoolean();
		String[][] matrix;
		if(di_begin)
		{
			matrix=create_matrix(plain);
		}
		else
		{
			matrix=create_matrix(plain);
		}
		String cipher_text=encode_matrix(matrix);
		return cipher_text.toUpperCase();  //ciphered text is represented by uppercase letters
	}

	@Override
	public String decode(String cipher) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public int process_id()
	{
		return 0;
	}

}
