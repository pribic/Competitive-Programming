package codeforce.a20j.under1400;

import java.util.Scanner;

public class A_ArrivaloftheGeneral {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int n = sc.nextInt();
      int minIndexOfMax = 0, maxIndexOfMin = 0;
      int min = Integer.MAX_VALUE;
      int max = Integer.MIN_VALUE;
      for (int i = 0; i < n; i++) {
        int val = sc.nextInt();
        if (val > max) {
          max = val;
          minIndexOfMax = i;
        }
        if (val <= min) {
          min = val;
          maxIndexOfMin = i;
        }
      }
      int ans = minIndexOfMax + n - maxIndexOfMin - 2;
      if (maxIndexOfMin > minIndexOfMax)
        ans++;
      System.out.println(ans);

    }
  }
}
