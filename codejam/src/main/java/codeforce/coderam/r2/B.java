package codeforce.coderam.r2;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author pribic (Priyank Doshi)
 * @since 15/02/21
 */
public class B {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = 1;//sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt(), m = sc.nextInt(), k = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
          arr[i] = sc.nextInt();
        }
        Arrays.sort(arr);
        long sum = 0;
        for(int i = 0; i < m; i++) sum += arr[n - 1 - i];
        for(int i = 0; i < k - 1; i++) sum += arr[n - m - 1- i];
        System.out.println(sum);
        
      }
    }
  }
}