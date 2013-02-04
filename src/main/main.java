package main;

import aca.ciphers.*;

public class main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        Bazeries b=new Bazeries(3752);
        String ctext=b.encode("simplesubstitutionplustransposition");
        System.out.println(ctext);
	}

}
