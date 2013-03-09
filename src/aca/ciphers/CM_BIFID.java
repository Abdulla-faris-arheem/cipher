package aca.ciphers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import aca.util.Pair;

public class CM_BIFID extends BIFID {
	
	 public CM_BIFID()
	 {
		 keys.add("EXTRAORDINARY");
		 keys.add("NOVELTY");
		 Random random_gen=new Random();
		 this.period=3+random_gen.nextInt(12);
	 }
	 
	 public CM_BIFID(int p)
	 {
		 keys.add("EXTRAORDINARY");
		 keys.add("NOVELTY");
		 this.period=p;
	 }
	 
	 public CM_BIFID(ArrayList<String> key)
	 {
		 keys.clear();
		for(int i=0;i<key.size();i++)
		{
			keys.add(key.get(i));
		}
		 Random random_gen=new Random();
		 this.period=3+random_gen.nextInt(12);
	 }
	 
	 public boolean key_need()
	  {
		    return need_key;
	 }
		    
		 public int get_key_num()
		 {
		    return 2;
		 }
		    
		 public ArrayList<Integer> get_key_len()
		 {
			 return null;
		 }
	 
	 public CM_BIFID(ArrayList<String> key,int p)
	 {
		 keys.clear();
			for(int i=0;i<key.size();i++)
			{
				keys.add(key.get(i));
			}
			this.period=p;
	 }
	 
	 /*
	  * Generate a polybius square from left to right (alternating vertical way)
	  */
	 public static char[][] generate_square(String key)
	 {
		 char[][] result=new char[5][5];
		 boolean[] filled=new boolean[26];
		 String key_l=key.toUpperCase();
		 int cur_row=0;
		 int cur_col=0;
		 for(int i=0;i<key_l.length();i++)
		 {
			 char cur_c=key_l.charAt(i);
			 if(cur_c=='J')
			 {
				 cur_c='I';
			 }
			 int order=cur_c-'A';
			 if(!filled[order])
			 {
				 result[cur_row][cur_col]=cur_c;
				 filled[order]=true;
			 }
			 else
			 {
				 continue;
			 }
			 if(cur_col%2==1)
			 {
				 //going upwards
				 if(cur_row==0)
				 {
					 cur_col++;
				 }
				 else
				 {
					 cur_row--;
				 }
			 }
			 else
			 {
				 //going downwards
				 if(cur_row==4)
				 {
					 cur_col++;
				 }
				 else
				 {
					 cur_row++;
				 }
			 }
		 }
		 for(int i=0;i<26;i++)
		 {
			 char cur_c=(char)('A'+i);
			 if(cur_c=='J')
				 continue;
			 if(!filled[i])
			 {
				 result[cur_row][cur_col]=cur_c;
				 filled[i]=true;
			 }
			 else
			 {
				 continue;
			 }
			 if(cur_col%2==1)
			 {
				 //going upwards
				 if(cur_row==0)
				 {
					 cur_col++;
				 }
				 else
				 {
					 cur_row--;
				 }
			 }
			 else
			 {
				 //going downwards
				 if(cur_row==4)
				 {
					 cur_col++;
				 }
				 else
				 {
					 cur_row++;
				 }
			 }
		 }
		 return result;
	 }
	
	
	 public String encode(String plain)
	    {
	    	//generate polymbius square
	    	char[][] poly_b=BIFID.generate_bifid_square(keys.get(0));
	    	char[][] poly_b2=CM_BIFID.generate_square(keys.get(1));
	    	HashMap<Character,Pair<Integer>> m=build_map(poly_b);
	    	//HashMap<Character,Pair<Integer>> m2=build_map(poly_b2);
	    	String plain_u=plain.toUpperCase();
	    	ArrayList<String> sub=new ArrayList<String>();
	    	
	    	int start_pos=0;
	    	int plain_size=plain_u.length();
	    	while(start_pos<plain_size)
	    	{
	    		int end_pos=start_pos+this.period<plain_size?start_pos+this.period:plain_size;
	    		sub.add(plain_u.substring(start_pos,end_pos));
	    		start_pos=end_pos;
	    	}
	    	StringBuilder b=new StringBuilder();
	    	
	    	for(String s:sub)
	    	{
	    		String sub_cipher=cipher_sub(poly_b,poly_b2,m,s);
	    		b.append(sub_cipher);
	    	}
	  /*  	for(char c:plain.toUpperCase().toCharArray())
	    	{
	    		Pair<Integer> pos=m.get(c);
	    		
	    	}*/
	    	return b.toString();
	    }
	 
	 private String cipher_sub(char[][] square1,char[][] square2, HashMap<Character,Pair<Integer>> h1,String p)
	 {
		 String p_u=p.toUpperCase();
		 ArrayList<Integer> row=new ArrayList<Integer>();
		 ArrayList<Integer> col=new ArrayList<Integer>();
		 for(int i=0;i<p_u.length();i++)
		 {
			 char cur_c=p_u.charAt(i);
			 if(cur_c=='J')
			 {
				 cur_c='I';
			 }
			 Character k=Character.valueOf(cur_c);
			 if(!h1.containsKey(k))
			 {
				 System.err.println("Cannot find character in polybius;");
				 return null;
			 }
			 int cur_r=h1.get(k).get_first();
			 int cur_col=h1.get(k).get_second();
			 row.add(cur_r);
			 col.add(cur_col);
		 }
		row.addAll(col);
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<row.size();i+=2)
		{
			sb.append(square2[row.get(i)][row.get(i+1)]);
		}
		return sb.toString();
	 }
	    
	 public static boolean need_key=true;
	// private String key1="EXTRAORDINARY";
	// private String key2="NOVELTY";
	 private ArrayList<String> keys=new ArrayList<String>();
  //   private static int key_num=2;
     private int period=7;
}
