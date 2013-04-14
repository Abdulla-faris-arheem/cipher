package aca.ciphers;

import java.util.HashMap;
import java.util.ArrayList;

/*
 * Incomplete
 */
public class Frac_morse implements Cipher {
	
	public Frac_morse()
	{
		create_hash(key,h_map);
	}
	
	private void create_hash(String k,HashMap<String,Character> map)
	{
		String k_u=k.toUpperCase();
		boolean[] filled=new boolean[26];
		int total_cnt=0;
		for(char c:k_u.toCharArray())
		{
			int pos=c-'A';
			if(!filled[pos])
			{
				String d=get_alphabet_morse(total_cnt);
				map.put(d, Character.valueOf(c));
				filled[pos]=true;
				total_cnt+=1;
			}
			else
			{
				continue;
			}
		}
		for(int i=0;i<26;i++)
		{
			if(!filled[i])
			{
				String d=get_alphabet_morse(total_cnt);
				char cur_c=(char)('A'+i);
				map.put(d, Character.valueOf(cur_c));
				filled[i]=true;
				total_cnt+=1;
			}
			else
			{
				continue;
			}
		}
	}
	
	private String get_alphabet_morse(int cnt)
	{
		StringBuilder sb=new StringBuilder();
		int first_line=cnt/9;
		switch(first_line)
		{
		case 0:
			sb.append(".");
			break;
		case 1:
			sb.append("-");
			break;
		case 2:
			sb.append("x");
			break;
		}
		int second_line=cnt%9;
		if(second_line>=0 && second_line<=2)
		{
			sb.append(".");
		}
		else if(second_line>=3 && second_line<=5)
		{
            sb.append("-");			
		}
		else
		{
			sb.append("x");
		}
		int third_line=cnt%3;
		if(third_line==0)
		{
			sb.append(".");
		}
		else if (third_line==1)
		{
			sb.append("-");
		}
		else
		{
			sb.append("x");
		}
		return sb.toString();
	}
	
	 public String encode(String plain)
	 {
		 Morse_code m=new Morse_code();
		 
		 String plain_u=plain.toUpperCase();
		 StringBuilder sb=new StringBuilder();
		 for (char c: plain_u.toCharArray())
		 {
			 if(c!=' ')
			 {
			   sb.append(m.get_morse_code(c));
			   sb.append("x");
			 }
			 else
			 {
				 sb.append("x");
			 }
		 }
		 sb.append("x");
		 String mc=sb.toString();
		 int tri=mc.length()/3;
		 int tri_near=tri*3;
		 if(tri_near<mc.length())
		 {
			 mc=mc.substring(0,tri_near);
		 }
		 StringBuilder cb=new StringBuilder();
		 for(int i=0;i<mc.length();i+=3)
		 {
			 String cur_m=mc.substring(i,i+3);
			 cb.append(h_map.get(cur_m));
		 }
		 return cb.toString();
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
	    
	    private String key="ROUNDTABLE";
	    private HashMap<String,Character> h_map=new HashMap<String,Character>();
}
