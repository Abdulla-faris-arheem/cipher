package aca.ciphers;

import java.util.ArrayList;
import java.util.Random;
import java.util.HashMap;
import aca.util.Pair;

public class Grandpre implements Cipher {
	
	public Grandpre()
	{
		char[][] sq={{'L','A','D','Y','B','U','G','S'},{'A','Z','I','M','U','T','H','S'},
				{'C','A','L','F','S','K','I','N'},{'Q','U','A','C','K','I','S','H'},
				{'U','N','J','O','V','I','A','L'},{'E','V','U','L','S','I','O','N'},
				{'R','O','W','D','Y','I','S','M'},{'S','E','X','T','U','P','L','Y'}};
		fill_square(key_square,sq);
	}
	
	public Grandpre(char[][] square)
	{
		fill_square(key_square,square);
	}
	
	public void fill_square(char[][] tgt_sq, char[][] src_sq)
	{
	    assert(tgt_sq.length==src_sq.length);
	    assert(tgt_sq[0].length==src_sq[0].length);
	    int row=tgt_sq.length;
	    int col=tgt_sq[0].length;
	    for(int i=0;i<row;i++)
	    {
	    	for(int j=0;j<col;j++)
	    	{
	    		tgt_sq[i][j]=src_sq[i][j];
	    	    Character cur_c=Character.valueOf(Character.toUpperCase(tgt_sq[i][j]));
	    	    if(h_map.containsKey(cur_c))
	    	    {
	    	    	ArrayList<Pair<Integer>> cur_l=h_map.get(cur_c);
	    	    	cur_l.add(new Pair<Integer>(i,j));
	    	    	h_map.put(cur_c, cur_l);
	    	    }
	    	    else
	    	    {
	    	    	ArrayList<Pair<Integer>> p=new ArrayList<Pair<Integer>>();
	    	    	p.add(new Pair<Integer>(i,j));
	    	    	h_map.put(cur_c, p);
	    	    }
	    	}
	    }
	}
	
	public String encode(String plain)
	 {
		String plain_u=plain.toUpperCase();
		// HashMap<Character,Pair<Integer>> h=BIFID.build_map(key_square);
		StringBuilder sb=new StringBuilder();
		 for(int i=0;i<plain.length();i++)
		 {
			 char cur_c=plain_u.charAt(i);
			 ArrayList<Pair<Integer>> arr=h_map.get(cur_c);
			 Random r=new Random();
			 int pick=r.nextInt(arr.size());
			 sb.append(arr.get(pick).get_first()+1);
			 sb.append(arr.get(pick).get_second()+1);
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
	    
	    private char[][] key_square=new char[8][8];
	    private HashMap<Character,ArrayList<Pair<Integer>> > h_map=new HashMap<Character,ArrayList<Pair<Integer>> >();
}
