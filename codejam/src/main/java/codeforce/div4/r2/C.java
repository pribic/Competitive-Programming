package codeforce.div4.r2;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author pribic (Priyank Doshi)
 * @since 28/01/21
 */
public class C {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] skills = new int[n];
        for (int i = 0; i < n; i++) {
          skills[i] = sc.nextInt();
        }
        Arrays.sort(skills);
        int l = 0;
        int r = n - 1;
        int teamsCnt = 0;
        while (l <= r) {
          if (skills[r] >= k) {
            teamsCnt++;
            r--;
          } else if (l != r && skills[l] + skills[r] >= k) {
            teamsCnt++;
            l++;
            r--;
          } else {
            l++;
          }
        }
        System.out.println(teamsCnt);
      }
    }
  }
}