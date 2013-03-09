package aca.ciphers;

import java.util.ArrayList;
import java.util.HashMap;

public class Tridigital implements Cipher {
	
	 public Tridigital()
	 {
		 num_key=generate_num_key(key);
		 
	 }
	 
	 public boolean key_need()
	  {
		    return need_key;
	 }
		    
		 public int get_key_num()
		 {
		    return 2;
		 }
		    
		 public ArrayList<Integer> get_key_len()
		 {
			 ArrayList<Integer> result=new ArrayList<Integer>();
			 result.add(10);
			 result.add(10);
			 return result;
		 }
	 
	 public Tridigital(ArrayList<String> keys)
	 {
		 if(keys.size()<2)
		 {
			 System.err.println("Not enough keys");
			 return;
		 }
		 key=keys.get(0);
		 keyword=keys.get(1);
		 num_key=generate_num_key(key);
	 }
	 
	 private int[] generate_num_key(String k)
	 {
		 assert(k.length()==10);
		 String k_u=k.toUpperCase();
		 char[] k_array=k_u.toCharArray();
		 int[] result=new int[10];
		 int[] end_pos=new int[10];
		 for(int i=0;i<10;i++)
		 {
			 //result[i]=0;
			 end_pos[i]=i;
		 }
		 for(int i=0;i<10;i++)
		 {
			 for(int j=i+1;j<10;j++)
			 {
				 if(k_array[i]>k_array[j])
				 {
					 swap_char(k_array,i,j);
					 swap_int(end_pos,i,j);
					 //result[i]=i;
				 }
			 }
			/* if(result[i]==9)
			 {
				 result[i]=0;
			 }
			 else
			 {
				 result[i]++;//start from 1
			 }*/
		 }
		 for(int i=0;i<10;i++)
		 {
			 if(i==9)
			 {
				 result[end_pos[i]]=0;
			 }
			 else
			 {
				result[end_pos[i]]=i+1;
			 }
		 }
		 return result;
		// Collections.sort(k.toCharArray());
		 
	 }
	 
	 private void swap_char(char[] array, int i,int j)
	 {
		 char temp=array[i];
		 array[i]=array[j];
		 array[j]=temp;
	 }
	 
	 private void swap_int(int[] array, int i,int j)
	 {
		 int temp=array[i];
		 array[i]=array[j];
		 array[j]=temp;
	 }
	 
	 private String key="NOVELCRAFT";
	 private String keyword="DRAGONFLY";
	 private int[] num_key=new int[10];
	 
	 HashMap<Character,Integer> generate_map(String k, int[] num)
	 {
		 //int num_size=num.length;
		 int cur_num=0;
		 HashMap<Character,Integer> m=new HashMap<Character,Integer>();
		 boolean[] filled=new boolean[26];
		 for(int i=0;i<k.length();i++)
		 {
			char cur_char=k.charAt(i);
			int pos=cur_char-'A';
			if(!filled[pos])
			{
				m.put(Character.valueOf(cur_char), Integer.valueOf(num[cur_num]));
				filled[pos]=true;
			}
			else
			{
				continue;
			}
			if(cur_num==8)
			{
				cur_num=0;
			}
			else
			{
				cur_num++;
			}
		 }
		 for(int i=0;i<26;i++)
		 {
			    char cur_char=(char)('A'+i);
				//int pos=cur_char-'A';
				if(!filled[i])
				{
					m.put(Character.valueOf(cur_char), Integer.valueOf(num[cur_num]));
					filled[i]=true;
				}
				else
				{
					continue;
				}
				if(cur_num==8)
				{
					cur_num=0;
				}
				else
				{
					cur_num++;
				}
		 }
		 m.put(' ', Integer.valueOf(num[9]));
		 return m;
	 }
	
	 public String encode(String plain)
     {
		HashMap<Character,Integer> map=generate_map(keyword,num_key);
		String p=plain.toUpperCase();
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<p.length();i++)
		{
		    char cur_char=p.charAt(i);
		    sb.append(map.get(Character.valueOf(cur_char)));
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
    
    public static boolean need_key=true; //need to generate a string key
    
    public static int key_num=2;   
    
    public static int required_key_len=10;
    
    public static boolean allow_empty=true;
    
    public static boolean allow_num=false;
}
