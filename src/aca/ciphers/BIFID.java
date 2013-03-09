package aca.ciphers;

import java.util.Random;
import java.util.HashMap;
import java.util.ArrayList;
import aca.util.*;

public class BIFID implements Cipher {
	
	public BIFID()
	{
		//Random random_gen=new Random();
	//	this.period=random_gen.nextInt(9)+5;
		//this.period=random_gen.
	}
	
	public BIFID(int p)
	{
		this.period=p;
	}
	
	public BIFID(int p, String key)
	{
		period=p;
		keyword=key;
	}
	
	public BIFID(String key)
	{
		Random random_gen=new Random();
		period=5+random_gen.nextInt(8);
		keyword=key;
		
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
	    return null;
	 }
	
	private boolean need_key=true;
	
	public static char[][] generate_bifid_square(String keyword)
	{
		boolean[] alpha=new boolean[26];
		char[][] square=new char[5][5];
		int cur_row=0;
		int cur_col=0;
		//write the keyword to the square in a clockwise spiral
		for(char c:keyword.toLowerCase().toCharArray())
		{
			
			if(c=='j')
			{
				c='i';
				//index='i'-'a';
			}
			int index=c-'a';
			if(!alpha[index])
			{
				square[cur_row][cur_col]=(char)(c+'A'-'a');
				//spiral
				alpha[index]=true;
				//modify cur_row,cur_col
			    Pair<Integer> next_pos=Generic_Func.traverse_spiral(cur_row, cur_col, 5);
			    cur_row=next_pos.get_first();
			    cur_col=next_pos.get_second();
			}	
		}
		for(int i=0;i<26;i++)
		{
			if (i==9 || alpha[i]==true)
				continue;
			else if(!alpha[i])
			{
				square[cur_row][cur_col]=(char)('A'+i);
				//spiral
				alpha[i]=true;
				//modify cur_row,cur_col
			    Pair<Integer> next_pos=Generic_Func.traverse_spiral(cur_row, cur_col, 5);
			    cur_row=next_pos.get_first();
			    cur_col=next_pos.get_second();
			}
		    
		}
		return square;
		
	}
	
	public static HashMap<Character,Pair<Integer>> build_map(char[][] square)
	{
	    int row=square.length;
	    int col=square[0].length;
	    HashMap<Character,Pair<Integer>> h=new HashMap<Character,Pair<Integer>>();
	    for(int i=0;i<row;i++)
	    {
	    	for(int j=0;j<col;j++)
	    	{
	    		h.put(square[i][j], new Pair<Integer>(i,j));
	    	}
	    }
	    return h;
	}
	
	private String cipher_sub(char[][] square,HashMap<Character,Pair<Integer>> map,String s)
	{
		int str_len=s.length();
		StringBuilder b=new StringBuilder();
		int[] row_col_list=new int[2*str_len];
		//int[] row_list=new int[str_len];
		//int[] col_list=new int[str_len];
		int cur_index=0;
		for(char c:s.toCharArray())
		{
			char find=(char)(c-'a'+'A');
			if(c=='j')
			{
				find='I';
			}
			Pair<Integer> cs=map.get(find);
			row_col_list[cur_index]=cs.get_first();
			row_col_list[cur_index+str_len]=cs.get_second();
			cur_index++;
		}
		for(int i=0;i<2*str_len;i+=2)
		{
			char cipher_c=square[row_col_list[i]][row_col_list[i+1]];
			b.append(cipher_c);
		}
		return b.toString(); 
	}
	
    public String encode(String plain)
    {
    	//generate polymbius square
    	char[][] poly_b=BIFID.generate_bifid_square(keyword);
    	HashMap<Character,Pair<Integer>> m=build_map(poly_b);
    	ArrayList<String> sub=new ArrayList<String>();
    	int start_pos=0;
    	int plain_size=plain.length();
    	while(start_pos<plain_size)
    	{
    		int end_pos=start_pos+this.period<plain_size?start_pos+this.period:plain_size;
    		sub.add(plain.substring(start_pos,end_pos));
    		start_pos=end_pos;
    	}
    	StringBuilder b=new StringBuilder();
    	
    	for(String s:sub)
    	{
    		String sub_cipher=cipher_sub(poly_b,m,s);
    		b.append(sub_cipher);
    	}
  /*  	for(char c:plain.toUpperCase().toCharArray())
    	{
    		Pair<Integer> pos=m.get(c);
    		
    	}*/
    	return b.toString();
    }
    
    public String decode(String cipher)
    {
    	return "";
    }
    
    private int period=8;
    private String keyword="EXTRAORDINARY";
}
