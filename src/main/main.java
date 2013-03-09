package main;

import aca.ciphers.*;

public class main {

	/**
	 * @param args
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
		Digrafid b=new Digrafid(4);
        String ctext=b.encode("Thisistheforestpri");
        System.out.println(ctext);
	}

}
