package codeforce.div2.r678;

import java.util.Scanner;

public class A {
  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int t = sc.nextInt();
      for (int i = 0; i < t; i++) {
        int n = sc.nextInt();
        int k = sc.nextInt();
        long sum = 0;
        for (int j = 0; j < n; j++) {
          sum += sc.nextInt();
        }
        System.out.println(sum == k ? "YES" : "NO");
      }
    }
  }

}
