package aca.ciphers;

import java.util.ArrayList;



public interface Cipher {
	/*
	 * Encode the plain text.
	 */
    public String encode(String plain);
    
    /*
     * Decode the cipher text.
     */
    public String decode(String cipher);
    
    public boolean key_need();
    
    public int get_key_num();
    
    public ArrayList<Integer> get_key_len();
    
    public int process_id(); //required pre-preprocessing. 0 for no pre-processing, 1 for remove all the non-alphanumeric characters; 2 for remove all the non-alphabetic characters
    
    
   // public boolean need_key=false; //need to generate a string key
    
    //public int key_num=0;
    
    //public int required_key_len=-1;
}
