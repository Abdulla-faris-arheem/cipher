package aca.ciphers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import aca.util.Pair;

public class Double_checkerboard extends Checkerboard {
	 public int get_key_num()
	 {
	    return 5;
	 }
	 
	 public ArrayList<Integer> get_key_len()
	 {
	     ArrayList<Integer> result=new ArrayList<Integer>();
	     result.add(5);
	     result.add(5);
	     result.add(5);
	     result.add(5);
	     result.add(-1);
	     return result;
	 }
	 
	 public Double_checkerboard()
	 {
		
	 }
	 
	 public Double_checkerboard(ArrayList<String> keys)
	 {
		
	      this.h_key1=keys.get(0).toUpperCase();
		  this.v_key1=keys.get(1).toUpperCase();
		  this.h_key2=keys.get(2).toUpperCase();
		  this.v_key2=keys.get(3).toUpperCase();
		  this.keyword=keys.get(4).toUpperCase();
	 }
	 
	 public String encode(String plain)
	  {
		  char[][] polybius=BIFID.generate_bifid_square(this.keyword);
		  
		  StringBuilder sb=new StringBuilder();
		  HashMap<Character,Pair<Integer> > r_c=build_index_hash(polybius); 
		  for(int i=0;i<plain.length();i++)
		  {
			  sb.append(get_cipher_text(r_c,plain.charAt(i)));
		  }
		  return sb.toString();
	  }
	 
	  public String get_cipher_text(HashMap<Character,Pair<Integer>> r_c,char a)
	  {
		  char cur_char=(char)(a-'a'+'A');
		  if(cur_char=='J')
		  {
			  cur_char='I';
		  }
		/*  if(!r_c.containsKey(Character.valueOf()))
		  {
			  System.err.println("Error");
		  }*/
		  int row_num=r_c.get(Character.valueOf(cur_char)).get_first();
		  int col_num=r_c.get(Character.valueOf(cur_char)).get_second();
		  StringBuilder sb=new StringBuilder();
		  //sb.append(get_ct_pair(row_num,col_num));
		  sb.append(get_ct_char(row_num,true));
		  sb.append(get_ct_char(col_num,false));
		  return sb.toString();
	  }
	 
	  private char get_ct_char(int num,boolean row)
	  {
		  Random r=new Random();
		  int pick=r.nextInt(2);
	      if(pick==0)
	      {
	    	  if(row)
		       {
		    	   return h_key1.charAt(num);
		       }
		       else
		       {
		    	   return v_key1.charAt(num);
		       }
	      }
	       else
	       {
	    	   if(row)
		       {
		    	   return h_key2.charAt(num);
		       }
		       else
		       {
		    	   return v_key2.charAt(num);
		       }
	       
		  }
	  }
	 
	 private String h_key1="BLACK";
	 private String v_key1="WHITE";
	 private String h_key2="HORSE";
	 private String v_key2="GRAYS";
	 private String keyword="KNIGHT";
	 
}
