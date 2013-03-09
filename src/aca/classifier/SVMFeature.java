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
	
	public boolean contain_num; //contain numeric values
	
	
	//assume key length is 1-10, compute the 
    
}
