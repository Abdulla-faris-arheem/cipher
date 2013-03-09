package aca.ciphers;

public class Variant_table implements Cipher_table {

	
	@Override
	public int get_row_index(char c) {
		if(c=='A')
			return 0;
		else
			return 25-(int)(c-'B');
	}

	@Override
	public int get_col_index(char c) {
		// TODO Auto-generated method stub
		return (int)c-'a';
	}
	
	public char get_char(int row, int col)
	{
		return Variant_table.variant[row][col];
	}
	
	public static char[][] variant=Vigenere.vigenere;//same table, but not the same key index
}
