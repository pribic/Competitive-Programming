import java.util.*;
import java.math.*;
import static java.lang.Math.*;

public class EllysPalMulDiv2 {
	
	public int getMin(int X) {
		for(int i = 1; i <= 1000; i++) {
			long mul = i * X;
			String s  = String.valueOf(mul);
			if(s.equalsIgnoreCase(new StringBuilder(s).reverse().toString()))
				return i;
		}
		return -1;
	}
}
