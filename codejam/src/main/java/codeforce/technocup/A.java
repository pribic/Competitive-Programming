package codeforce.technocup;

import java.util.Arrays;
import java.util.Scanner;

public class A {
  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int t = sc.nextInt();
      for (int i = 0; i < t; i++) {
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int j = 0; j < n; j++) {
          arr[j] = sc.nextInt();
        }
      //  System.out.println(Arrays.toString(arr));
        for (int j = n - 1; j >= 0; j--) {
          if (j % 2 == 1)
            System.out.print(arr[j] + " ");
          else
            System.out.print(-arr[j] + " ");
        }
        System.out.println();
      }
    }
  }
}
