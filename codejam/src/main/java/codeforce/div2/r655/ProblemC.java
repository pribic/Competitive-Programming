package codeforce.div2.r655;

import java.util.Scanner;

public class ProblemC {
  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int t = sc.nextInt();
      for (int i = 0; i < t; i++) {
        int n = sc.nextInt();
        int cnt = 0;
        int leftCnt = 0;
        int rightCnt = 0;
        boolean anyChange = false;
        int[] arr = new int[n];
        for (int j = 1; j <= n; j++) {
          int x = sc.nextInt();
          cnt = cnt + (x == j ? 0 : 1);
          arr[j - 1] = x;
        }
        for (int j = 0; j < n; j++) {
          if (arr[j] == j + 1)
            leftCnt++;
          else
            break;
        }
        for (int j = n - 1; j >= 0; j--) {
          if (arr[j] == j + 1)
            rightCnt++;
          else
            break;
        }
        if (cnt == 0)
          System.out.println("0");
        else if (leftCnt + rightCnt + cnt == n)
          System.out.println("1");
        else
          System.out.println("2");
      }
    }
  }
}