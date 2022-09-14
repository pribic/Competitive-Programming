package codeforce.edu.binarySearch.step3;

import java.util.Scanner;

public class CCowsInStalls {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int n = sc.nextInt();
      int k = sc.nextInt();
      int[] arr = new int[n];
      for (int i = 0; i < n; i++) {
        arr[i] = sc.nextInt();
      }

      //true true ... false false

      int l = 1;
      int r = 1_000_000_000;
      while (r > l + 1) {
        int mid = l + (r - l) / 2;
      //  System.out.println(l + " " + mid + " " + r);
        if (f(mid, k, arr)) {
          l = mid;
        } else {
          r = mid;
        }
      }
      System.out.println(l);
    }
  }

  private static boolean f(int mid, int k, int[] arr) {
    int lastCow = Integer.MIN_VALUE/2;
    int cowCnt = 0;
    for (int i = 0; i < arr.length; i++) {
      if (arr[i] - lastCow >= mid) {
        cowCnt++;
        lastCow = arr[i];
      }
    }
    return cowCnt >= k;
  }

}
