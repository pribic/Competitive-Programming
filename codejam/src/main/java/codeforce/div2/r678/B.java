package codeforce.div2.r678;

import java.util.Scanner;

public class B {
  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int t = sc.nextInt();
      for (int i = 0; i < t; i++) {
        int n = sc.nextInt();

        int start = 3;

        while (isPrime(start) || !isPrime(start + n - 1) ) start++;

        for (int j = 0; j < n; j++) {
          for (int k = 0; k < n; k++) {
            if (j == k) {
              System.out.print(start + " ");
            } else {
              System.out.print("1 ");
            }
          }
          System.out.println();
        }
      }
    }
  }

  private static boolean isPrime(int start) {
    for (int i = 2; i < start; i++) {
      if (start % i == 0)
        return false;
    }
    return true;
  }

}
