import org.junit.Test;
import static org.junit.Assert.*;

public class AqaAsadiGroupsTest {
	
	@Test(timeout=2000)
	public void test0() {
		int[] Skills = new int[] {1, 2};
		int k = 2;
		assertEquals(2L, new AqaAsadiGroups().minimumDifference(Skills, k));
	}
	
	@Test(timeout=2000)
	public void test1() {
		int[] Skills = new int[] {1, 2};
		int k = 1;
		assertEquals(0L, new AqaAsadiGroups().minimumDifference(Skills, k));
	}
	
	@Test(timeout=2000)
	public void test2() {
		int[] Skills = new int[] {1, 2, 3, 4};
		int k = 2;
		assertEquals(8L, new AqaAsadiGroups().minimumDifference(Skills, k));
	}
	
	@Test(timeout=2000)
	public void test3() {
		int[] Skills = new int[] {1, 2};
		int k = 5;
		assertEquals(80L, new AqaAsadiGroups().minimumDifference(Skills, k));
	}
}
