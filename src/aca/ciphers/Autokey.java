package aca.ciphers;

import java.util.ArrayList;

/*
 * Insert part of the plaintext into the key
 * 
 */
public class Autokey implements Cipher {
	
	public Autokey()
	{
		
	}
	
	public Autokey(String k)
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
	
	public void set_table_type(int table_id)
	{
		
	}
	
	/*
	 * Get the ciphered text from a table and a key
	 */
	public String get_table_text(Cipher_table table, String plain,String key)
	{
	   int key_len=key.length();
	   int plain_len=plain.length();
	   //String inserted;
	   String new_key;
	   if(plain_len>key_len)
	   {
	     String inserted=plain.substring(0,plain_len-key_len).toUpperCase();
	     new_key=this.key+inserted;
	   }
	   else
	   {
		   new_key=key.substring(0,plain_len);
	   }
	   StringBuilder b=new StringBuilder();
	   for (int i=0;i<new_key.length();i++)
	   {
		   int row_index=table.get_row_index(new_key.charAt(i));
		   int col_index=table.get_col_index(plain.charAt(i));
		   b.append(table.get_char(row_index, col_index));
	   }
	   return b.toString();	
	}

	private String key="PRIMER";
	private int table=0;//0 for Vigenere, 1 for Variant,2 for Beaufort, and 3 for Porta.
	
	
	@Override
	public String encode(String plain) {
		String cipher_text="";
		Cipher_table t=null;
		if(this.table==0)
		{
		   t=new Vigenere_table();
		}
		cipher_text=get_table_text(t,plain,this.key);
		return cipher_text;
	}

	@Override
	public String decode(String cipher) {
		// TODO Auto-generated method stub
		return null;
	}

}
