package codeforce.globalround.r14;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * @author pribic (Priyank Doshi)
 * @since 03/05/21
 */
public class C {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int m = sc.nextInt();
        int x = sc.nextInt();
        Pair[] pairs = new Pair[n];
        for (int i = 0; i < n; i++) {
          pairs[i] = new Pair(sc.nextInt(), i + 1);
        }
        Arrays.sort(pairs, Comparator.comparingInt(p -> p.val));
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
          ans[pairs[i].idx - 1] = (i % m) + 1;
        }
        System.out.println("YES");
        for (int idx : ans)
          System.out.print(idx + " ");
        System.out.println();
      }
    }
  }

  static class Pair {
    int val, idx;

    public Pair(int val, int idx) {
      this.val = val;
      this.idx = idx;
    }
  }
}