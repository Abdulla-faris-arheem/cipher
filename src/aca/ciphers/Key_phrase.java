package aca.ciphers;

import java.util.ArrayList;
import java.util.Random;

//import aca.util.Generic_Func;

public class Key_phrase implements Cipher {
	
	public Key_phrase()
	{
		//int[] array=Generic_Func.generate_random_perm(1, 27);
		Random r=new Random();
		for(int i=0;i<26;i++)
		{
			int letter_pos=r.nextInt(26);
			cipher_alphabet[i]=(char)('A'+letter_pos);
		}
	}
	
	public Key_phrase(char[] alphabet)
	{
		assert(alphabet.length==cipher_alphabet.length);
		for(int i=0;i<alphabet.length;i++)
		{
			cipher_alphabet[i]=alphabet[i];
		}
	}
	
	public Key_phrase(String alpha)
	{
		char[] alpha_array=alpha.toCharArray();
		assert(alpha_array.length==cipher_alphabet.length);
		for(int i=0;i<alpha_array.length;i++)
		{
			cipher_alphabet[i]=alpha_array[i];
		}
		
	}
	
	char[] cipher_alphabet=new char[26];
	
	public String encode(String plain)
	 {
		String plain_u=plain.toUpperCase();
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<plain.length();i++)
		{
			int pos=plain_u.charAt(i)-'A';
			sb.append(cipher_alphabet[pos]);
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
	    
	    public int process_id()
		{
			return 2;
		}
}
