package kickstart.Y2020.round1F.MetalHarvest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Solution {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        System.out.print("Case #" + tt + ": ");
        int n = sc.nextInt();
        int k = sc.nextInt();
        List<Pair> intervals = new ArrayList<>();
        for (int i = 0; i < n; i++) {
          intervals.add(new Pair(sc.nextInt(), sc.nextInt()));
        }
        Collections.sort(intervals);
        int ans = 0;
        Pair active = new Pair(intervals.get(0).s, intervals.get(0).s + k);
        Pair current = intervals.get(0);
        for (int i = 0; i < n; i++) {
          Pair intersecting = new Pair(Math.max(current.s, active.s), Math.min(current.e, active.e));
          if(intersecting.e > intersecting.s) {
            //intersecting = true
            current.s = intersecting.s;
            int curLen = current.e -current.s;
            ans += (curLen + k - 1) / k;
          } else {
            //non intersecting.
            //start a new interval.
            active.s = current.s;
            active.e += active.s + k;
            int curLen = current.e - current.s;
            ans += (curLen + k - 1) / k;
          }
          active.s = current.e;
        }
        System.out.println(ans);
      }
    }
  }

  static class Pair implements Comparable<Pair> {
    int s, e;

    public Pair(int s, int e) {
      this.s = s;
      this.e = e;
    }

    @Override
    public int compareTo(Pair o) {
      return this.s - o.s;
    }

    @Override
    public String toString() {
      return s + " " + e;
    }
  }
}
