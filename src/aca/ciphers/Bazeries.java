package aca.ciphers;

import java.util.*;

import aca.util.*;

public class Bazeries implements Cipher {
	
	/*
	 * Constructor
	 */
	public Bazeries()
	{
		Random random_gen=new Random();
		this.key=random_gen.nextInt(1000000);
	}
//	public static boolean need_key=false;
	
	 public boolean key_need()
	 {
	    return need_key;
	 }
	    
	 public int get_key_num()
	 {
	    return 1;
	 }
	    
	 public ArrayList<Integer> get_key_len()
	 {
	    return null;
	 }
	 
	 private boolean need_key=false;
	
	public Bazeries(int k)
	{
		this.key=k;
	}

	private int key;
	/*
	 * pt: Simple substitution plus transposition.
     First a number less than a million is chosen (say 3752). It is spelled out and used
     as the key in a 5x5 ciphertext Polybius square entered in left-to-right horizontal rows.
     A 5x5 plaintext Polybius square is used with the alphabet in normal order vertically.
     In the ciphertext and plaintext squares, I and J (I/J) are combined in one cell.
	 * The plaintext is divided into groups governed by the key numbers, in this example:
3, 7, 5, and 2. Letters within each group are reversed. The result is enciphered using
the squares to match. The ciphertext is then written in 5-letter groups.
	 * @see aca.ciphers.Cipher#encode(java.lang.String)
	 */
    public String encode(String plain)
    {
    	ArrayList<Integer> split_key=Generic_Func.split_number(this.key);
    	ArrayList<String> spell_key=Generic_Func.spell_number(this.key);
        //generate ciphered table
    	boolean[] used=new boolean[26];
    	char[][] ct=new char[5][5];//ciphered table
    	int cipher_row=0;
    	int cipher_col=0;
    	for(String s:spell_key)
    	{
    		int index;
    		for(int i=0;i<s.length();i++)
    		{
    			//no need to exclude 'j' here since there is no 'j' in the number representations
    			index=s.charAt(i)-'a';
    			if(used[index]==false)
    			{
    				ct[cipher_row][cipher_col]=(char)('A'+index);
    				if(cipher_col<4)
    				{
    					cipher_col++;
    				}
    				else
    				{
    					cipher_row++;
    					cipher_col=0;
    				}
    				used[index]=true;
    			}
    		}
    	}
    	for(int j=0;j<26;j++)
    	{
    		if(j==9)
    		{
    			//'j'
    			continue;
    		}
    		if(used[j]==false)
    		{
    			ct[cipher_row][cipher_col]=(char)('A'+j);
    			if(cipher_col<4)
				{
					cipher_col++;
				}
				else
				{
					cipher_row++;
					cipher_col=0;
				}
				used[j]=true;
    		}
    	}
    	//Compute Reversed group
    	int pt_index=0;
    	StringBuilder r_pt=new StringBuilder();
    	int split_index=0;
    	int pl_len=plain.length();
    	while(pt_index<pl_len)
    	{
    		int reverse_len=split_key.get(split_index);
    		int end=(pt_index+reverse_len<pl_len)?pt_index+reverse_len:pl_len;
    		String sub=plain.substring(pt_index,end);
    	    StringBuilder sub_b=new StringBuilder(sub);
    	    sub_b.reverse();
    	    r_pt.append(sub_b.toString());
    	    pt_index=end;
    	    if(split_index<split_key.size()-1)
    	    {
    	    	split_index++;
    	    }
    	    else
    	    {
    	    	split_index=0;
    	    }
    	}
    	for(int i=0;i<r_pt.length();i++)
    	{
    		char pt_c=r_pt.charAt(i);
    		int pt_row=get_pt_row(pt_c);
    		int pt_col=get_pt_col(pt_c);
    		char c_char=ct[pt_row][pt_col];
    		r_pt.setCharAt(i,c_char);
    	}
    	return r_pt.toString();
    }
    
    /*
     * Get the index of a character in the plain text table
     */
    private int get_pt_row(char cha)
    {
    	if(cha=='j')
    		return get_pt_row('i');
    	int rel_index=cha-'a';
    	if(rel_index<9)  //prior to 'j'
    	{
    		return rel_index%5;
    	}
    	else
    	{
    		return (rel_index-1)%5;
    	}
    }
    
    private int get_pt_col(char cha)
    {
    	if(cha=='j')
    		return get_pt_col('i');
    	int rel_index=cha-'a';
    	if(rel_index<9)  //prior to 'j'
    	{
    		return rel_index/5;
    	}
    	else
    	{
    		return (rel_index-1)/5;
    	}
    }
    /*
     * (non-Javadoc)
     * @see aca.ciphers.Cipher#decode(java.lang.String)
     */
    public String decode(String cipher)
    {
    	return "";
    }
    
    char[][] p_square=new char[][]{{'a','f','l','q','v'},{'b','g','m','r','w'},
    		{'c','h','n','s','x'},{'d','i','o','t','y'},{'e','k','p','u','z'}};
    


}
