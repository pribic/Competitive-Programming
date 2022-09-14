package codeforce.div2.r672;

import java.util.Scanner;

public class CubesSorting {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int t = sc.nextInt();
      for (int tt = 0; tt < t; tt++) {
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
          arr[i] = sc.nextInt();
        }
        boolean flag = false;
        for (int i = 1; i < n; i++) {
          if (arr[i] >= arr[i - 1]) {
            flag = true;
            break;
          }
        }
        if (flag) {
          System.out.println("YES");
        } else
          System.out.println("NO");
      }
    }
  }
}
