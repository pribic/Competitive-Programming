package codeforce.edu.binarySearch.step1;

import java.util.Scanner;

public class ProblemB {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int n = sc.nextInt(), k = sc.nextInt();
      int[] arr = new int[n];
      for (int i = 0; i < n; i++) {
        arr[i] = sc.nextInt();
      }
    //  - - - - -
      for (int i = 0; i < k; i++) {
        int q = sc.nextInt();
        int l = 0;
        int r = n - 1;
        int best = -1;
        while (l <= r) {
          int mid = l + (r - l) / 2;
          if(arr[mid] <= q) {
            best = mid;
            l = mid + 1;
          } else {
            r = mid - 1;
          }
          
        }
        System.out.println(best + 1);
      }
    }
  }
}
