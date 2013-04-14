package aca.ciphers;

import java.util.ArrayList;
import java.util.HashMap;
//import java.util.Random;

import aca.util.Pair;

public class Phillips_RC extends Phillips {
	
	public Phillips_RC()
	{
		key="DIAGONALS";
	}
	
	public Phillips_RC(String k)
	{
		key=k;
	}
	
	
	private String key;
	
	public String encode(String plain)
	 {
		char[][] square1=build_bow_square(key);
		char[][] square2_1=shift_square(square1,0,1,true);
		char[][] square2=shift_square(square2_1,0,1,false);
		char[][] square3_1=shift_square(square2,1,1,true);
		char[][] square3=shift_square(square3_1,1,1,false);
		char[][] square4_1=shift_square(square3,2,1,true);
		char[][] square4=shift_square(square4_1,2,1,false);
		char[][] square5_1=shift_square(square4,3,1,true);
		char[][] square5=shift_square(square5_1,3,1,false);
		char[][] square6_1=shift_square(square5,0,1,true);
		char[][] square6=shift_square(square6_1,0,1,false);
		char[][] square7_1=shift_square(square6,1,1,true);
		char[][] square7=shift_square(square7_1,1,1,false);
		char[][] square8_1=shift_square(square7,2,1,true);
		char[][] square8=shift_square(square8_1,2,1,false);
		HashMap<Character,Pair<Integer>> h_map1=BIFID.build_map(square1);
		HashMap<Character,Pair<Integer>> h_map2=BIFID.build_map(square2);
		HashMap<Character,Pair<Integer>> h_map3=BIFID.build_map(square3);
		HashMap<Character,Pair<Integer>> h_map4=BIFID.build_map(square4);
		HashMap<Character,Pair<Integer>> h_map5=BIFID.build_map(square5);
		HashMap<Character,Pair<Integer>> h_map6=BIFID.build_map(square6);
		HashMap<Character,Pair<Integer>> h_map7=BIFID.build_map(square7);
		HashMap<Character,Pair<Integer>> h_map8=BIFID.build_map(square8);
		StringBuilder sb=new StringBuilder();
		int square_index=1;
		for(int i=0;i<plain.length();i+=5)
		{
			int end=i+5>plain.length()?plain.length():i+5;
			switch(square_index){
				case 1: 
					sb.append(get_sub_cipher(plain,i,end,h_map1,square1));
					break;
				case 2:
					sb.append(get_sub_cipher(plain,i,end,h_map2,square2));
					break;
				case 3:
					sb.append(get_sub_cipher(plain,i,end,h_map3,square3));
					break;
				case 4:
					sb.append(get_sub_cipher(plain,i,end,h_map4,square4));
					break;
				case 5:
					sb.append(get_sub_cipher(plain,i,end,h_map5,square5));
					break;
				case 6:
					sb.append(get_sub_cipher(plain,i,end,h_map6,square6));
					break;
				case 7:
					sb.append(get_sub_cipher(plain,i,end,h_map7,square7));
					break;
				case 8:
					sb.append(get_sub_cipher(plain,i,end,h_map8,square8));
					break;
				default:
						break;
			}
			if(square_index==8)
			{
				square_index=1;
			}
			else
			{
				square_index++;
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
