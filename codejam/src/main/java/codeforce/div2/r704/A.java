package codeforce.div2.r704;

import java.util.Scanner;

/**
 * @author pribic (Priyank Doshi)
 * @since 23/02/21
 */
public class A {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        long p = sc.nextLong();
        long a = sc.nextLong();
        long b = sc.nextLong();
        long c = sc.nextLong();
        
        long a1 = (p + a - 1) / a;
        long b1 = (p + b - 1) / b;
        long c1 = (p + c - 1) / c;
        
        long d1 = a1*a - p;
        long d2 = b1*b - p;
        long d3 = c1*c - p;

        System.out.println(Math.min(d1, Math.min(d2,d3)));
      }
    }
  }
}