package codeforce.educational.r96;

import java.util.Arrays;
import java.util.Scanner;

public class ProblemB {
  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int t = sc.nextInt();
      for (int tt = 0; tt < t; tt++) {
        int n = sc.nextInt();
        int k = sc.nextInt();
        long[] arr = new long[n];
        for (int i = 0; i < n; i++) {
          arr[i] = sc.nextInt();
        }
        Arrays.sort(arr);
        for (int i = 0; i < k; i++) {
          arr[n - 1] += arr[n - 2 - i];
        }
        System.out.println(arr[n - 1]);
      }
    }
  }
}
