package codeforce.div2.r655;

import java.util.Scanner;

public class ProblemB {
  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int t = sc.nextInt();
      for (int i = 0; i < t; i++) {
        int n = sc.nextInt();
        if (n % 2 == 0) {
          System.out.println(n / 2 + " " + n / 2);
          continue;
        }
        int upper = (int) Math.sqrt(n);
        boolean found = false;
        for (int j = 3; j <= upper && !found; j++) {
          if (n % j == 0) {
            j = n / j;
            System.out.println(j + " " + (n - j));
            found = true;
          }
        }
        if (!found) {
          //handle primes
          System.out.println(" 1 " + (n - 1));
        }
      }
    }
  }
}
