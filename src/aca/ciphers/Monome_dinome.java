package aca.ciphers;

import java.util.ArrayList;
//import java.util.Random;
import java.util.Hashtable;
//import aca.util.Pair;

import aca.util.Generic_Func;

public class Monome_dinome implements Cipher {
	
	public Monome_dinome()
	{
		key="NOTARIES";
	}
	
	public Monome_dinome(String k)
	{
		this.key=k;
	}
	
	private String key;
	
	public String encode(String plain)
	 {
		int[] perm=Generic_Func.generate_random_perm(0, 10);
		//int[] perm={7,3,4,5,9,0,6,2,1,8};
		Hashtable<Character,String> h_map=build_box(perm, key);
		StringBuilder sb=new StringBuilder();
		String plain_u=plain.toUpperCase();
		for(int i=0;i<plain_u.length();i++)
		{
			char cur_c=plain_u.charAt(i);
			if(cur_c=='J')
			{
				cur_c='I';
			}
			else if(cur_c=='Z')
			{
				cur_c='Y';
			}
			sb.append(h_map.get(cur_c));
		}
		//Random r=new Random();
		//int key_len=1+r.nextInt(7);
		
		return sb.toString();
	 }
	
	private Hashtable<Character,String> build_box(int[] perm, String k)
	{
	//	char[][] box=new char[3][8];
		int[] row=new int[2];
		row[0]=perm[0];
		row[1]=perm[1];
		int[] col=new int[8];
		for(int i=0;i<8;i++)
		{
			col[i]=perm[i+2];
		}
	    boolean[] filled=new boolean[26];
	    String k_u=k.toUpperCase();
	    Hashtable<Character,String> result=new Hashtable<Character,String>();
	    int cur_row=0;
	    int cur_col=0;
	    for(int i=0;i<k_u.length();i++)
	    {
	    	char cur_c=k_u.charAt(i);
	    	if(cur_c=='J')
	    	{
	    		cur_c='I';
	    	}
	    	else if(cur_c=='Z')
	    	{
	    		cur_c='Y';
	    	}
	    	int pos=cur_c-'A';
	    	if(!filled[pos])
	    	{
	    		String r;
	    		if(cur_row==0)
	    		{
	    		  r=Integer.toString(col[cur_col]);
	    		}
	    		else
	    		{
	    		  r=Integer.toString(row[cur_row-1])+Integer.toString(col[cur_col]);
	    		}
	    		result.put(Character.valueOf(cur_c), r);
	    		if(cur_col==7)
	    		{
	    			cur_row++;
	    			cur_col=0;
	    		}
	    		else
	    		{
	    			cur_col++;
	    		}
	    		filled[pos]=true;
	    	}
	    	
	    }
	    for(int i=0;i<26;i++)
	    {
	    	if(i==25 || i==9)
	    		continue;
	    	if(!filled[i])
	    	{
	    		String r;
	    		if(cur_row==0)
	    		{
	    		  r=Integer.toString(col[cur_col]);
	    		}
	    		else
	    		{
	    		  r=Integer.toString(row[cur_row-1])+Integer.toString(col[cur_col]);
	    		}
	    		char cur_c=(char)('A'+i);
	    		result.put(Character.valueOf(cur_c), r);
	    		if(cur_col==7)
	    		{
	    			cur_row++;
	    			cur_col=0;
	    		}
	    		else
	    		{
	    			cur_col++;
	    		}
	    		filled[i]=true;
	    	}
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
	    	return true;
	    }
	    
	    public int get_key_num()
	    {
	    	return 1;
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
