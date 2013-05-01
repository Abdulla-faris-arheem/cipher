package aca.ciphers;

import java.util.ArrayList;
//import java.util.Random;
import aca.util.Pair;

public class Portax implements Cipher {
	public Portax()
	{
		key="EASY";
	}
	
	public Portax(String k)
	{
		key=k;
	}
	
	String key;
	public String encode(String plain)
	 {
		//int period=key.length();
		String plain_u=plain.toUpperCase();
		int left=plain_u.length()%(key.length()*2);
		int add_x=0;
        if(left!=0)
        {
        	add_x=key.length()*2-left;
        }
        for(int i=0;i<add_x;i++)
        {
        	plain_u+='X';
        }
		char[][] block=Incomp_column.build_block(plain_u, -1,key.length(), 0);
		char[][] result_block=new char[block.length][block[0].length];
		for(int i=0;i<block[0].length;i++)
		{
			//encipher by column
			for(int j=0;j<block.length;j+=2)
			{
				Pair<Character> p=get_ct(block[j][i],block[j+1][i],key.charAt(i)); 
				result_block[j][i]=p.get_first();
				result_block[j+1][i]=p.get_second();
			}
		}
		//Random r=new Random();
		//int key_len=1+r.nextInt(7);
		String r=read_block(result_block, true);
		return r;
	 }
	
	private Pair<Character> get_ct(char c1, char c2, char key_char)
	{
		int k_pos=(key_char-'A')/2;//range: 0-12 slide size
		if(c1<='M')
		{
			//locate in the fixed alphabet
			int c1_pos=c1-'A';//c1 pos from 'A'
			int c2_pos=(c2-'A')/2-k_pos; //c2 pos from 'A'
			if(c2_pos<0)
			{
				c2_pos+=13;
			}
			if(c1_pos==c2_pos)
			{
				int r_c1_pos=(13+k_pos+c1_pos)%13;
				char r_c1=(char)('N'+r_c1_pos);
				//if(r_c1)
				char r_c2;
				if((c2-'A')%2==0)
					r_c2=(char)(c2+1);
				else
					r_c2=(char)(c2-1);
				Pair<Character> result=new Pair<Character>(r_c1,r_c2);
				return result;
			}
			else
			{
				//rectangle
				int r_c1_pos=(c2-'A')/2-k_pos;//c2_pos
				if(r_c1_pos<0)
				{
					r_c1_pos+=13;
				}
				char r_c1=(char)(r_c1_pos+'A');
				int r_c2_pos=(c1_pos+k_pos+13)%13;
				
				char r_c2;
				if((c2-'A')%2==0)
				   r_c2=(char)(r_c2_pos*2+'A');
				else
				{
					r_c2=(char)(r_c2_pos*2+'A'+1);
				}
				Pair<Character> result=new Pair<Character>(r_c1,r_c2);
				return result;
			}
		}
		else
		{
			int c1_pos=c1-k_pos-'N';
			if(c1_pos<0)
			{
				c1_pos+=13;
			}
			int c2_pos=(c2-'A')/2-k_pos;
			if(c2_pos<0)
			{
				c2_pos+=13;
			}
			if(c1_pos==c2_pos)
			{
				char r_c1=(char)('A'+c1_pos);
				char r_c2;
				if((c2-'A')%2==0)
					r_c2=(char)(c2+1);
				else
					r_c2=(char)(c2-1);
				Pair<Character> result=new Pair<Character>(r_c1,r_c2);
				return result;
			}
			else
			{
				int r_c1_pos=(k_pos+c2_pos+13)%13;				
				char r_c1=(char)('N'+r_c1_pos);
				int r_c2_pos=(k_pos+c1_pos+13)%13;
				char r_c2;
				if((c2-'A')%2==0)
				{
					r_c2=(char)('A'+r_c2_pos*2);
				}
				else
				{
				  r_c2=(char)('A'+r_c2_pos*2+1);
				}
				Pair<Character> result=new Pair<Character>(r_c1,r_c2);
				return result;
			}
			
		
		}
		//return null;
	}
	
	/**
	 * Read a rectangle block.
	 * 
	 * @param block the block to be read from
	 * @param row read horizontally (true) or vertically(false)
	 * @return
	 */
	public static String read_block(char[][] block, boolean row)
	{
		StringBuilder sb=new StringBuilder();
		int r=block.length;
		int c=block[0].length;
		if(row)
		{
			for(int i=0;i<r;i++)
			{
				for(int j=0;j<c;j++)
				{
					if(block[i][j]!='\0')
					{
					  sb.append(block[i][j]);
					}
				}
			}
		}
		else
		{
			for(int j=0;j<c;j++)
			{
				for(int i=0;i<r;i++)
				{
					if(block[i][j]!='\0')
					{
					  sb.append(block[i][j]);
					}
				}
			}
		}
		return sb.toString();
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
