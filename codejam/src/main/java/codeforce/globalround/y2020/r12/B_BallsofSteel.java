package codeforce.globalround.y2020.r12;

import java.util.Scanner;

public class B_BallsofSteel {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int t = sc.nextInt();
      while (t-- > 0) {
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[][] points = new int[n][2];
        for (int i = 0; i < n; i++) {
          points[i][0] = sc.nextInt();
          points[i][1] = sc.nextInt();
        }
        int cnt = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
          int localCnt = 0;
          for (int j = 0; j < n; j++) {
            if (i != j) {
              if(manDistance(points, i, j) <= k)
                localCnt++;
            }
          }
          cnt = Math.max(cnt, localCnt);
        }
        System.out.println(cnt == n - 1 ? 1 : -1);
      }
    }
  }

  private static int manDistance(int[][] points, int p, int q) {
    return Math.abs(points[p][0] - points[q][0]) + Math.abs(points[p][1] - points[q][1]);
  }
}
