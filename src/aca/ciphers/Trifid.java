package aca.ciphers;

import java.util.ArrayList;
import java.util.Random;
import java.util.HashMap;

public class Trifid implements Cipher {
	
	public Trifid()
	{
		Random r=new Random();
		period=5+r.nextInt(10);
	}
	
	public Trifid(int p)
	{
		period=p;
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
	
	public Trifid(int p, String key)
	{
		keyword=key.toUpperCase();
		period=p;
	}
	
	public Trifid(String key)
	{
		keyword=key;
		Random r=new Random();
		period=5+r.nextInt(10);
	}
	
    private int period=10;
    private String keyword="EXTRAORDINARY";
    
   public String encode(String plain)
   {
	    HashMap<Character,String> map1=new HashMap<Character,String>();
	    HashMap<String,Character> map2=new HashMap<String,Character>();
	    build_map(map1,map2);
	    StringBuilder sb=new StringBuilder();
	    int pos=0;
	    String plain_u=plain.toUpperCase();
	    while(pos<plain_u.length())
	    {
	    	int end_pos=pos+period;
	    	end_pos=(end_pos>plain_u.length())?plain_u.length():end_pos;
	    	String sub=plain_u.substring(pos, end_pos);
	    	sb.append(cipher_sub(map1,map2,sub));
	    	pos=end_pos;
	    }
	    return sb.toString();
   }
   
   private String cipher_sub(HashMap<Character,String> map1, HashMap<String,Character> map2,String sub_s)
   {
	   char[][] code=new char[3][sub_s.length()];
	   for(int i=0;i<sub_s.length();i++)
	   {
		   Character c=Character.valueOf(sub_s.charAt(i));
		   String num_str=map1.get(c);
		   for(int j=0;j<3;j++)
		   {
			   code[j][i]=num_str.charAt(j);
		   }
		   
	   }
	   StringBuilder sb=new StringBuilder();
	   for(int j=0;j<3;j++)
	   {
	     for(int i=0;i<sub_s.length();i++)
	     {
		    sb.append(code[j][i]);
	     }
	   }
	   String total_str=sb.toString();
	   StringBuilder sb2=new StringBuilder();
	   int cur_pos=0;
	   while(cur_pos<total_str.length())
	   {
		   String cur_sub=total_str.substring(cur_pos,cur_pos+3);
		   sb2.append(map2.get(cur_sub));
		   cur_pos+=3;
	   }
	   return sb2.toString();
	 //  int row=0;
	  // int col=0;
	  /* StringBuilder sb=new StringBuilder();
	   while(true)
	   {
		  sb.append(code[row][col]);
		  sb.
	   }*/
   }
    
   private void build_map(HashMap<Character,String> m1,HashMap<String,Character> m2)
   {
	   int i=1;
	   int j=1;
	   int k=1;
	   boolean[] filled=new boolean[26];
	   for(int m=0;m<keyword.length();m++)
	   {
		   char cur_c=keyword.charAt(m);
		   int char_pos=cur_c-'A';
		   if(!filled[char_pos])
		   {
			   String tri=generate_triple_str(i,j,k);
			   filled[char_pos]=true;
			   m1.put(Character.valueOf(cur_c),tri);
			   m2.put(tri,Character.valueOf(cur_c));
		   }
		   else
		   {
			   continue;
		   }
		   if(k==3)
		   {
			   if(j==3)
			   {
				   i++;
				   j=1;
				   k=1;
			   }
			   else
			   {
				   j++;
				   k=1;
			   }
		   }
		   else
		   {
			   k++;
		   }
	   }
	   for(int m=0;m<26;m++)
	   {
		   char cur_c=(char)('A'+m);
		  // int char_pos='A'+m;
		   if(!filled[m])
		   {
			   String tri=generate_triple_str(i,j,k);
			   filled[m]=true;
			   m1.put(Character.valueOf(cur_c),tri);
			   m2.put(tri,Character.valueOf(cur_c));
		   }
		   else
		   {
			   continue;
		   }
		   if(k==3)
		   {
			   if(j==3)
			   {
				   i++;
				   j=1;
				   k=1;
			   }
			   else
			   {
				   j++;
				   k=1;
			   }
		   }
		   else
		   {
			   k++;
		   }
	   }
	   String tri=generate_triple_str(i,j,k);//3,3,3
	   m1.put('#',tri);
	   m2.put(tri,'#');
	   
	   
	   
   }
   
   private String generate_triple_str(int i,int j,int k)
   {
	   StringBuilder sb=new StringBuilder();
	   sb.append(i);
	   sb.append(j);
	   sb.append(k);
	   return sb.toString();
   }
    /*
     * Decode the cipher text.
     */
    public String decode(String cipher)
    {
    	return null;
    }
    
    public boolean need_key=true; //need to generate a string key
    
    public int key_num=1;
    
    public int process_id()
	{
		return 2;
	}
}
