package codeforce.div2.r652;

import java.util.Scanner;

public class ProblemA {
  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int t = sc.nextInt();
      for (int i = 0; i < t; i++) {
        long a = sc.nextInt();
        long b = sc.nextInt();
        long c = sc.nextInt();
        if (a * b <= c) {
          System.out.println("1 -1");
        } else if (a < c) {
          System.out.println("1 " + b);
        } else if( a == c || a > c) {
          System.out.println("-1 "+ b);    
        }
      }
    }
  }
}
