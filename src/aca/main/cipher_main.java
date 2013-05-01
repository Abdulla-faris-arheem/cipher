package aca.main;

import aca.ciphers.*;

public class cipher_main {

	/**
	 * Main function. You can use it to test the various ciphers. 
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
       // Bazeries b=new Bazeries(3752);
		//Caesar b=new Caesar(19);
	//	int[] key={4,1,3,2,5};
	//	Vigenere b=new Vigenere();
		//Autokey b=new Autokey();
	//	Baconian b=new Baconian();
		//Beaufort b=new Beaufort("RECIPROCAL");
	//	BIFID b=new BIFID(7,"EXTRAORDINARY");
		//Cadenus b=new Cadenus();
		//int[] key={3,1,2};
		//Columnar b=new Columnar(key);
		//Checkerboard b=new Checkerboard();
		//CM_BIFID b = new CM_BIFID(7);
		//Foursquare b=new Foursquare();
		//Variant b=new Variant();
		//Two_square b=new Two_square();
		//Tri_square b=new Tri_square();
		//Tridigital b=new Tridigital();
		//Trifid b=new Trifid(10);
//		Digrafid b=new Digrafid(4);
		//Grandpre b=new Grandpre();
		//Frac_morse b=new Frac_morse();
		//Double_checkerboard b=new Double_checkerboard();
	//	Gromark b=new Gromark("HERBAL");
		//int[] holes={1,8,12,10};
	//	Grille b=new Grille(holes);
	//	Grille b=new Grille();
		//int[] key={9,3,2,1,4,9,2};
		//Gronsfeld b=new Gronsfeld(7,key);
	//	Headlines b=new Headlines();
		//int[] key={3,1,2};
		//Incomp_column b=new Incomp_column(key);
		//Interrupted_key b=new Interrupted_key();
		//Key_phrase b=new Key_phrase("GIVEMELIBERTYORGIVEMEDEATH");
	//	Monome_dinome b=new Monome_dinome("DEBUNKS");
		
		//Morbit b=new Morbit();
		//Myszkowski b=new Myszkowski();
		//Nicodemus b=new Nicodemus();
		//Nihilist_sub b=new Nihilist_sub();
		
		//int[] k={2,1,3,4};
		//Nihilist_trans b=new Nihilist_trans(k);
		//Period_Gromark b=new Period_Gromark();
		//Phillips_RC b=new Phillips_RC();
		//Playfair b=new Playfair();
		//char[] a={'x','-','.','.','x','.','-','-','x','.'};
		//Pollux b=new Pollux();
		//Porta b=new Porta();
		//Portax b=new Portax();
		//Progressive_key b=new Progressive_key("GRAPEFRUIT",1);
		//int[] num_k={3,2,1,4,5};
		//Swagman b=new Swagman(num_k);
	//	Swagman b=new Swagman();
		//Running_key b=new Running_key();
		//Seriated_playfair b=new Seriated_playfair("LOGARITHM",6);
		//int[] num_k={2,1,3};
		//Redefence b=new Redefence(num_k);
		//Railfence b=new Railfence(3);
		//Rag_baby b=new Rag_baby();
		//Quagmire_IV b=new Quagmire_IV();
	//	Route_transposition b=new Route_transposition(2);
	//	Seriated_playfair b=new Seriated_playfair("LOGARITHM",6);
	//	String ctext=b.encode("comequicklyweneedhxelpimmediatelyt");
	//	System.out.println(ctext);
		int[] key={4,1,3,2,5};
		Cipher b=new Amsco(key);
		System.out.println("Running Amsco cipher as an example");
		System.out.println("Use 41325 as the key");
		System.out.println("Plain text: incomplete columnar with alternating single letters and digraphs");
        String ctext=b.encode("incompletecolumnarwithalternatingsinglelettersanddigraphs");
        System.out.println("Cipher text:");
        System.out.println(ctext);
		
	}

}
