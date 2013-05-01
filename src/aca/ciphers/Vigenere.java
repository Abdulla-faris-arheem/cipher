package aca.ciphers;

import java.util.ArrayList;

public class Vigenere implements Cipher {
	
	public Vigenere(){
		
	}
	
	public Vigenere(String k)
	{
		this.key=k;
	}
	
	public static char get_vigenere_char(char k_char,char p_char)
	{
		char k_u=Character.toUpperCase(k_char);
		char p_u=Character.toUpperCase(p_char);
		int k_pos=k_u-'A';
		int p_pos=p_u-'A';
		int result=(k_pos+p_pos>=26)?k_pos+p_pos-26:k_pos+p_pos;
		return (char)('A'+result);
	}
	
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
	
	public static boolean need_key=true;
	
	private String key="POLYALPHABETIC";
	
	public static char[][] vigenere={
			{'A','B','C','D','E','F','G','H','I','J','K','L','M',
				'N','O','P','Q','R','S','T','U','V','W','X','Y','Z'},
		    {'B','C','D','E','F','G','H','I','J','K','L','M',
					'N','O','P','Q','R','S','T','U','V','W','X','Y','Z','A'},
			{'C','D','E','F','G','H','I','J','K','L','M',
					'N','O','P','Q','R','S','T','U','V','W','X','Y','Z','A','B'},
			{'D','E','F','G','H','I','J','K','L','M',
					'N','O','P','Q','R','S','T','U','V','W','X','Y','Z','A','B','C'},
			{'E','F','G','H','I','J','K','L','M',
					'N','O','P','Q','R','S','T','U','V','W','X','Y','Z','A','B','C','D'},
            {'F','G','H','I','J','K','L','M',
					'N','O','P','Q','R','S','T','U','V','W','X','Y','Z','A','B','C','D','E'},
			{'G','H','I','J','K','L','M',
					'N','O','P','Q','R','S','T','U','V','W','X','Y','Z','A','B','C','D','E','F'},
			{'H','I','J','K','L','M',
					'N','O','P','Q','R','S','T','U','V','W','X','Y','Z','A','B','C','D','E','F','G'},
			{'I','J','K','L','M',
				    'N','O','P','Q','R','S','T','U','V','W','X','Y','Z','A','B','C','D','E','F','G','H'},
			{'J','K','L','M',
					'N','O','P','Q','R','S','T','U','V','W','X','Y','Z','A','B','C','D','E','F','G','H','I'},
			{'K','L','M',
					'N','O','P','Q','R','S','T','U','V','W','X','Y','Z','A','B','C','D','E','F','G','H','I','J'},
			{'L','M',
					'N','O','P','Q','R','S','T','U','V','W','X','Y','Z','A','B','C','D','E','F','G','H','I','J','K'},
			{'M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','A','B','C','D','E','F','G','H','I','J','K','L'},
			{'N','O','P','Q','R','S','T','U','V','W','X','Y','Z','A','B','C','D','E','F','G','H','I','J','K','L','M'},
			{'O','P','Q','R','S','T','U','V','W','X','Y','Z','A','B','C','D','E','F','G','H','I','J','K','L','M',
				'N'},
		   {'P','Q','R','S','T','U','V','W','X','Y','Z','A','B','C','D','E','F','G','H','I','J','K','L','M',
					'N','O'},
		{'Q','R','S','T','U','V','W','X','Y','Z','A','B','C','D','E','F','G','H','I','J','K','L','M',
					    'N','O','P'},
		{'R','S','T','U','V','W','X','Y','Z','A','B','C','D','E','F','G','H','I','J','K','L','M',
							'N','O','P','Q'},
		{'S','T','U','V','W','X','Y','Z','A','B','C','D','E','F','G','H','I','J','K','L','M',
								'N','O','P','Q','R'},
		{'T','U','V','W','X','Y','Z','A','B','C','D','E','F','G','H','I','J','K','L','M',
									'N','O','P','Q','R','S'},
		{'U','V','W','X','Y','Z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T'},
		{'V','W','X','Y','Z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U'},
		{'W','X','Y','Z','A','B','C','D','E','F','G','H','I','J','K','L','M',
		    'N','O','P','Q','R','S','T','U','V'},
       {'X','Y','Z','A','B','C','D','E','F','G','H','I','J','K','L','M',
				'N','O','P','Q','R','S','T','U','V','W'},
      {'Y','Z','A','B','C','D','E','F','G','H','I','J','K','L','M',
					'N','O','P','Q','R','S','T','U','V','W','X'},
       {'Z','A','B','C','D','E','F','G','H','I','J','K','L','M',
						'N','O','P','Q','R','S','T','U','V','W','X','Y'}};
	
	public char look_up_vtable(char pt, char key)
	{
		int col=(int)(pt-'a');
		int row=(int)(key-'A');
		return Vigenere.vigenere[row][col];
	}

	@Override
	public String encode(String plain) {
		int key_len=key.length();
		int cur_index=0;
		int plain_size=plain.length();
		int key_index=0;
		StringBuilder s=new StringBuilder();
		while(cur_index<plain_size)
		{
			s.append(look_up_vtable(plain.charAt(cur_index),this.key.charAt(key_index)));
			if(key_index==key_len-1)
			{
				key_index=0;
			}
			else
			{
				key_index++;
			}
			cur_index++;
		}
		return s.toString();
	}

	@Override
	public String decode(String cipher) {
		// TODO Auto-generated method stub
		return null;
	}
	
	 public int process_id()
		{
			return 2;
		}

}
