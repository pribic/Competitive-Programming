package codeforce.div3.r674;

import java.util.Scanner;

public class A_FloorNumber {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int t = sc.nextInt();
      for (int i = 0; i < t; i++) {
        int n = sc.nextInt();
        int x = sc.nextInt();
        if (n <= 2) {
          System.out.println("1");
        } else {
          n -= 2;
          System.out.println(1 + ((n + x - 1) / x));
        }
      }
    }
  }
}
