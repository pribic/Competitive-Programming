import org.junit.Test;
import static org.junit.Assert.*;

public class CutTheCubeTest {
	
	@Test(timeout=2000)
	public void test0() {
		int L = 1;
		int B = 1;
		int H = 1;
		assertEquals(2, new CutTheCube().findWinner(L, B, H));
	}
	
	@Test(timeout=2000)
	public void test1() {
		int L = 2;
		int B = 1;
		int H = 1;
		assertEquals(1, new CutTheCube().findWinner(L, B, H));
	}
	
	@Test(timeout=2000)
	public void test2() {
		int L = 2;
		int B = 2;
		int H = 1;
		assertEquals(1, new CutTheCube().findWinner(L, B, H));
	}
	
	@Test(timeout=2000)
	public void test3() {
		int L = 97931;
		int B = 95210;
		int H = 92383;
		assertEquals(1, new CutTheCube().findWinner(L, B, H));
	}
}
