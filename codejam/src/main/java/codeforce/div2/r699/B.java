package codeforce.div2.r699;

import java.util.Scanner;

/**
 * @author pribic (Priyank Doshi)
 * @since 05/02/21
 */
public class B {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        /**
         * 4 1 2 3
         * 4 2 2 3
         * 4 2 3 3
         *
         * 1 1 1...100
         * (100 - 1)^2 = 10000 - 200 + 1 = 9801 
         *
         */

        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] heights = new int[n];
        for (int i = 0; i < n; i++) {
          heights[i] = sc.nextInt();
        }
        if (k > 9801) System.out.println(-1);
        else {
          //simulate
          int ans = -1;
          boolean notReachedEnd = true;
          for(int turn = 1; turn <= k && notReachedEnd; turn++) {
            //repeat same thing
            for(int i = 0; i < n; i++) {
              if(i == n - 1) {
                //reached end, means all the next ones will also reach end
                notReachedEnd = false;
                ans = -1;
                continue;
              }
              if(heights[i] >= heights[i+1]) continue;
              heights[i]++;
              ans = i + 1;
              break;
            }
          }
          System.out.println(ans);
        }
      }
    }
  }
}