package codeforce.educational.r100;

import java.util.Scanner;

public class B {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int t = sc.nextInt();
      while (t-- > 0) {
        int n = sc.nextInt();
        int[] time = new int[n];
        int[] x = new int[n];
        for (int i = 0; i < n; i++) {
          time[i] = sc.nextInt();
          x[i] = sc.nextInt();
        }
        int curPosition = 0;
        int successfulCommand = 0;
        int extraFuel = 0;
        for (int i = 0; i < n - 1; i++) {
          //where is robot in time range ti and ti+1
          int timeDiff = time[i + 1] - time[i];
          if (Math.abs(curPosition - x[i]) <= timeDiff) {
            successfulCommand++;
            extraFuel = 0;
          }
          if (x[i] > curPosition) {
            curPosition += timeDiff;
          } else {
            curPosition -= timeDiff;
          }
          extraFuel -= timeDiff;
        }
        System.out.println(successfulCommand + 1);
      }
    }
  }
}

/**
 * 1 5
 * 3 0
 * 6 4
 * active -> 3
 * cur -> 0, 2
 */

