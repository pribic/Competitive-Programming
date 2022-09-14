package codeforce.div2.r711;

import java.util.Scanner;

/**
 * @author pribic (Priyank Doshi)
 * @since 29/03/21
 */
public class B {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int w = sc.nextInt();
        int[] arr = new int[n];
        int[] freq = new int[31];
        for (int i = 0; i < n; i++) {
          arr[i] = sc.nextInt();
          String toBinaryString = Integer.toBinaryString(arr[i]);
          freq[toBinaryString.length() - 1]++;
        }
        long height = 1;
        int cnt = 0;
        int curW = w;
        int start = Integer.toBinaryString(w).length() - 1;
        while (cnt < n) {
          if(curW <= 0) {
            height++;
            curW = w;
          }
          for (int i = start; i >= 0 && curW > 0; i--) {
            if (freq[i] > 0) {
              if (Math.pow(2, i) <= curW) {
                int maxTaken = (int) (curW / Math.pow(2, i));
                int minTaken = Math.min(maxTaken, freq[i]);
                cnt += minTaken;
                freq[i] -= minTaken;
                curW -= minTaken*Math.pow(2, i);
              }
            }
          }
        }
        System.out.println(height);
      }
    }
  }
}