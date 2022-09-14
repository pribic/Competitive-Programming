package codeforce.div2.r684;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class A {

  public static void main(String[] args) {
    double d = 283.50d;
    double total = 0.0d;
    for(int i = 3182; i<= 3200; i++) {
      total += d;
      System.out.println(i + "=" + d);
      d *= 1.2;
    }
    System.out.println(total);
    try (Scanner sc = new Scanner(System.in)) {
      int t = sc.nextInt();
      while (t-- > 0) {
        int n = sc.nextInt();
        int c0 = sc.nextInt();
        int c1 = sc.nextInt();
        int h = sc.nextInt();
        String s = sc.next();
        int zero = 0;
        int one = 0;
        for(char x : s.toCharArray()) {
          switch (x) {
            case '0':
              zero++;
              break;
            default:
              one++;
              break;
          }
        }
        System.out.println(Math.min(zero*c0, zero*h + zero*c1) + Math.min(one*c1, one*h + one*c0)); 
      }
    }
  }
}

//0 1 2 3 4 5 || 6 7 8 9 10 11 || 12









