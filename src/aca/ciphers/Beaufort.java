package aca.ciphers;

import java.util.ArrayList;

public class Beaufort implements Cipher {
	
	public Beaufort(){
		
	}
	
	public Beaufort(String k)
	{
		key=k;
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
	
	public boolean need_key=true;
	
	private String key="RECIPROCAL";
	
	private String get_table_text(Cipher_table t,String plain,String key)
	{
		 int key_len=key.length();
		 int plain_len=plain.length();
		 StringBuilder b=new StringBuilder();
		 int plain_index=0;
		 int key_index=0;
		 while(plain_index<plain_len)
		 {
			 int row=t.get_row_index(key.charAt(key_index));
			 int col=t.get_col_index(plain.charAt(plain_index));
			 char new_c=t.get_char(row, col);
			 b.append(new_c);
			 plain_index++;
			 if(key_index==key_len-1)
			 {
				 key_index=0;
			 }
			 else
			 {
				 key_index++;
			 }
		 }
		 return b.toString();
		 
	/*	   String inserted=plain.substring(0,plain_len-key_len).toUpperCase();
		   String new_key=this.key+inserted;
		   StringBuilder b=new StringBuilder();
		   for (int i=0;i<key_len;i++)
		   {
			   int row_index=table.get_row_index(new_key.charAt(i));
			   int col_index=table.get_col_index(plain.charAt(i));
			   b.append(table.get_char(row_index, col_index));
		   }
		   return b.toString();	*/
	}

	@Override
	public String encode(String plain) {
		String cipher_text="";
		Cipher_table t=new Beaufort_table();
		cipher_text=get_table_text(t,plain,this.key);
		return cipher_text;
	}

	@Override
	public String decode(String cipher) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public int process_id()
	{
		return 2;
	}

}
