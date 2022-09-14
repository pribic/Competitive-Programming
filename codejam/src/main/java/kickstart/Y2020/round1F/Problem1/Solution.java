package kickstart.Y2020.round1F.Problem1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Solution {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        System.out.print("Case #" + tt + ":");
        int n = sc.nextInt();
        int x = sc.nextInt();
        List<Pair> arr = new ArrayList<>(n);
        for (int i = 1; i <= n; i++) {
          int val = sc.nextInt();
          int times = (val + x - 1) / x;
          arr.add(new Pair(i, times));
        }
        Collections.sort(arr);
        for (int i = 0; i < n; i++) {
          System.out.print(" " + arr.get(i).index);
        }
        System.out.println();
      }
    }
    
  }
  static class Pair implements  Comparable<Pair> {
    int index, val;

    public Pair(int index, int val) {
      this.index = index;
      this.val = val;
    }

    @Override
    public int compareTo(Pair o) {
      return this.val - o.val;
    }
  }

}
