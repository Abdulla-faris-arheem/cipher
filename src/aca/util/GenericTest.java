package aca.util;

import static org.junit.Assert.*;
import java.util.ArrayList;

import org.junit.Test;
import org.junit.Assert;

public class GenericTest {

	@Test
	public void test() {
		//fail("Not yet implemented");
		ArrayList<String> spell_num=Generic_Func.spell_number(108);
		for (int i=0;i<spell_num.size();i++)
		{
			System.out.println(spell_num.get(i));
		}
		/*ArrayList<Integer> split_list=Generic_Func.split_number(3752);
		ArrayList<Integer> desired=new ArrayList<Integer>();
	    desired.add(3);
	    desired.add(7);
	    desired.add(5);
	    desired.add(2);
		Assert.assertEquals("Result",desired, split_list);*/
	}
	

	

}
