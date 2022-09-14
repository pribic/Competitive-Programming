package codeforce.educational.r102;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author pribic (Priyank Doshi)
 * @since 14/01/21
 */
public class A {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int d = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
          arr[i] = sc.nextInt();
        }
        Arrays.sort(arr);
        int min1 = arr[0];
        int min2 = arr[1];
        if(arr[n - 1] <= d || min1 + min2 <= d)
          System.out.println("YES");
        else
          System.out.println("NO");
      }
    }
  }
}