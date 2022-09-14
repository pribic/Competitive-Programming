package codeforce.edu.binarySearch.step3;

import java.util.Scanner;

public class BSplittingAnArray {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int n = sc.nextInt();
      int k = sc.nextInt();
      int[] arr = new int[n];
      for (int i = 0; i < n; i++) {
        arr[i] = sc.nextInt();
      }
      long l = 0;
      long r = Long.MAX_VALUE;
      while (r > l + 1) {
        long mid = l + (r - l) / 2;
        System.out.println(l + " " + mid + " " + r);
        if (f(mid, k, arr)) {
          r = mid;
        } else {
          l = mid;
        }
      }
      System.out.println(r);
    }
  }

  //10 4
//1 3 2 4 10 8 4 2 5 3
  private static boolean f(long sum, int k, int[] arr) {
    int cnt = 1;
    long curSum = 0;
    for (int i = 0; i < arr.length; i++) {
      if (curSum + arr[i] <= sum) {
        curSum += arr[i];
      } else {
        cnt++;
        curSum = arr[i];
        if(curSum > sum)
          return false;
      }
    }
    return cnt <= k;
  }

}
