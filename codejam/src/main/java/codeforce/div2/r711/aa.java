package codeforce.div2.r711;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * @author pribic (Priyank Doshi)
 * @since 04/04/21
 */
public class aa {

  public static void main(String[] args) {
    int[] a = new int[20000];
    int[] b = new int[20000];
    Arrays.fill(a, 1);
    Arrays.fill(b, 100000);
   // System.out.println(new aa().minAbsoluteSumDiff(a, b));
   // System.out.println(new aa().minAbsoluteSumDiff(new int[] {1,7,5}, new int[] {2,3,5}));
   // System.out.println(new aa().minAbsoluteSumDiff(new int[] {2,4,6,8,10}, new int[]{2,4,6,8,10}));
    System.out.println(new aa().minAbsoluteSumDiff(new int[] {1,10,4,4,2,7}, new int[] {9,3,5,1,7,4}));
  }

  public int minAbsoluteSumDiff(int[] nums1, int[] nums2) {
    int n = nums1.length;
    int[] d = new int[n];
    for (int i = 0; i < n; i++)
      d[i] = nums1[i];
    Arrays.sort(d);

    int gainSoFar = Integer.MIN_VALUE;
    int gainSoFarI = -1;
    int minGain = Integer.MAX_VALUE;
    for (int i = 0; i < n; i++) {
      if (nums1[i] != nums2[i]) {
        //System.out.println("i=" + i);
        int tn = nums2[i];
        int small = findSmall(d, tn); //find largest num such that it is <= d
        int big = findBig(d, tn); //find smallest num such that it is >= d
        List<Integer> dd = new ArrayList<>();
        dd.add(Math.abs(subtractModulo(nums1[i], nums2[i])));

        if (small != -1)
          dd.add(Math.abs(subtractModulo(small, tn)));
        if (big != -1) {
          dd.add(Math.abs(subtractModulo(big, tn)));
        }

        Collections.sort(dd);
        int minDiff = dd.get(0); // this is the min abs value we can get at this num
        int oldDiff = Math.abs(subtractModulo(nums1[i], nums2[i])); // this is the current abs value we got from this number
        //we know minDiff <= oldDiff

        int gain = subtractModulo(oldDiff, minDiff); //this is how much we gain if we use this number for minimizing , we want max gain
        if (gain > gainSoFar) {
          gainSoFar = gain;
          gainSoFarI = i;
          minGain = minDiff;
        }
      }
    }
    int cd = 0;
    for (int i = 0; i < n; i++) {
      if (i == gainSoFarI) {
        cd = addModulo(cd, minGain);
      } else {
        cd = addModulo(cd, Math.abs(subtractModulo(nums1[i], nums2[i])));
      }
    }
    return cd;
  }

  private int subtractModulo(int a, int b) {
    int mod = 1000000000 + 7;
    return (a % mod - b % mod) % mod;
  }

  private int addModulo(int a, int b) {
    int mod = 1000000000 + 7;
    return ((a % mod) + (b % mod)) % mod;
  }

  private int findSmall(int[] d, int tn) {
    // 1 3 4 5  8 10
    // t t t t  f  f
    // 7
    if (d[0] > tn) {
      return -1;
    }
    int l = 0;
    int r = d.length;
    while (r > l + 1) {
      int mid = l + (r - l) / 2;
      if (d[mid] <= tn) {
        l = mid;
      } else {
        r = mid;
      }
    }
    return d[l];
  }

  private int findBig(int[] d, int tn) {
    // 1 3 4 5  8 10
    // f f f f  t  t
    // 7
    if (d[d.length - 1] < tn) {
      return -1;
    }
    int l = -1;
    int r = d.length - 1;
    while (r > l + 1) {
      int mid = l + (r - l) / 2;
      if (d[mid] >= tn) {
        r = mid;
      } else {
        l = mid;
      }
    }
    return d[r];
  }
}