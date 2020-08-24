import java.util.*;
import java.math.*;
import static java.lang.Math.*;

public class EmoticonsDiv2 {

	public static void main(String[] args) {
		System.out.println(new EmoticonsDiv2().printSmiles(8));
	}
	public int printSmiles(int smiles) {
		int box = 1;
		int cp = 1;
		return findNum(box, cp, smiles, 0);
	}

	private int findNum(int box, int cp, int smiles, int second) {
		if(box == smiles)
			return second;
		if(box > smiles)
			return Integer.MAX_VALUE;
		return Math.min(findNum(box + cp, cp, smiles, second + 1),
			findNum(box, box, second, second + 1));
	}
}
