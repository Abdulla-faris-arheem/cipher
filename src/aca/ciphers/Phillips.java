package aca.ciphers;

import java.util.ArrayList;
import java.util.HashMap;
//import java.util.Random;
//import aca.util.Pair;

import aca.util.Pair;

public class Phillips implements Cipher {
	
	public Phillips()
	{
		key="DIAGONALS";
	}
	
	public Phillips(String k)
	{
		key=k;
	}
	
	private String key;
	
	/**
	 * Builder a 5*5 polybius square based on a keyword.
	 * The text is written in rows: first left-to-right, second right-to-left, then left-to right...
	 * 
	 * @param k keyword
	 * @return polybius square (I/J is combined, and characters are uppercased)
	 */
	public char[][] build_bow_square(String k)
	{
		boolean[] filled=new boolean[26];
		char[][] result=new char[5][5];
		int row=0;
		int col=0;
		String k_u=k.toUpperCase();
		for(int i=0;i<k_u.length();i++)
		{
		    int pos=k_u.charAt(i)-'A';
	
		    if(pos==9)
		    {
		    	pos=8;
			    if(filled[pos])
			    {
			    	continue;
			    }
		    	result[row][col]='I';
		    }
		    else
		    {
			    if(filled[pos])
			    {
			    	continue;
			    }
		        result[row][col]=k_u.charAt(i);
		    }
		   
		    filled[pos]=true;
		    if(row%2==0)
		    {
		    	if(col==4)
		    	{
		    		row++;
		    		//col=0;
		    	}
		    	else
		    	{
		    		col++;
		    	}
		    }
		    else
		    {
		    	if(col==0)
		    	{
		    		row++;
		    	}
		    	else
		    	{
		    		col--;
		    	}
		    }
		}
		for(int i=0;i<26;i++)
		{
			if(i==9)
			{
				continue;
			}
			if(!filled[i])
			{
				char cur_c=(char)('A'+i);
				result[row][col]=cur_c;
				filled[i]=true;
			}
			else
			{
				continue;
			}
			 if(row%2==0)
			    {
			    	if(col==4)
			    	{
			    		row++;
			    	}
			    	else
			    	{
			    		col++;
			    	}
			    }
			    else
			    {
			    	if(col==0)
			    	{
			    		row++;
			    	}
			    	else
			    	{
			    		col--;
			    	}
			    }
		}
		return result;
	}
	
	/**
	 * Shift a row/col of a square.
	 * 
	 * @param square the square to be shifted
	 * @param start the row/col number (starting from 0)
	 * @param shift_pos shift number
	 * @param row row or column. Row is true, and column is false.
	 * @return
	 */
	public static char[][] shift_square(char[][] square,int start,int shift_pos,boolean row)
	{
		int total_row=square.length;
		int total_col=square[0].length;
		char[][] result=new char[total_row][total_col];
		for(int i=0;i<5;i++)
		{
			for(int j=0;j<5;j++)
			{
				result[i][j]=square[i][j];
			}
		}
		if(row)
		{
			int end=(start+shift_pos)%5;
			for(int i=0;i<5;i++)
			{
				char temp=result[start][i];
				result[start][i]=result[end][i];
				result[end][i]=temp;
				
			}
		}
		else
		{
			int end=(start+shift_pos)%5;
			for(int i=0;i<5;i++)
			{
				char temp=result[i][start];
				result[i][start]=result[i][end];
				result[i][end]=temp;
				
			}
		}
		return result;
	}
	
	protected String get_sub_cipher(String p,int start,int end,HashMap<Character,Pair<Integer>> h_map,char[][] square)
	{
		StringBuilder sb=new StringBuilder();
		for(int i=start;i<end;i++)
		{
			char cur_c=Character.toUpperCase(p.charAt(i));
			if(cur_c=='J')
			{
				cur_c='I';
			}
			Pair<Integer> row_col=h_map.get(cur_c);
			int row=row_col.get_first();
			int col=row_col.get_second();
			int next_col=col+1;
			if(col==square[0].length-1)
			{
				next_col=0;
			}
			int next_row=row+1;
			if(row==square.length-1)
			{
				next_row=0;
			}
			sb.append(square[next_row][next_col]);
		}
		return sb.toString();
	}
	
	public String encode(String plain)
	 {
		char[][] square1=build_bow_square(key);
		char[][] square2=shift_square(square1,0,1,true);
		char[][] square3=shift_square(square2,1,1,true);
		char[][] square4=shift_square(square3,2,1,true);
		char[][] square5=shift_square(square4,3,1,true);
		char[][] square6=shift_square(square5,0,1,true);
		char[][] square7=shift_square(square6,1,1,true);
		char[][] square8=shift_square(square7,2,1,true);
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
	    
	    public int process_id()
		{
			return 2;
		}
}
