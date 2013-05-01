package aca.classifier;

import java.util.*;

public class SVMFeature implements Feature{
	
	public boolean has_j; //if the text contains j
	
	public double ic;//index of coincidence
	
	public int length; //to compute the unigram probability
	
	public boolean length_even;
	
	public HashMap<Character,Integer> frequencies;
	
	public double dic;//digraph index of coincidence
	
	public double uni_prob; //unigram probabilities for the whole sentence
	
	public boolean contain_star; //contain "#" in the cipher text
	
	public boolean all_num; //all the cipher text is number
	
	public boolean part_num; //contain numeric values, but not all the cipher text are digits 
	
//	public int start_num_len;//number of digits at the starting position
	
//	public int end_num_len;//number of digits at the ending position
	
	public double max_ic; //maximum ic for periods 1-15
	
	public double max_ic2; //maximum ic for shifting 1-25
	
	public double max_kappa;//max kappa value
	
	public double lr_value;//lr_value
	
	//public boolean start_num;//
	
	public int start_digit_num;//non-zero value only if part_num is true. This is the count of consecutive digits in the cipher text from the beginning. 
	
	public int end_digit_num;//non-zero value only if part_num is true. This is the count of consecutive digits in the cipher text from the end. 
	//assume key length is 1-10, compute the 
	
	public boolean length_25; //whether the cipher text length is multiple of 25
	
	//public double bigram_prob;//bigram probability of the whole cipher text
	public double bigram_derivation;//bigram derivation from English
	
	public double trigram_derivation;//trigram derivation from English
	
	public double word_percentage;//how many English words can be found in the cipher text
	
	public double half_percentage;//the percentage of A-M against N-Z in the cipher text
	
	
	
//	public boolean contain_space
    
}
