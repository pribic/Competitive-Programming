package codeforce.educational.r106;

import java.util.Scanner;

/**
 * @author pribic (Priyank Doshi)
 * @since 18/03/21
 */
public class A {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int k1 = sc.nextInt();
        int k2 = sc.nextInt();
        int w = sc.nextInt();
        int b = sc.nextInt();
        
        if(k1 > k2) {
            int temp = k1;
            k1 = k2;
            k2 = temp;
        }
        //k1 small, k2 big
        int diff = k2 - k1;
        int white = diff/2 + k1;
        int black = diff/2 + (n - k2);
        System.out.println(w <= white && b <= black ? "YES" : "NO");
      }
    }
  }
}