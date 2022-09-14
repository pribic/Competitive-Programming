package codeforce.div2.r678;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class C {
  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int t = sc.nextInt();
      for (int i = 0; i < t; i++) {
        int n = sc.nextInt();
        int x = sc.nextInt();
        int pos = sc.nextInt();
        int[] arr = new int[n];
        for (int j = 0; j < n; j++) {
          arr[j] = j;
        }
        int cnt = binarySearch(arr, pos);
        
      }
    }
  }

  private static int binarySearch(int[] arr, int x) {
    int left = 0;
    int right = arr.length;
    int cnt = 0;
    while (left < right) {
      int middle = (left + right) / 2;
      cnt++;
      if (arr[middle] <= x)
        left = middle + 1;
      else
        right = middle;
    }
    return cnt;
  }

}
