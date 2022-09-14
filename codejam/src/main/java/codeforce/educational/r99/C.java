package codeforce.educational.r99;

import java.util.Scanner;

public class C {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int t = sc.nextInt();
      while (t-- > 0) {
        int x = sc.nextInt();
        int y = sc.nextInt();
        System.out.println((x - 1) + " " + y);
      }
    }
  }
}


