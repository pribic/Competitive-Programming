package codeforce.div3.r702;

import java.util.Scanner;

/**
 * @author pribic (Priyank Doshi)
 * @since 16/02/21
 */
public class B {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
          arr[i] = sc.nextInt();
        }
        int[] reminder = new int[3];
        for (int num : arr) reminder[num % 3]++;
        int avg = n / 3;
        int ans = 0;

        boolean anyChange = true;
        while (anyChange) {
          anyChange = false;
          for (int i = 0; i < 3; i++)
            if (reminder[i] > avg) {
              anyChange = true;
              int diff = reminder[i] - avg;
              ans += diff;
              reminder[(i + 1) % 3] += diff;
              reminder[i] = avg;
            }
        }
        System.out.println(ans);

/**
 * [0,2,5,5,4,8] -> 1,1,4 -> 2, 1, 3 -> 3, 1, 2 -> 2, 2, 2
 * [2 0 2 1 0 0] -> 3,1,2 -> 2, 2, 2 
 *
 */
      }
    }
  }
}