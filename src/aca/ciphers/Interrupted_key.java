package aca.ciphers;

import java.util.ArrayList;
import java.util.Random;

public class Interrupted_key implements Cipher {
	
	public Interrupted_key()
	{
		this.key="ORANGE";
	}
	
	public Interrupted_key(String key)
	{
	   this.key=key;
	}
	
	private String key;
	
	
	public String encode(String plain)
	 {
		String k=create_k(key,plain.length());//create a interrupted key
		String cipher_text=get_cipher_text(k,plain,"Vigenere");
		
		return cipher_text;
	 }
	
	private String get_cipher_text(String k, String pt, String cipher_type)
	{
		assert(k.length()==pt.length());
		String k_u=k.toUpperCase();
		//String k_u="ORANORANGEORORAORANORAOOORANGOORORAORANG";
		String pt_u=pt.toUpperCase();
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<k.length();i++)
		{
			int k_pos=k_u.charAt(i)-'A';
			int p_pos=pt_u.charAt(i)-'A';
			int result=k_pos+p_pos>26?k_pos+p_pos-26:k_pos+p_pos;
			sb.append((char)('A'+result));			
		}
		return sb.toString();
	}
	    
	private String create_k(String key, int len)
	{
		if(len==1)
			return key.substring(0,1);
		else
		{
		  Random r=new Random();
		  int range=key.length()<len?key.length():len;
		  int number=r.nextInt(range+1);
		//  int number=key.length();
		  String cur_part=key.substring(0,number);
		  if(number==len)
		  {
			  return cur_part;
		  }
		  else
		  {
			  return create_k(key,len-number)+cur_part;
		  }		  
		}
		
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
	    	return true;
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
