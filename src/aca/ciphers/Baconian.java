package aca.ciphers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Baconian implements Cipher {
	
	public Baconian()
	{
		Init_map();
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
	 
	 private boolean need_key=false;
	
	public void Init_map()
	{
		baconian_map=new HashMap<Character,String>();
		baconian_map.put('a', "aaaaa");
		baconian_map.put('b', "aaaab");
		baconian_map.put('c', "aaaba");
		baconian_map.put('d', "aaabb");
		baconian_map.put('e', "aabaa");
		baconian_map.put('f', "aabab");
		baconian_map.put('g', "aabba");
		baconian_map.put('h', "aabbb");
		baconian_map.put('i', "abaaa");
		baconian_map.put('j', "abaaa");
		baconian_map.put('k', "abaab");
		baconian_map.put('l', "ababa");
		baconian_map.put('m', "ababb");
		baconian_map.put('n', "abbaa");
		baconian_map.put('o', "abbab");
		baconian_map.put('p', "abbba");
		baconian_map.put('q', "abbbb");
		baconian_map.put('r', "baaaa");
		baconian_map.put('s', "baaab");
		baconian_map.put('t', "baaba");
		baconian_map.put('u', "baabb");
		baconian_map.put('v', "baabb");
		baconian_map.put('w', "babaa");
		baconian_map.put('x', "babab");
		baconian_map.put('y', "babba");
		baconian_map.put('z', "babbb");
	}
	
	public String get_map(char c)
	{
		return baconian_map.get(c);
	}
	
	public String conceal(String b)
	{
		Random random_gen=new Random();
		//A-M=a, N-Z=b
		StringBuilder s=new StringBuilder();
		for(char c:b.toCharArray())
		{
			if(c=='a')
			{
				int cur=random_gen.nextInt(13);
				s.append((char)('A'+cur));
			}
			else if(c=='b')
			{
				int cur=random_gen.nextInt(13);
				s.append((char)('N'+cur));
			}
			else
			{
				System.err.println("Erroneous string in baconian cipher. "+b);
			}
		}
		return s.toString();
	}
	
	private HashMap<Character,String> baconian_map;

	@Override
	public String encode(String plain) {
		StringBuilder b=new StringBuilder();
		for(int i=0;i<plain.length();i++)
		{
			b.append(get_map(plain.charAt(i)));
		}
	//	return b.toString();
		String concealed=conceal(b.toString());
		return concealed;
	}

	@Override
	public String decode(String cipher) {
		// TODO Auto-generated method stub
		return null;
	}

}
