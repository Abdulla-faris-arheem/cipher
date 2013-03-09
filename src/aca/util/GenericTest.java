package aca.util;

import static org.junit.Assert.*;
import java.util.ArrayList;

import org.junit.Test;
import org.junit.Assert;

public class GenericTest {

	@Test
	public void test() {
		//fail("Not yet implemented");
		//int[] result=Generic_Func.generate_random_perm(1,6);
		//ArrayList<String> spell_num=Generic_Func.spell_number(108);
		//for (int i=0;i<result.length;i++)
		//{
	//		System.out.println(result[i]);
	//	}
		for(int i=0;i<2;i++)
		{
			for(int j=0;j<2;j++)
			{
				System.out.println(i);
				System.out.println(j);
				System.out.println("Next");
				Pair<Integer> next=Generic_Func.traverse_spiral(i, j, 2);
				System.out.println(next.get_first());
				System.out.println(next.get_second());
			}
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
