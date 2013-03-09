package aca.ciphers;

import java.util.ArrayList;
import java.util.Random;
import java.util.HashMap;
import aca.util.Pair;

public class Digrafid implements Cipher {
	
	public Digrafid()
	{
		Random r=new Random();
		period=r.nextInt(15);
		keys.add("KEYWORD");
		keys.add("VERTICAL");
		build_rec(rec1,h1,keys.get(0),true);
		build_rec(rec2,h2,keys.get(1),false);
	}
	
	public Digrafid(int p)
	{
		period=p;
		keys.add("KEYWORD");
		keys.add("VERTICAL");
		build_rec(rec1,h1,keys.get(0),true);
		build_rec(rec2,h2,keys.get(1),false);
	}
	
	public Digrafid(ArrayList<String> ks)
	{
		if(ks.size()<2)
		{
			System.err.println("Not enough keys");
		}
		for(int i=0;i<ks.size();i++)
		{
			keys.add(ks.get(i));
		}
		build_rec(rec1,h1,keys.get(0),true);
		build_rec(rec2,h2,keys.get(1),false);
		Random r=new Random();
		period=r.nextInt(15);
	}
	
	public void build_rec(char[][] rec, HashMap<Character,Pair<Integer>> map, String key,boolean up)
	{
		String key_n=key.toUpperCase();
		if(!up)
		{
			key_n=key.toLowerCase();
		}
		boolean[] filled=new boolean[26];
		int row=0;
		int col=0;
		for(char c:key_n.toCharArray())
		{
			int pos=c-'A';
			if(!up)
			{
				pos=c-'a';
			}
			if(!filled[pos])
			{
				filled[pos]=true;
				rec[row][col]=c;
				map.put(Character.valueOf(c), new Pair<Integer>(row,col));
				
			}
			else
			{
				continue;
			}
			if(up)
			{
			  if(col==8)
			  {
				  col=0;
				  row++;
			  }
			  else
			  {
				  col++;
			  }
			}
			else
			{
				if(row==8)
				{
					row=0;
					col++;
				}
				else
				{
					row++;
				}
			}
		}
		for(int i=0;i<26;i++)
		{
			//int pos=c-'A';
			if(!filled[i])
			{
				filled[i]=true;
				rec[row][col]=(char)(i+'A');
				if(!up)
				{
					rec[row][col]=(char)(i+'a');
				}
				map.put(Character.valueOf(rec[row][col]), new Pair<Integer>(row,col));
			}
			else
			{
				continue;
			}
			if(up)
			{
			  if(col==8)
			  {
				  col=0;
				  row++;
			  }
			  else
			  {
				  col++;
			  }
			}
			else
			{
				if(row==8)
				{
					row=0;
					col++;
				}
				else
				{
					row++;
				}
			}
		}
		
	}
	 
     public String encode(String plain)
     {
    	String p=plain.toLowerCase();
    	if(plain.length()/2==1)
    	{
    		p+="x";
    	}
    	int total_len=p.length();
    	int pos=0;
    	StringBuilder sb=new StringBuilder();
    	while(pos<total_len)
    	{
    		int end_pos=(pos+period*2)>total_len?total_len:pos+period*2;
    		sb.append(cipher_sub(p.substring(pos,end_pos)));
    		pos=end_pos;
    	}
    	return sb.toString(); 
     }
     
     public String cipher_sub(String sub)
     {
    	 StringBuilder sb=new StringBuilder();
    	 int[][] codes=new int[3][sub.length()/2];
    	
    	 for(int i=0;i<sub.length();i+=2)
    	 {
    		 char c1=(char)(sub.charAt(i)-'a'+'A');
    		 char c2=sub.charAt(i+1);
    		 Pair<Integer> cp1=h1.get(c1);
    		 codes[0][i/2]=cp1.get_second()+1;
    		// sb.append(cp1.get_second()+1);
    		 Pair<Integer> cp2=h2.get(c2);
    		 codes[1][i/2]=mid_square[cp1.get_first()][cp2.get_second()];
    		 codes[2][i/2]=cp2.get_first()+1;
    		// sb.append(mid_square[cp1.get_first()][cp2.get_second()]);
    		// sb.append(cp2.get_first()+1);
    	 }
    	 ArrayList<Integer> triple=new ArrayList<Integer>();
    	 for(int i=0;i<3;i++)
    	 {
    		 for(int j=0;j<sub.length()/2;j++)
    		 {
    			 if(triple.size()<3)
    			 {
    			   triple.add(codes[i][j]);
    			 }
    			 else
    			 {
    				 sb.append(find_ct(triple));
    				 triple.clear();
    				 triple.add(codes[i][j]);
    			 }
    		 }
    	 }
    	if(triple.size()==3)
    	{
    		sb.append(find_ct(triple));
    	}
    	 return sb.toString();
     }
     
     public String find_ct(ArrayList<Integer> tri)
     {
    	 assert(tri.size()==3);
    	 StringBuilder sb=new StringBuilder();
    	 Pair<Integer> p=get_pos(tri.get(1));
    	 sb.append(rec1[p.get_first()][tri.get(0)-1]);
    	 sb.append(rec2[tri.get(2)-1][p.get_second()]);
    	 return sb.toString();
     }
     
     /*
      * Get the row and col information from the 
      */
     public Pair<Integer> get_pos(int n)
     {
    	 int row=(n-1)/3;
    	 int col=(n-1)%3;
    	 return new Pair<Integer>(row,col);
     }
    
    /*
     * Decode the cipher text.
     */
    public String decode(String cipher)
    {
    	return null;
    }
    
    private char[][] rec1=new char[3][9];
    private char[][] rec2=new char[9][3];
    private HashMap<Character,Pair<Integer>> h1=new HashMap<Character,Pair<Integer>>();
    private HashMap<Character,Pair<Integer>> h2=new HashMap<Character,Pair<Integer>>();
    private int[][] mid_square={{1,2,3},{4,5,6},{7,8,9}};
    
    public boolean need_key=true; //need to generate a string key
    
    public static int key_num=2;  
    
    public int period=3;
    
    public ArrayList<String> keys=new ArrayList<String>();
    
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
}
