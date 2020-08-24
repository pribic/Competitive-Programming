import org.junit.Test;
import static org.junit.Assert.*;

public class AqaAsadiNamesTest {
	
	@Test(timeout=2000)
	public void test0() {
		//{"Mohammad Reza", "Yasaman Sadat", "Umi Kulsum", "Girl"}
		String Dad = "Mohammad Reza";
		String Mom = "Yasaman Sadat";
		String FirstChild = "Umi Kulsum";
		String Gender = "Girl";
		assertEquals("Yasaman Kulsum", new AqaAsadiNames().getName(Dad, Mom, FirstChild, Gender));
	}
	
	@Test(timeout=2000)
	public void test1() {
		String Dad = "Mohammad Reza";
		String Mom = "Yasaman Sadat";
		String FirstChild = "Baqer Ali";
		String Gender = "Girl";
		assertEquals("Sadat Yasaman", new AqaAsadiNames().getName(Dad, Mom, FirstChild, Gender));
	}
	
	@Test(timeout=2000)
	public void test2() {
		String Dad = "Mohammad Reza";
		String Mom = "Yasaman Sadat";
		String FirstChild = "Umi Kulsum";
		String Gender = "Girl";
		assertEquals("Yasaman Kulsum", new AqaAsadiNames().getName(Dad, Mom, FirstChild, Gender));
	}
	
	@Test(timeout=2000)
	public void test3() {
		String Dad = "Mohammad Reza";
		String Mom = "Yasaman Sadat";
		String FirstChild = "Umi Kulsum";
		String Gender = "Boy";
		assertEquals("Reza Mohammad", new AqaAsadiNames().getName(Dad, Mom, FirstChild, Gender));
	}
	
	@Test(timeout=2000)
	public void test4() {
		String Dad = "Mohammad Ali";
		String Mom = "Yasaman Sadat";
		String FirstChild = "Mohammad Reza";
		String Gender = "Boy";
		assertEquals("Mohammad Reza", new AqaAsadiNames().getName(Dad, Mom, FirstChild, Gender));
	}
	
	@Test(timeout=2000)
	public void test5() {
		String Dad = "Dhikrullah Ali";
		String Mom = "Umi Kulsum";
		String FirstChild = "Reza Hosseinzadeh";
		String Gender = "Boy";
		assertEquals("Dhikrullah Hosseinzadeh", new AqaAsadiNames().getName(Dad, Mom, FirstChild, Gender));
	}
}
