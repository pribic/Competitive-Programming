import org.junit.Test;
import static org.junit.Assert.*;

public class AqaAsadiPlaysTest {
	
	@Test(timeout=2000)
	public void test0() {
		int[] A = new int[] {6, 1, 4};
		assertEquals(2, new AqaAsadiPlays().getMin(A));
	}
	
	@Test(timeout=2000)
	public void test1() {
		int[] A = new int[] {4, 4, 1, 3, 2};
		assertEquals(2, new AqaAsadiPlays().getMin(A));
	}
	
	@Test(timeout=2000)
	public void test2() {
		int[] A = new int[] {2, 8, 3, 12, 16};
		assertEquals(3, new AqaAsadiPlays().getMin(A));
	}
	
	@Test(timeout=2000)
	public void test3() {
		int[] A = new int[] {47, 47};
		assertEquals(-1, new AqaAsadiPlays().getMin(A));
	}
}
