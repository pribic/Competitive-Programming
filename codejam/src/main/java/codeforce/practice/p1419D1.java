package codeforce.practice;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author pribic (Priyank Doshi)
 * @since 18/03/21
 */
public class p1419D1 {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = 1;//sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
          arr[i] = sc.nextInt();
        }
        if (n <= 2) {
          System.out.println(0);
          for (int num : arr) System.out.print(num + " ");
          System.out.println();
          continue;
        }
        Arrays.sort(arr);
        int[] ans = new int[n];
        int index = 1;
        for (int num : arr) {
          ans[index] = num;
          index += 2;
          if (index >= n) index = 0;
        }
        System.out.println((n -1) / 2);
        for (int num : ans) System.out.print(num + " ");
        System.out.println();
      }
    }
  }
}