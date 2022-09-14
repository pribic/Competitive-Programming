package codeforce.div3.r693;

import java.util.Scanner;

/**
 * @author pribic (Priyank Doshi)
 * @since 05/01/21
 */
public class C {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int[] chips = new int[n];
        for (int i = 0; i < n; i++) {
          chips[i] = sc.nextInt();
        }
        long[] bestScore = new long[n];
        long maxScore = Long.MIN_VALUE;
        for (int i = n - 1; i >= 0; i--) {
          long score = chips[i];
          if(i + chips[i] < n)
            score += bestScore[i + chips[i]];
          bestScore[i] = score;
          maxScore = Math.max(maxScore, score);
        }
        System.out.println(maxScore);
      }
    }
  }
}