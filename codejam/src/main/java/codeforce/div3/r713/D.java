package codeforce.div3.r713;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * @author pribic (Priyank Doshi)
 * @since 10/04/21
 */
public class D {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        // System.out.println("str.toString() = " + Arrays.toString(str));
        List<Long> arr = new ArrayList<>(n + 2);
        long sum = 0;
        for (int i = 0; i < n + 2; i++) {
          long val = sc.nextLong();
          arr.add(val);
          sum += val;
        }
        Collections.sort(arr);
        //suppose guess is >= sum
        if (sum - arr.get(n + 1) - arr.get(n) == arr.get(n) || sum - arr.get(n + 1) - arr.get(n) == arr.get(n + 1)) {
          for (int i = 0; i < n; i++) {
            long num = arr.get(i);
            System.out.print(num + " ");
          }
          System.out.println();
        } else { // guess is < sum
          //so each number upto n can be guess
          boolean found = false;
          for (int i = 0; i < n; i++) {
            long tempSum = sum - arr.get(n + 1) - arr.get(i);
            if (tempSum == arr.get(n + 1)) {
              long t = arr.get(i);
              arr.set(i, arr.get(n));
              arr.set(n, arr.get(n + 1));
              arr.set(n + 1, t);
              found = true;
              break;
            }
          }

          if (!found) {
            System.out.println(-1);
          } else {
            for (int i = 0; i < n; i++) {
              System.out.println(arr.get(i) + " ");
            }
            System.out.println();
          }
        }
      }
    }
  }
}