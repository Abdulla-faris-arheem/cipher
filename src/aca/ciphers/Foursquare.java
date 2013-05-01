package aca.ciphers;
import java.util.ArrayList;
import aca.util.Pair;

public class Foursquare implements Cipher{

	 public Foursquare()
	 {
		 String key1="GEOMETRY";
		 String key2="LOGARITHM";
		 keys.add(key1);
		 keys.add(key2);
		 build_normal_square(square1);
		 build_key_square(square2,key1);
		 build_normal_square(square3);
		 build_key_square(square4,key2);
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
	 
	 public Foursquare(ArrayList<String> ks)
	 {
		 if(ks.size()<2)
		 {
			 System.err.println("Not enough keys");
			 return;
		 }
		 for(int i=0;i<ks.size();i++)
		 {
			 keys.add(ks.get(i));
		 }
		 String key1=keys.get(0);
		 String key2=keys.get(1);
		 keys.add(key1);
		 keys.add(key2);
		 build_normal_square(square1);
		 build_key_square(square2,key1);
		 build_normal_square(square3);
		 build_key_square(square4,key2);
	 }
	 
	 private void build_normal_square(char[][] square)
	 {
		 int cur_index=0;
		 for(int i=0;i<5;i++)
		 {
			 for(int j=0;j<5;j++)
			 {
				 if(cur_index==9)
				 {
					 cur_index++;
				 }
				 square[i][j]=(char)('A'+cur_index);
				 cur_index++;
			 }
		 }
	 }
	 
	 static void build_key_square(char[][] square,String key)
	 {
		 boolean[] filled=new boolean[26];
		 String key_u=key.toUpperCase();
		 int cur_row=0;
		 int cur_col=0;
		 for(int i=0;i<key_u.length();i++)
		 {
			 char cur_c=key_u.charAt(i);
			 if(cur_c=='J')
			 {
				 cur_c='I';
			 }
			 int order=cur_c-'A';
			 if(!filled[order])
			 {
				 square[cur_row][cur_col]=cur_c;
				 filled[order]=true;
			 }
			 else
			 {
				 continue;
			 }
			 if(cur_row==4)
			 {
				 cur_row=0;
				 cur_col++;
			 }
			 else
			 {
				 cur_row++;
			 }
		 }
		 for(int i=0;i<26;i++)
		 {
			 char cur_c=(char)('A'+i);
			 if(cur_c=='J')
				 continue;
			 if(!filled[i])
			 {
				 square[cur_row][cur_col]=cur_c;
				 filled[i]=true;
			 }
			 else
			 {
				 continue;
			 }
			 if(cur_row==4)
			 {
				 cur_row=0;
				 cur_col++;
			 }
			 else
			 {
				 cur_row++;
			 }			
		 }
	 }
	
	 public String encode(String plain)
     {
		String p=plain.toUpperCase();
		if(plain.length()%2==1)
		{
			p+="X"; //ensure the plain text has even length;
		}
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<p.length();i+=2)
		{
			sb.append(cipher_digraph(p.substring(i, i+2)));
		}
    	return sb.toString(); 
     }
	 
	 private String cipher_digraph(String s)
	 {
		 assert(s.length()==2);
		 Pair<Integer> row_col1=compute_pos(s.charAt(0));
		 Pair<Integer> row_col2=compute_pos(s.charAt(1));
		 StringBuilder a=new StringBuilder();
		 a.append(square2[row_col1.get_first()][row_col2.get_second()]);
		 a.append(square4[row_col2.get_first()][row_col1.get_second()]);
		 return a.toString();
	 }
	 
	 Pair<Integer> compute_pos(char a)
	 {
		 //compute the position in the unkeyed square
		 if(a=='J')
		 {
			 return compute_pos('I');
		 }
		 int pos=a-'A';
		 if(pos<9)
		 {
			 Pair<Integer> p=new Pair<Integer>(pos/5,pos%5);
			 return p;
		 }
		 else
		 {
			 Pair<Integer> p=new Pair<Integer>((pos-1)/5,(pos-1)%5);
			 return p;
		 }
	 }
    
    /*
     * Decode the cipher text.
     */
    public String decode(String cipher)
    {
    	return null;
    }
    
    
    private ArrayList<String> keys=new ArrayList<String>();
    
    public char[][] square1=new char[5][5];
    public char[][] square2=new char[5][5];
    public char[][] square3=new char[5][5];
    public char[][] square4=new char[5][5];
    
    public boolean need_key=true; //need to generate a string key
    
    public static int key_num=2; 
    
    public int process_id()
	{
		return 2;
	}
}
