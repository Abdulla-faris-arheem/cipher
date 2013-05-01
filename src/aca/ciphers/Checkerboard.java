package aca.ciphers;
import java.util.ArrayList;
import java.util.HashMap;
import aca.util.Pair;
//import java.util.Random;

public class Checkerboard implements Cipher {
	
	  public Checkerboard()
	  {
		  
	  }
	  
	 /* public Checkerboard(boolean comp)
	  {
		  complex=comp;
	  }*/
	  
	  public boolean key_need()
	  {
		    return need_key;
	 }
		    
		 public int get_key_num()
		 {
		    return 3;
		 }
		    
		 public ArrayList<Integer> get_key_len()
		 {
		     ArrayList<Integer> result=new ArrayList<Integer>();
		     result.add(5);
		     result.add(5);
		     result.add(-1);
		     return result;
		 }
	  
	  public Checkerboard(ArrayList<String> keys)
	  {
		  this.h_key1=keys.get(0).toUpperCase();
		  this.v_key1=keys.get(1).toUpperCase();
		  this.keyword=keys.get(2).toUpperCase();
	  }
	  
	  public Checkerboard(String keyword,String key1,String key2)
	  {
		  this.keyword=keyword.toUpperCase();
		  this.h_key1=key1.toUpperCase();
		  this.v_key1=key2.toUpperCase();
		  this.multiple_key=false;
	  }
	  
	  //Can use multiple pair of keys and choose randomly. Here we only implement at most two
	  //keys.
	/*  public Checkerboard(String keyword, String hk1,String hk2, String vk1, String vk2)
	  {
		  this.keyword=keyword.toUpperCase();
		  this.h_key1=hk1.toUpperCase();
		//  this.h_key2=hk2.toUpperCase();
		  this.v_key1=vk1.toUpperCase();
		 // this.v_key2=vk2.toUpperCase();
		  this.multiple_key=true;
	  }*/
	  
	  private String v_key1="WHITE";//apply to the columns
	 // private String v_key2="GRAYS";
	  private String h_key1="BLACK";//apply to the lines
	 // private String h_key2="HORSE";
	  private String keyword="KNIGHT";
	  private boolean multiple_key=false;
	  
	  
	  
	//  public static char[][] polybius={{'K','N','I','G','H'},{'P','Q','R','S','T'},
	//	  {'O','Y','Z','U','A'},{'M','X','W','V','B'},{'L','F','E','D','C'}};
	  
	
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
		  sb.append(get_ct_char(row_num,true));
		  sb.append(get_ct_char(col_num,false));
		  return sb.toString();
	  }
	  
	  private char get_ct_char(int num, boolean row)
	  {
		  if(!multiple_key)
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
			 // Random random_gen=new Random();
			  //temporary
			 return 'X'; 
			  
		  }
	  }
	  
	  public HashMap<Character,Pair<Integer>> build_index_hash(char[][] square)
	  {
		  HashMap<Character,Pair<Integer>> result=new HashMap<Character,Pair<Integer>>();
		  int row=square.length;
		  int col=square[0].length;
		  for (int i=0;i<row;i++)
		  {
			  for(int j=0;j<col;j++)
			  {
				  Character cur_c=Character.valueOf(square[i][j]);
				  Pair<Integer> p=new Pair<Integer>(i,j);
				  result.put(cur_c, p);
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
	    
	    public static boolean need_key=true;
	    
	//    boolean complex=false;
	    
	    public static int key_num=3; 
	    
	    public int process_id()
		{
			return 2;
		}

}
