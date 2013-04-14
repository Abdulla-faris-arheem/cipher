package aca.ciphers;

import java.util.ArrayList;
//import java.util.Random;

public class Running_key implements Cipher {
	
	public Running_key()
	{
		
	}
	
	public String encode(String plain)
	 {
		String plain_u=plain.toUpperCase();
		if(plain_u.length()%2!=0)
		{
			plain_u+="X";
		}
		int period=plain_u.length()/2;
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<period;i++)
		{
			char k=plain_u.charAt(i);
			char p=plain_u.charAt(i+period);
			sb.append(Vigenere.get_vigenere_char(k, p));
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
	    	return false;
	    }
	    
	    public int get_key_num()
	    {
	    	return 0;
	    }
	    
	    public ArrayList<Integer> get_key_len()
	    {
	    	return null;
	    }
}
