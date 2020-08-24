import org.junit.Test;
import static org.junit.Assert.*;

public class TrivialWordSearchTest {
	
	@Test(timeout=2000)
	public void test0() {
		String w = "DOG";
		assertArrayEquals(new String[] {"DGOODDO", "DODGOGG", "DGOODDD", "GOOGDGO", "GOGGOGD", "DOOGDOO", "OOGGOOD" }, new TrivialWordSearch().construct(w));
	}
	
	@Test(timeout=2000)
	public void test1() {
		String w = "ABBA";
		assertArrayEquals(new String[] {"BABA", "AABB", "ABAA", "ABAB" }, new TrivialWordSearch().construct(w));
	}
	
	@Test(timeout=2000)
	public void test2() {
		String w = "TOPCODER";
		assertArrayEquals(new String[] {"TOPTOPTOPTOP", "TOPTORTOPTOP", "TOPTOETOPTOP", "TOPTODTOPTOP", "TOPTOOTOPTOP", "TOPTOCTOPTOP", "TOPTOPTOPTOP", "TOPTOOTOPTOP", "TOPTOTTOPTOP", "TOPTOPTOPTOP", "TOPTOPTOPOOP", "TOPTOPTOPTOP" }, new TrivialWordSearch().construct(w));
	}
	
	@Test(timeout=2000)
	public void test3() {
		String w = "XXXXX";
		assertArrayEquals(new String[] { }, new TrivialWordSearch().construct(w));
	}
	
	@Test(timeout=2000)
	public void test4() {
		String w = "E";
		assertArrayEquals(new String[] {"E" }, new TrivialWordSearch().construct(w));
	}
}
