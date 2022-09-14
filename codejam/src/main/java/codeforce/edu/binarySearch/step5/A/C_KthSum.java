package codeforce.edu.binarySearch.step5.A;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class C_KthSum {
  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int n = sc.nextInt();
      int k = sc.nextInt();
      int[] a = new int[n];
      for (int i = 0; i < n; i++) {
        a[i] = sc.nextInt();
      }
      int[] b = new int[n];
      for (int i = 0; i < n; i++) {
        b[i] = sc.nextInt();
      }

      Arrays.sort(a);
      Arrays.sort(b);
      List<Integer> list = new ArrayList<>();
      for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
          list.add(a[i] + b[j]);
        }
      }
      Collections.sort(list);
      for (int i = 0; i < list.size(); i++) {
        //System.out.println(i + "=" + list.get(i));
      }
      int l = 0;
      int r = Integer.MAX_VALUE;

      while (r > l + 1) {
        int mid = l + (r - l) / 2;
      //  System.out.println(l + " " + mid + " " + r);
        if (f(mid, a, b, k)) {
          l = mid;
        } else {
          r = mid;
        }
      }
      System.out.println(l);
    }
  }

  private static boolean f(int mid, int[] a, int[] b, int k) {
    int cnt = 0;

    //find numbers < mid in a.
    //if a[i] < mid - true
    // else false
    for(int i = 0; i < a.length; i ++) {
      int l = -1;
      int r = b.length;


      while (r > l + 1) {
        int mid1 = l + (r - l) / 2;
        
        if (b[mid1] + a[i] < mid) {
          l = mid1;
        } else {
          r = mid1;
        }
      }

      cnt += l + 1;
    }
    //System.out.println("cnt and mid " + cnt + " " + mid);
    return cnt <= k;
  }
}

//N logN