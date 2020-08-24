package codeforce.edu.binarySearch.step1;

import java.util.Scanner;

public class ProblemA {

  public static void main(String[] args) {
    try(Scanner sc = new Scanner(System.in)) {
      int n = sc.nextInt(), k = sc.nextInt();
      int[] arr = new int[n];
      for (int i = 0; i < n; i++) {
        arr[i] = sc.nextInt();
      }

      for (int i = 0; i < k; i++) {
        int q = sc.nextInt();
        int l = 0;
        int r = n - 1;
        boolean found = false;
        while (l <= r) {
          int mid = l + (r - l) / 2;
          if(arr[mid] == q) {
            found = true;
            break;
          }
          if(q > arr[mid])
            l = mid+1;
          else
            r = mid - 1;
        }
        System.out.println(found ? "YES" : "NO");
      }
    }
  }
}
