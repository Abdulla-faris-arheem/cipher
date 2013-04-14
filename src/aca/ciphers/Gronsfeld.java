package aca.ciphers;

import java.util.ArrayList;
import java.util.Random;

public class Gronsfeld implements Cipher {
	
	public Gronsfeld()
	{
		Random r=new Random();
		period=1+r.nextInt(14);
		key=new int[period];
		generate_num_key(key,period);
	}
	
	public Gronsfeld(int p)
	{
		//Random r=new Random();
		period=p;
		key=new int[period];
		generate_num_key(key,period);
	}
	
	public Gronsfeld(int p,int[] k)
	{
		//Random r=new Random();
		period=p;
		key=new int[period];
		for (int i=0;i<k.length;i++)
		{
			key[i]=k[i];
		}
	}
	
	public String encode(String plain)
	 {
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<plain.length();i+=period)
		{
			int pos=i+period>plain.length()?plain.length():i+period;
			String sub_text=plain.substring(i,pos);
			sb.append(cipher_sub(key,sub_text));
		}
		return sb.toString();
	 }
	
	public String cipher_sub(int[] key,String subt)
	{
		StringBuilder sb=new StringBuilder();
		assert(subt.length()<key.length);
		for(int i=0;i<subt.length();i++)
		{
			sb.append(get_cipher_char(key[i],subt.toUpperCase().charAt(i)));
		}
		return sb.toString();
	}
	
	private char get_cipher_char(int k, char p_char)
	{
		//make sure the p_char is uppercased and k is a one-digit number
		int k_pos=k;
		int p_pos=p_char-'A';
		int result=k_pos+p_pos>26?k_pos+p_pos-26:k_pos+p_pos;
		return (char)('A'+result);
	}
	
	public void generate_num_key(int[] key,int period)
	{
		assert(key.length==period);
		Random r=new Random();
		for(int i=0;i<period;i++)
		{
			key[i]=r.nextInt(10);
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
	    private int period;
	    private int[] key;
}
