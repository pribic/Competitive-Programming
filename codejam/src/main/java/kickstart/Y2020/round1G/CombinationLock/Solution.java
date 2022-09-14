package kickstart.Y2020.round1G.CombinationLock;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Solution {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        System.out.print("Case #" + tt + ": ");
        int w = sc.nextInt();
        int n = sc.nextInt();
        int[] wheels = new int[w];
        for (int i = 0; i < w; i++) {
          wheels[i] = sc.nextInt();
        }
        int ans = Integer.MAX_VALUE;
        for(int i = 1; i <= n; i++) {
          int curSteps = 0;
          for(int x : wheels) {
            if(x != i) {
              if(x > i) {
                curSteps += Math.min(x - i, n - x + i);
              } else {
                curSteps += Math.min(i - x, x + n - i);
              }
            }
          }
          ans = Math.min(ans, curSteps);
        }
        System.out.println(ans);
      }
    }
    
  }
}
