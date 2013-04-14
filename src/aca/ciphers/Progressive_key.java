package aca.ciphers;

import java.util.ArrayList;
import java.util.Random;

public class Progressive_key implements Cipher {
	public Progressive_key()
	{
		key="GRAPEFRUIT";
		Random r=new Random();
		progression_index=r.nextInt(25);
	}
	
	public Progressive_key(String k)
	{
		key=k;
		Random r=new Random();
		progression_index=r.nextInt(25);
	}
	public Progressive_key(String k,int index)
	{
		key=k;
		progression_index=index;
	}
	
	private String key;
	private int progression_index;
	
	public String encode(String plain)
	 {
		int period=key.length();
		String key_u=key.toUpperCase();
		String plain_u=plain.toUpperCase();
		StringBuilder sb=new StringBuilder();
		int kp=0;
		for(int i=0;i<plain_u.length();i+=period)
		{
			int end=i+period>plain_u.length()?plain_u.length():i+period;
			char kp_c=(char)(kp+'A');
			kp+=progression_index;
			for(int j=i;j<end;j++)
			{
				char k1=key_u.charAt(j-i);
				char pt=plain_u.charAt(j);
				char c1=Vigenere.get_vigenere_char(k1, pt);
				char c2=Vigenere.get_vigenere_char(kp_c, c1);
				sb.append(c2);				
			}
		}
		
		return sb.toString();
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
