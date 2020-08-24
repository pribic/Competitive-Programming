import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class SmallestRegularTest {
	
	@Test(timeout=2000)
	public void test0() {
		// (((())(())))
		String S = "((()()(())))"; 
		int[] arr = new SmallestRegular().findLexSmallest(S);
		for(int i : arr)
			System.out.println(i);
		assertArrayEquals(new int[] {0,3,4,0,5,6,0,6,7 }, new SmallestRegular().findLexSmallest(S));
	}
	
	@Test()
	public void test1() {
		String S = "(()())";
		assertArrayEquals(new int[] {1, 2, 3 }, new SmallestRegular().findLexSmallest(S));
	}
	
	@Test(timeout=2000)
	public void test2() {
		String S = "()()()()";
		assertArrayEquals(new int[] {2, 3, 6, 2, 3, 5, 0, 1, 4 }, new SmallestRegular().findLexSmallest(S));
	}
}
