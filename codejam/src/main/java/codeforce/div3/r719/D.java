package codeforce.div3.r719;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author pribic (Priyank Doshi)
 * @since 05/05/21
 */
public class D {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
          arr[i] = sc.nextInt();
        }
        int[] diff = new int[n];
        for (int i = 1; i <= n; i++) {
          diff[i - 1] = arr[i - 1] - i;
        }
        Map<Integer, Integer> freq = new HashMap<>();
        for (int i = 0; i < n; i++) {
          freq.put(diff[i], freq.getOrDefault(diff[i], 0) + 1);
        }
        // System.out.println("Arrays.toString(arr) = " + Arrays.toString(arr));
        //System.out.println("Arrays.toString(diff) = " + Arrays.toString(diff));
        //System.out.println("freq.toString() = " + freq.toString());
        long ans = 0;
        for (long nn : freq.values()) {
          nn--;
          ans += (nn * (nn + 1L)) / 2;
        }
        System.out.println(ans);
      }

    }
  }
}
