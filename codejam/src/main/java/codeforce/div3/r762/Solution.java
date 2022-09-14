package codeforce.div3.r762;

import java.util.TreeMap;

public class Solution {
  int max = 100000 + 1;

  public static void main(String[] args) {
    System.out.println("new Solution().getDistances(new int[]{2, 1, 3, 1, 2, 3, 3}) = " + new Solution().getDistances(new int[]{2, 1, 3, 1, 2, 3, 3}));
  }

  public long[] getDistances(int[] arr) {
    int n = arr.length;
    int[] fr = new int[max]; // frequency of this number till now
    long[] distr = new long[n]; // 
    int[] li = new int[max]; // last seen index of this number
    fr[arr[n - 1]]++;
    li[arr[n - 1]] = n - 1;
    for (int i = n - 2; i >= 0; i--) {
      int num = arr[i];
      if (fr[num] > 0)
        distr[i] = distr[li[num]] + fr[num] * (li[num] - i);
      fr[num]++;
      li[num] = i;
    }
    print("fr", fr);
    print("distr", distr);
    print("li", li);

    int[] fl = new int[max]; // frequency of this number till now
    long[] distl = new long[n]; // 
    int[] pi = new int[max]; // last seen index of this number
    fl[arr[0]]++;
    pi[arr[0]] = 0;
    TreeMap<Integer, Integer> map = new TreeMap<>();
    for (int i = 1; i < n; i++) {
      int num = arr[i];
      if (fl[num] > 0)
        distl[i] = distl[pi[num]] + fl[num] * (i - pi[num]);
      fl[num]++;
      pi[num] = i;
    }
    //print("fl", fl);
    print("distl", distl);
    print("pi", pi);
    long[] ans = new long[n];
    for (int i = 0; i < n; i++)
      ans[i] = distl[i] + distr[i];
    return ans;
  }

  void print(String id, int[] a) {
    System.out.print(id + " ");
    for (int aa : a)
      if (aa > 0)
        System.out.print(aa + " ");
    System.out.println();
  }

  void print(String id, long[] a) {
    System.out.print(id + " ");
    for (long aa : a)
      if (aa > 0)
        System.out.print(aa + " ");
    System.out.println();
  }
}