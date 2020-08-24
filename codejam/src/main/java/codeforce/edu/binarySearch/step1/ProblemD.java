package codeforce.edu.binarySearch.step1;

import java.util.Arrays;
import java.util.Scanner;
import java.util.TreeSet;

public class ProblemD {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int n = sc.nextInt();
      int[] arr = new int[n];
      for (int i = 0; i < n; i++) {
        arr[i] = sc.nextInt();
      }
      Arrays.sort(arr);
      int k = sc.nextInt();
      //  false false true true true true true true
      for (int i = 0; i < k; i++) {
        new TreeSet<>();
        int q = sc.nextInt();
        int qq = sc.nextInt();
        int l = 0;
        int r = n - 1;
        int bestL = -1;
        while (l <= r) {
          int mid = l + (r - l) / 2;
          if (arr[mid] < q) {
            bestL = mid;
            l = mid + 1;
          } else {
            r = mid - 1;
          }
        }

        int bestR = n;
        l = 0;
        r = n - 1;
        while (l <= r) {
          int mid = l + (r - l) / 2;
          if (arr[mid] > qq) {
            bestR = mid;
            r = mid - 1;
          } else {
            l = mid + 1;
          }
        }
        bestL++;
        System.out.print(bestR - bestL + " ");
      }
    }
  }
}
