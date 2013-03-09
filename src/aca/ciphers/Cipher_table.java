package aca.ciphers;

public interface Cipher_table {
	public int get_row_index(char c);
	
	public int get_col_index(char c);
	
	public char get_char(int row, int col);
	

}
