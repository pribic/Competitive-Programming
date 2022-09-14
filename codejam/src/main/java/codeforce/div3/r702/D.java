package codeforce.div3.r702;

import java.util.Scanner;

/**
 * @author pribic (Priyank Doshi)
 * @since 16/02/21
 */
public class D {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int[] perm = new int[n];
        for (int i = 0; i < n; i++) {
          perm[i] = sc.nextInt();
        }
        int[] ans = new int[n];
        buildDepth(perm, 0, n, 0, ans);
        for (int depth : ans) System.out.print(depth + " ");
        System.out.println();
      }
    }
  }

  private static void buildDepth(int[] perm, int left, int right, int curDepth, int[] ans) {
    if (left >= right || left >= ans.length || right < 0) return;
    //find max in this range
    int max = -1;
    int maxIndex = -1;
    for (int i = left; i < right; i++) {
      if (perm[i] > max) {
        max = perm[i];
        maxIndex = i;
      }
    }
    ans[maxIndex] = curDepth;
    buildDepth(perm, left, maxIndex, curDepth + 1, ans);
    buildDepth(perm, maxIndex + 1, right, curDepth + 1, ans);
  }
}