import java.util.*;
import java.math.*;
import static java.lang.Math.*;

public class PosNegDice {
	
	public int[] evaluateRoll(int T, int[] positiveDice, int[] negativeDice) {
		int[] freqP = new int[7];
		int[] freqN = new int[7];
		for(int i : positiveDice) {
			freqP[i]++;
			freqN[i]--;
		}
		for(int i : negativeDice) {
			freqN[i]++;
			freqP[i]--;
		}
		int max = 0;
		int success = 0;
		for(int i = 1; i < freqP.length; i++) {
			if ( freqP[i] > 0 && T  >= i) {
				success = 1;
				break;
			}
		}
		int cnt = 0;
		for(int i : freqN)
			cnt = cnt + ((i > 0 ) ? i : 0);
		return new int[]{ success, cnt};
	}
}
