package aca.ciphers;

import java.util.ArrayList;
import java.util.HashMap;
import aca.util.Pair;

public class Nihilist_sub implements Cipher {
	
	public Nihilist_sub()
	{
		cube_keyword="SIMPLE";
		key="EASY";
	}
	
	public Nihilist_sub(ArrayList<String> keys)
	{
		assert(keys.size()>=2);
		cube_keyword=keys.get(0);
		key=keys.get(1);
	}
	
	public String encode(String plain)
	 {
		char[][] polybius=new char[5][5];
		Two_square.build_square(polybius, cube_keyword);
		HashMap<Character,Pair<Integer>> h_map=BIFID.build_map(polybius);
		int period=key.length();
		int[] key_num=new int[period];
		generate_row_col(key_num,h_map,key);
		int[] p_num=new int[period];
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<plain.length();i+=period)
		{
			int end=i+period>=plain.length()?plain.length():i+period;
			String sub_str=plain.substring(i,end);
			generate_row_col(p_num,h_map,sub_str);
			for(int j=0;j<sub_str.length();j++)
			{
				int cipher_num=key_num[j]+p_num[j];
				sb.append(cipher_num);
			}
			
		}
		return sb.toString();
	 }
	
	private void generate_row_col(int[] key_num,HashMap<Character,Pair<Integer>> h_map,String key)
	{
		assert(key_num.length>=key.length());
		String k_u=key.toUpperCase();
		for(int i=0;i<k_u.length();i++)
		{
			char cur_k=k_u.charAt(i);
			if(cur_k=='J')
			{
				cur_k='I';
			}
			Pair<Integer> p=h_map.get(Character.valueOf(cur_k));
			int num=(p.get_first()+1)*10+p.get_second()+1;
			key_num[i]=num;
		}
	}
	
	private String cube_keyword;
	private String key;
	    
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
	    	return 2;
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
