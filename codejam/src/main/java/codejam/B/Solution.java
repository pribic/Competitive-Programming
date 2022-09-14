package codejam.B;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * @author pribic (Priyank Doshi)
 * @since 10/04/21
 */
public class Solution {

  public static void main(String[] args) {
    Boolean[] primes = basicSieve3(500);
    List<Long> primeNums = new ArrayList<>();
    for (int i = 2; i < primes.length; i++) {
      if (primes[i])
        primeNums.add((long) i);
    }
    
    try (Scanner sc = new Scanner(System.in)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        System.out.print("Case #" + tt + ": ");
        int m = sc.nextInt();
        Map<Long, Long> input = new HashMap<>();
        long sum = 0;
        for (int i = 0; i < m; i++) {
          long a = sc.nextLong();
          long b = sc.nextLong();
          sum += a * b;
          input.put(a, b);
        }

        long ans = 0;
        for (long num = sum; num > Math.max(1, sum - 29941); num--) {
          Map<Long, Long> primeFactors = findPrimes(num, input.keySet());
          if (primeFactors.size() > 0) {
            long curSum = 0;
            boolean flag = false;
            for (long primeFactor : primeFactors.keySet()) {
              if (!input.containsKey(primeFactor) || input.get(primeFactor) < primeFactors.get(primeFactor)) {
                flag = true;
                break;
              }
              curSum += primeFactor * (input.get(primeFactor) - primeFactors.get(primeFactor));
            }
            for(long primeFactor : input.keySet()) {
              if(!primeFactors.containsKey(primeFactor)) {
                curSum += primeFactor * input.get(primeFactor);
              }
            }
            if (!flag && num == curSum) {
              ans = num;
              break;
            }
          }
        }
        System.out.println(ans);
      }
    }
  }


  private static Map<Long, Long> findPrimes(long num, Set<Long> primeNums) {
    Map<Long, Long> map = new HashMap<>();
    for(long prime : primeNums) {
      while (num % prime == 0) {
        map.put(prime, map.getOrDefault(prime, 0l) + 1);
        num /= prime;
      }
    }
    return num != 1 ? Collections.emptyMap() : map;
  }

  private static Boolean[] basicSieve3(int n) {
    Boolean[] primes = new Boolean[n + 1];
    Arrays.fill(primes, true);
    for (int i = 2; i * i <= n; i++) {
      if (primes[i]) {
        for (int j = i * i; j <= n; j += i) {
          primes[j] = false;
        }
      }
    }
    return primes;
  }
}