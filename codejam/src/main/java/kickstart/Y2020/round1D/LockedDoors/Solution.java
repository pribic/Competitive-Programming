package kickstart.Y2020.round1D.LockedDoors;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Solution {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int q = sc.nextInt();
        Holder[] arr = new Holder[n + 1];
        for (int i = 1; i < n; i++) {
          arr[i] = new Holder(sc.nextInt(), i);
        }
        arr[0] = new Holder(Integer.MAX_VALUE, 0);
        arr[n] = new Holder(Integer.MAX_VALUE, n);
        int[][] memo = new int[n][n];
        System.out.print("Case #" + tt + ":");
        Map<Integer, Holder> kToLeft = new HashMap<>();
        Map<Integer, Holder> kToRight = new HashMap<>();
        Map<Integer, Integer> kToAns = new HashMap<>();
        int maxK = Integer.MIN_VALUE;
        for (int i = 0; i < q; i++) {
          int currentPoint = sc.nextInt();
          int k = sc.nextInt();
          kToLeft.put(k, arr[currentPoint - 1]);
          kToRight.put(k, arr[currentPoint]);
          if (currentPoint == 1) {
            kToAns.put(k, k);
            print(k);
          } else if (currentPoint == n) {
            kToAns.put(k, n - k + 1);
            print(n - k + 1);
          } else if (k == 1) {
            kToAns.put(k, currentPoint);
            print(currentPoint);
          } else if (k <= maxK)
            print(kToAns.get(k));
          else {
            int ans = 0;
            int turn = 1;
            Holder left;
            Holder right;
            if (maxK != Integer.MIN_VALUE) {
              left = kToLeft.get(maxK);
              right = kToRight.get(maxK);
              k = k - maxK + 1;
              currentPoint = left.index + 1;
            } else {
              left = arr[currentPoint - 1];
              right = arr[currentPoint];
            }
            while (turn <= k - 1) {
              kToLeft.put(turn, left);
              kToRight.put(turn, right);
              kToAns.put(turn, currentPoint);

              if (left.val < right.val) {
                left = arr[left.index - 1];
                currentPoint = left.index + 1;
              } else {
                right = arr[right.index + 1];
                currentPoint = right.index;
              }
              turn++;

              kToLeft.put(turn, left);
              kToRight.put(turn, right);
              kToAns.put(turn, currentPoint);

            }
            print(currentPoint);
          }
          maxK = Math.max(maxK, k);
        }
        System.out.println();
      }
    }
  }

  private static void print(int k) {
    System.out.print(" " + k);
  }

  static class Holder {
    int val;
    int index;

    public Holder(int val, int index) {
      this.val = val;
      this.index = index;
    }

    @Override
    public String toString() {
      return val + " " + index;
    }
  }
}
