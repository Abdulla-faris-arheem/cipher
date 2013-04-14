package aca.ciphers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import aca.util.Pair;

public class Seriated_playfair extends Playfair {
	public Seriated_playfair()
	{
		key="LOGARITHM";
		Random r=new Random();
		period=r.nextInt(15);
	}
	
	public Seriated_playfair(String k)
	{
		key=k;
		Random r=new Random();
		period=r.nextInt(15);
	}
	
	public Seriated_playfair(String k,int p)
	{
		key=k;
		period=p;
	}
	
	private String key;
	private int period;
	
	public String encode(String plain)
	 {
		char[][] square=new char[5][5];
		Two_square.build_square(square, key);
		String plain_u=plain.toUpperCase();
		HashMap<Character,Pair<Integer>> h_map=BIFID.build_map(square);
		if(plain_u.length()%2!=0)
		{
			plain_u+="X";
		}
	//	plain_u="COMEQUICKLYWENEEDHXELPIMMEDIATELYTOM";
		//char[][] pt=new char[2][plain_u.length()/2];
		char[][] ct=new char[2][plain_u.length()/2];
		for(int i=0;i<plain_u.length();i+=period*2)
		{
			int end=i+period*2>plain_u.length()?plain_u.length():i+period*2;
			fill_ct_sq(plain_u,ct,i,end,h_map,square);
		}
        String result=read_block(ct);
		return result;
	 }
	
	private void fill_ct_sq(String plain,char[][] ct, int start,int end,HashMap<Character,Pair<Integer>> h_map,char[][] square)
	{
		//int block_period=(end-start)/2;
		int block_id=start/(ct.length*period);
		for(int i=start;i<end;i++)
		{
			int block_pos=i%period;
			if(i+period>=end)
				break;
			StringBuilder sb=new StringBuilder();
			if(plain.charAt(i)==plain.charAt(i+period))
				continue;
			sb.append(plain.charAt(i));
			sb.append(plain.charAt(i+period));
			String result=get_sub_ct(sb.toString(),h_map,square);
			ct[0][period*block_id+block_pos]=result.charAt(0);
			ct[1][period*block_id+block_pos]=result.charAt(1);
		}
	}
	
	private String read_block(char[][] ct)
	{
		//read the block horizontally 
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<ct[0].length;i+=period)
		{
			int end=i+period>ct[0].length?ct[0].length:i+period;
			for(int j=0;j<ct.length;j++)
			{
				for(int k=i;k<end;k++)
				{
					if(ct[j][k]!='\0')
					{
					 sb.append(ct[j][k]);
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
