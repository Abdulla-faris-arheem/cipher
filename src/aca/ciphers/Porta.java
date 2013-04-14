package aca.ciphers;

import java.util.ArrayList;
//import java.util.Random;

public class Porta implements Cipher {
	
	public Porta()
	{
		key="PORTA";
	}
	
	public Porta(String k)
	{
		key=k;
	}
	
	private String key;
	
	public String encode(String plain)
	 {
		String plain_u=plain.toUpperCase();
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<plain_u.length();i+=key.length())
		{
			int end=i+key.length()>plain_u.length()?plain_u.length():i+key.length();
			sb.append(get_sub_ct(plain_u,i,end,key));
		}
	//	Random r=new Random();
		//int key_len=1+r.nextInt(7);
		
		return sb.toString();
	 }
	
	private String get_sub_ct(String plain,int start,int end,String key)
	{
		String key_u=key.toUpperCase();
		String plain_u=plain.toUpperCase();
		StringBuilder sb=new StringBuilder();
		for(int i=start;i<end;i++)
		{
			sb.append(get_ct_char(key_u.charAt(i-start),plain_u.charAt(i)));
		}
		return sb.toString();
	}
	
	private char get_ct_char(char kc,char pc)
	{
		int kc_pos=(kc-'A')/2;
		int pc_pos=pc-'A';
		if(pc-'M'>0)
		{
			
			return (char)('A'+(pc_pos-kc_pos)%13);
		}
		else
		{			
			return (char)((pc_pos+kc_pos)%13+'N');
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
