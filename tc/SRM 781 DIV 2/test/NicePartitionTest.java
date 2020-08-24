import org.junit.Test;
import static org.junit.Assert.*;

public class NicePartitionTest {
	
	@Test(timeout=2000)
	public void test0() {
		int[] H = new int[] {0,2};
		assertEquals(2, new NicePartition().minCost(H));
	}
	
	@Test(timeout=2000)
	public void test1() {
		int[] H = new int[] {3,5,1,5,7,9};
		assertEquals(12, new NicePartition().minCost(H));
	}
	
	@Test(timeout=2000)
	public void test2() {
		int[] H = new int[] {31,52,11,52,73,19,54,124,21,1};
		assertEquals(272, new NicePartition().minCost(H));
	}
	
	@Test(timeout=2000)
	public void test3() {
		int[] H = new int[] {1,1,1,1,1,1};
		assertEquals(0, new NicePartition().minCost(H));
	}
	
	@Test(timeout=2000)
	public void test4() {
		int[] H = new int[] {463,210,438,95,300,192,114,72,330,226,125,193,384,326,338,6};
		assertEquals(1798, new NicePartition().minCost(H));
	}
}
