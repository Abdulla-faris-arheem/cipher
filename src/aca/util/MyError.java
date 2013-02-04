package aca.util;

public class MyError extends Error {
     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void output_error()
     {
    	 System.err.println(this.getMessage());
     }
}
