package aca.ciphers;



public interface Cipher {
	/*
	 * Encode the plain text.
	 */
    public String encode(String plain);
    
    /*
     * Decode the cipher text.
     */
    public String decode(String cipher);
}
