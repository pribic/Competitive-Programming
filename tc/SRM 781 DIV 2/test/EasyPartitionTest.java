import org.junit.Test;
import static org.junit.Assert.*;

public class EasyPartitionTest {
	
	@Test(timeout=2000)
	public void test0() {
		int N = 1;
		assertEquals("10100000", new EasyPartition().getPartition(N));
	}
	
	@Test(timeout=2000)
	public void test1() {
		int N = 2;
		assertEquals("0110110000000000", new EasyPartition().getPartition(N));
	}
	
	@Test(timeout=2000)
	public void test2() {
		int N = 3;
		assertEquals("010110110100000000000000", new EasyPartition().getPartition(N));
	}
	
	@Test(timeout=2000)
	public void test3() {
		int N = 4;
		assertEquals("01010110110101000000000000000000", new EasyPartition().getPartition(N));
	}
}
