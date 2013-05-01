package aca.ciphers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import aca.util.Pair;

public class Tri_square implements Cipher {
	
	public Tri_square()
	{
		String key1="NOVELS";
		String key2="READING";
		String key3="PASTTIME";
		keys.add(key1);
		keys.add(key2);
		keys.add(key3);
		Foursquare.build_key_square(square1,key1);
		Two_square.build_square(square2,key2);
		square3=BIFID.generate_bifid_square(key3);
	}
	
	
	public boolean key_need()
	  {
		    return true;
	 }
		    
		 public int get_key_num()
		 {
		    return 3;
		 }
		    
		 public ArrayList<Integer> get_key_len()
		 {
			 return null;
		 }
	
	
	
	
	
	private ArrayList<String> keys=new ArrayList<String>();
	private char[][] square1=new char[5][5];
	private char[][] square2=new char[5][5];
	private char[][] square3=new char[5][5];
	
	public Tri_square(ArrayList<String> keys)
	{
		if(keys.size()<3)
		{
			System.err.println("Not enough keys");
			return;
		}
		for(int i=0;i<keys.size();i++)
		{
			this.keys.add(keys.get(i));
		}
		Foursquare.build_key_square(square1,this.keys.get(0));
		Two_square.build_square(square2,this.keys.get(1));
		square3=BIFID.generate_bifid_square(this.keys.get(2));
	}
	
	 public String encode(String plain)
     {
		String p=plain.toUpperCase();
		if(plain.length()%2==1)
		{
			p+="X";
		}
		HashMap<Character,Pair<Integer>> h1=BIFID.build_map(square1);
		HashMap<Character,Pair<Integer>> h2=BIFID.build_map(square2);
		HashMap<Character,Pair<Integer>> h3=BIFID.build_map(square3);
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<p.length();i+=2)
		{
			sb.append(cipher_digraph(p.substring(i, i+2),h1,h2,h3));
		}
    	return sb.toString(); 
     }
	 
	 private String cipher_digraph(String pl,HashMap<Character,Pair<Integer> > h1, HashMap<Character,Pair<Integer>> h2, HashMap<Character,Pair<Integer>> h3)
	 {
		 
		 assert(pl.length()==2);
		 StringBuilder sb=new StringBuilder();
		 char c1=pl.charAt(0);
		 char c2=pl.charAt(1);
		 if(c1=='J')
		 {
			 c1='I';
		 }
		 if(c2=='J')
		 {
			 c2='I';
		 }
		 Pair<Integer> p1=h1.get(Character.valueOf(c1));
		 Pair<Integer> p2=h2.get(Character.valueOf(c2));
		 Random r=new Random();
		 int c1_row=p1.get_first();
		 int c1_col=p1.get_second();
		 int c2_row=p2.get_first();
		 int c2_col=p2.get_second();
		 int rand=r.nextInt(4)+1;
		 int rand2=r.nextInt(4)+1;
		 int ct1_row=(c1_row+rand>4)?c1_row+rand-4:c1_row+rand;
		 int ct2_col=(c2_col+rand2>4)?c2_col+rand2-4:c2_col+rand2;
		 sb.append(square1[ct1_row][c1_col]);
		 sb.append(square3[c1_row][c2_col]);
		 sb.append(square2[c2_row][ct2_col]);
		 return sb.toString();
		/* if(p1.get_first()==p2.get_first())
		 {
			 sb.append(c2);
			 sb.append(c1);
			 return sb.toString();
		 }
		 else
		 {
			 sb.append(square2[p1.get_first()][p2.get_second()]);
			 sb.append(square1[p2.get_first()][p1.get_second()]);
			 return sb.toString();
		 }*/
	}
    
    /*
     * Decode the cipher text.
     */
    public String decode(String cipher)
    {
    	return null;
    }
    
    public static boolean need_key=true; //need to generate a string key
    
    public static int key_num=3; 
    
    public int process_id()
	{
		return 2;
	}

}
