package aca.ciphers;

import java.util.ArrayList;
import java.util.Random;

public class Null implements Cipher {
	//25-letter plaintext maximum
	public Null()
	{
		
	}
	
	public String encode(String plain)
	 {
		Random r=new Random();
		int key_len=1+r.nextInt(7);
		
		return null;
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
