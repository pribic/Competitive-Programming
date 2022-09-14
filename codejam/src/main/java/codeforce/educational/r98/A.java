package codeforce.educational.r98;

import java.util.Scanner;

public class A {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int t = sc.nextInt();
      for (int tt = 0; tt < t; tt++) {
        long x = sc.nextLong();
        long y = sc.nextLong();
        long min = Math.min(x, y);
        if(x == y)
          System.out.println(2*x);
        else
          System.out.println(2 * min + (Math.max(x, y) - min) * 2 - 1);
      }
    }
  }
}

//10101010

//01101010 