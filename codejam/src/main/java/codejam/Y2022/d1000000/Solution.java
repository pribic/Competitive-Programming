package codejam.Y2022.d1000000;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author pdoshi
 * @since 02/04/22
 */
public class Solution {

  /*
  - sort input numbers
  - we checked if we can make 1 2 3 ... for each given dice
   */


  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int T = sc.nextInt();
    for (int tt = 1; tt <= T; tt++) {
      System.out.print("Case #" + tt + ": ");
      int n = sc.nextInt();
      int[] arr = new int[n];
      for (int i = 0; i < n; i++) {
        arr[i] = sc.nextInt();
      }
      solve(n, arr);
    }
  }
  
  // 1 2 4
     
  // 1 2 3

  private static void solve(int n, int[] arr) {
    Arrays.sort(arr);
    int cnt = 0;
    int need = 1;
    for (int i = 0; i < n; i++) {
      if(arr[i] >= need) {
        cnt = cnt + 1;
        need = need + 1;
      } 
    }
    System.out.println(cnt);
  }

}
