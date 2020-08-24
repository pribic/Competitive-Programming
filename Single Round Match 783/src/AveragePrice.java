import java.util.HashSet;

public class AveragePrice {
	
	public double nonDuplicatedAverage(int[] prices) {
		HashSet<Integer> set = new HashSet<>();
		for(int p : prices) {
			set.add(p);
		}
		double sum = 0;
		for(int p : set) {
			sum += p;
		}
		
		return sum / set.size();
	}
}
