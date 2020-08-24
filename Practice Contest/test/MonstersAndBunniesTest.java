import org.junit.Test;
import static org.junit.Assert.*;

public class MonstersAndBunniesTest {
	
	@Test(timeout=2000)
	public void test0() {
		int M = 0;
		int B = 0;
		assertEquals(1.0, new MonstersAndBunnies().survivalProbability(M, B), 1e-9);
	}
	
	@Test(timeout=2000)
	public void test1() {
		int M = 0;
		int B = 47;
		assertEquals(1.0, new MonstersAndBunnies().survivalProbability(M, B), 1e-9);
	}
	
	@Test(timeout=2000)
	public void test2() {
		int M = 1;
		int B = 0;
		assertEquals(0.0, new MonstersAndBunnies().survivalProbability(M, B), 1e-9);
	}
	
	@Test(timeout=2000)
	public void test3() {
		int M = 1;
		int B = 47;
		assertEquals(0.0, new MonstersAndBunnies().survivalProbability(M, B), 1e-9);
	}
	
	@Test(timeout=2000)
	public void test4() {
		int M = 2;
		int B = 0;
		assertEquals(0.3333333333333333, new MonstersAndBunnies().survivalProbability(M, B), 1e-9);
	}
}
