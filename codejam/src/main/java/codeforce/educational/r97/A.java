package codeforce.educational.r97;

import java.util.Scanner;

public class A {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int t = sc.nextInt();
      for (int tt = 0; tt < t; tt++) {
        long l = sc.nextLong();
        long r = sc.nextLong();
        if(r >= 2 * l) {
          System.out.println("NO");
        }
        else {
          System.out.println("YES");
        }
      }
    }
  }
}

//10101010

//01101010 