package aca.ciphers;

public class Beaufort_table implements Cipher_table {
	
	
	public static char[][] beaufort_table={
		{'Z','Y','X','W','V','U','T','S','R','Q','P','O','N','M','L','K','J',
			'I','H','G','F','E','D','C','B','A'},
	    {'Y','X','W','V','U','T','S','R','Q','P','O','N','M','L','K','J',
				'I','H','G','F','E','D','C','B','A','Z'},	
		{'X','W','V','U','T','S','R','Q','P','O','N','M','L','K','J',
					'I','H','G','F','E','D','C','B','A','Z','Y'},	
		{'W','V','U','T','S','R','Q','P','O','N','M','L','K','J',
						'I','H','G','F','E','D','C','B','A','Z','Y','X'},
		{'V','U','T','S','R','Q','P','O','N','M','L','K','J',
							'I','H','G','F','E','D','C','B','A','Z','Y','X','W'},	
		{'U','T','S','R','Q','P','O','N','M','L','K','J',
								'I','H','G','F','E','D','C','B','A','Z','Y','X','W','V'},
		{'T','S','R','Q','P','O','N','M','L','K','J',
									'I','H','G','F','E','D','C','B','A','Z','Y','X','W','V','U'},
		{'S','R','Q','P','O','N','M','L','K','J',
										'I','H','G','F','E','D','C','B','A','Z','Y','X','W','V','U','T'},	
		{'R','Q','P','O','N','M','L','K','J',
											'I','H','G','F','E','D','C','B','A','Z','Y','X','W','V','U','T','S'},	
		{'Q','P','O','N','M','L','K','J','I','H','G','F','E','D','C','B','A','Z','Y','X','W','V','U','T','S','R'},
		{'P','O','N','M','L','K','J','I','H','G','F','E','D','C','B','A','Z','Y','X','W','V','U','T','S','R','Q'},	
		{'O','N','M','L','K','J','I','H','G','F','E','D','C','B','A','Z','Y','X','W','V','U','T','S','R','Q','P'},
		{'N','M','L','K','J','I','H','G','F','E','D','C','B','A','Z','Y','X','W','V','U','T','S','R','Q','P','O'},
		{'M','L','K','J','I','H','G','F','E','D','C','B','A','Z','Y','X','W','V','U','T','S','R','Q','P','O','N'},	
		{'L','K','J','I','H','G','F','E','D','C','B','A','Z','Y','X','W','V','U','T','S','R','Q','P','O','N','M'},
		{'K','J','I','H','G','F','E','D','C','B','A','Z','Y','X','W','V','U','T','S','R','Q','P','O','N','M','L'},
		{'J','I','H','G','F','E','D','C','B','A','Z','Y','X','W','V','U','T','S','R','Q','P','O','N','M','L','K'},	
		{'I','H','G','F','E','D','C','B','A','Z','Y','X','W','V','U','T','S','R','Q','P','O','N','M','L','K','J'},
		{'H','G','F','E','D','C','B','A','Z','Y','X','W','V','U','T','S','R','Q','P','O','N','M','L','K','J','I'},
		{'G','F','E','D','C','B','A','Z','Y','X','W','V','U','T','S','R','Q','P','O','N','M','L','K','J','I','H'},	
		{'F','E','D','C','B','A','Z','Y','X','W','V','U','T','S','R','Q','P','O','N','M','L','K','J','I','H','G'},
		{'E','D','C','B','A','Z','Y','X','W','V','U','T','S','R','Q','P','O','N','M','L','K','J','I','H','G','F'},
		{'D','C','B','A','Z','Y','X','W','V','U','T','S','R','Q','P','O','N','M','L','K','J','I','H','G','F','E'},	
		{'C','B','A','Z','Y','X','W','V','U','T','S','R','Q','P','O','N','M','L','K','J','I','H','G','F','E','D'},
		{'B','A','Z','Y','X','W','V','U','T','S','R','Q','P','O','N','M','L','K','J','I','H','G','F','E','D','C'},	
		{'A','Z','Y','X','W','V','U','T','S','R','Q','P','O','N','M','L','K','J','I','H','G','F','E','D','C','B'}};

	@Override
	public int get_row_index(char c) {
		return 25-((int)c-'A');
	}

	@Override
	public int get_col_index(char c) {
		return (int)c-'a';
	}

	@Override
	public char get_char(int row, int col) {
		return Beaufort_table.beaufort_table[row][col];
	//	return 0;
	}

}
