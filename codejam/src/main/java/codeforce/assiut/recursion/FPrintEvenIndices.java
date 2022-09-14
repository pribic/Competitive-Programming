package codeforce.assiut.recursion;

import java.util.Scanner;

/**
 * @author pribic (Priyank Doshi)
 * @since 10/05/21
 */
public class FPrintEvenIndices {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = 1;//sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
          arr[i] = sc.nextInt();
        }
        printEven(arr, 0);
        System.out.println();
      }
    }
  }

  private static void printEven(int[] arr, int idx) {
    if(idx >= arr.length)
      return;
    printEven(arr, idx + 2);
    System.out.print(arr[idx] + " ");
  }
}