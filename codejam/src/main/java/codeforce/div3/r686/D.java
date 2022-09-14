package codeforce.div3.r686;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class D {

  public static void main(String[] args) {

    try (Scanner sc = new Scanner(System.in)) {
      int t = sc.nextInt();
      while (t-- > 0) {

        long n = sc.nextLong();
        // System.out.println("all factors of " + n);
        Set<Long> allFactors = new TreeSet<>();
        for (long i = 2; i <= Math.sqrt(n); i++) {
          if (n % i == 0) {
            allFactors.add(i);
            allFactors.add(n / i);
          }
        }
        if (allFactors.isEmpty()) {
          System.out.println(1);
          System.out.println(n);
          continue;
        }
        //2.6.12 - 144
        //2.2.2.18
        //3.18
        long ans = Integer.MIN_VALUE;
        List<Long> anss = new ArrayList<>();
        for(long fact : allFactors) {
          List<Long> factors = new ArrayList<>();
          long tempAns = find(factors, n, fact);
          if(tempAns > ans) {
            //update ans
            ans = tempAns;
            anss.clear();
            anss.addAll(factors);
          }
        }
        System.out.println(ans);
        for (long x : anss)
          System.out.print(x + " ");
        System.out.println();
      }
    }
  }

  static long find(List<Long> factors, long n, long smallestFactor) {
    long ans = 1;
    while (true) {
      long otherFactor = n / smallestFactor;
      if (otherFactor == 1 || otherFactor % smallestFactor != 0) {
        factors.add(n);
        break;
      }
      n = otherFactor;
      ans++;
      factors.add(smallestFactor);
    }
    return ans;
  }
}
