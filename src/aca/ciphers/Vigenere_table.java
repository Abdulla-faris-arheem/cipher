package aca.ciphers;

public class Vigenere_table implements Cipher_table {

	@Override
	public int get_row_index(char c) {
		return (int)c-'A';
	}

	@Override
	public int get_col_index(char c) {
		// TODO Auto-generated method stub
		return (int)c-'a';
	}
	
	public char get_char(int row, int col)
	{
		return Vigenere_table.vigenere[row][col];
	}
	
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

}
