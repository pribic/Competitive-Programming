package codeforce.div3.r702;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author pribic (Priyank Doshi)
 * @since 16/02/21
 */
public class E {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        Pair[] nums = new Pair[n];
        int[] origin = new int[n];
        for (int i = 0; i < n; i++) {
          int val = sc.nextInt();
          nums[i] = new Pair(i, val);
          origin[i] = val;
        }
        Arrays.sort(nums);
        int l = -2;
        int r = n + 1;
        while (r > l + 1) {
          int mid = l + (r - l) / 2;
          if (check(nums, mid)) r = mid - 1;
          else l = mid + 1;
        }
        Set<Integer> ans = new TreeSet<>();
        for (int i = 0; i < n; i++) {
          if (origin[i] >= nums[r+1].val) {
            ans.add(i + 1);
          }
        }
        System.out.println(ans.size());
        for (int x : ans) {
          System.out.print(x + " ");
        }
        System.out.println();
      }
    }
  }

  private static boolean check(Pair[] nums, int mid) {
    int val = nums[mid].val;
    //need to check whether val can win or not
    long runningSum = val;
    long lastI = 0;
    for (int i = 0; i < nums.length; i++) {
      if (mid != i && runningSum >= nums[i].val) {
        //roll up
        runningSum += nums[i].val;
      }
      lastI = i;
    }
    return lastI == nums.length - 1;
  }

  static class Pair implements Comparable<Pair> {
    int index, val;

    public Pair(int index, int val) {
      this.index = index;
      this.val = val;
    }

    @Override
    public int compareTo(Pair o) {
      return this.val - o.val;
    }

    @Override
    public String toString() {
      final StringBuilder sb = new StringBuilder("Pair{");
      sb.append("index=").append(index);
      sb.append(", val=").append(val);
      sb.append('}');
      return sb.toString();
    }
  }
}