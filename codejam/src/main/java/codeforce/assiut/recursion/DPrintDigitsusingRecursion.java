package codeforce.assiut.recursion;

import java.util.Scanner;

/**
 * @author pribic (Priyank Doshi)
 * @since 10/05/21
 */
public class DPrintDigitsusingRecursion {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        printDigits(n);
        System.out.println();
      }
    }
  }

  private static void printDigits(int n) {
    if (n < 10) {
      System.out.print(n + " ");
      return;
    }
    //I want to print this once I have printed everything else
    printDigits(n / 10);
    System.out.print(n % 10 + " ");
  }
}