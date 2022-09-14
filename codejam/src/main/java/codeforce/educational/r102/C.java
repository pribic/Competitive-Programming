package codeforce.educational.r102;

import java.util.Scanner;

/**
 * @author pribic (Priyank Doshi)
 * @since 14/01/21
 */
public class C {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int k = sc.nextInt();
        int k1 = k;
        for(int i = 0; i < n - k; i++)
          System.out.print(k1-- + " ");
        for(int i = 1; i <= 2*k - n; i++)
          System.out.print(i + " ");
        System.out.println();
          
        /**
         * 1 2 3 4 5 4 3 - k=5, n=7
         * 
         * 
         */
      }
    }
  }
}