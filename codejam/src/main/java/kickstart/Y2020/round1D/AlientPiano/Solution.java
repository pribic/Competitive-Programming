package kickstart.Y2020.round1D.AlientPiano;

import java.util.Scanner;

public class Solution {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
          arr[i] = sc.nextInt();
        }
        int ans = 0;
        
        int increase = 0;
        int decrease = 0;
        
        for (int i = 1; i < n; i++) {
          if(arr[i] > arr[i-1]) {
            increase++;
            decrease = 0;
          }
          else if(arr[i] < arr[i-1]) {
            decrease++;
            increase = 0;
          }
          else {
            //do nothing
          }
          if(Math.abs(increase) >= 4 || Math.abs(decrease) >= 4) {
            ans++;
            increase = 0;
            decrease = 0;
          }
        }
        
        System.out.println("Case #" + tt + ": " + ans);
      }
    }
  }
}
