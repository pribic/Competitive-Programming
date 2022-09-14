package codeforce.div2.r716;

import java.util.Scanner;

/**
 * @author pribic (Priyank Doshi)
 * @since 19/04/21
 */
public class A {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int[] arr = new int[n];
        boolean valid = true;
        for (int i = 0; i < n; i++) {
          arr[i] = sc.nextInt();
          if((int)Math.sqrt(arr[i]) * (int)Math.sqrt(arr[i]) != arr[i])
            valid = false;
        }
        System.out.println(!valid ? "YES" : "NO");
      }
    }
  }
}