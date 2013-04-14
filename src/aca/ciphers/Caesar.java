package aca.ciphers;

import java.util.ArrayList;
import java.util.Random;

/*
 * Simplest, substition-only cipher
 */

public class Caesar implements Cipher {
	
	public Caesar()
	{
		Random random_gen=new Random();
		shift=1+random_gen.nextInt(25);
	}
	
	public Caesar(int s)
	{
		shift=s;
	}
	
	public boolean key_need()
	 {
	    return need_key;
	 }
	    
	 public int get_key_num()
	 {
	    return 0;
	 }
	    
	 public ArrayList<Integer> get_key_len()
	 {
	     return null;
	 }
	
	private int shift;//distance to shift
	
	public char shifted(char c, int shift)
	{
		if('z'-c>=shift)
		{
			return (char)(c+shift);
		}
		else
		{
			int upshift=shift-('z'-c);
			return (char)('a'+upshift-1);
		}
	}

	@Override
	public String encode(String plain) {
		shift=shift%26;//less than 26
		StringBuilder cipher_text=new StringBuilder();
		for(char s:plain.toCharArray())
		{
			cipher_text.append(shifted(s,shift));
		}
		return cipher_text.toString().toUpperCase();
	}

	@Override
	public String decode(String cipher) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private boolean need_key=false;

}
