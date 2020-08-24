package kickstart.Y2020.round1E.HighBuildings;

import java.util.Scanner;

public class Solution {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int t = sc.nextInt();
      for (int tt = 1; tt <= t; tt++) {
        System.out.print("Case #" + tt + ": ");
        int n = sc.nextInt();
        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = sc.nextInt();

        int[] ans = new int[n];

        if (c + (a - c) + (b - c) > n) {
          System.out.println("IMPOSSIBLE");
          continue;
        } else {
          for(int i = 0; i < a -c; i++) ans[i] = n - 1;
          for (int i = a - c; i < a; i++) ans[i] = n;
          for (int i = n - 1; i > n - 1 - (b - c); i--) ans[i] = n - 1;
        }
        for (int ii : ans) {
          if (ii == 0) {
            System.out.print("1 ");
          } else
            System.out.print(ii + " ");
        }
        System.out.println();
      }
    }
  }

}
