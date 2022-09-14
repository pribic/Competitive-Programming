package codeforce.edu.binarySearch.step5.A;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class B_MultiplicationTable {
  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int n = sc.nextInt();
      int index = 1;
      List<Integer> num = new ArrayList<>();
      for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= n; j++) {
          num.add(i * j);
        }
      }
      Collections.sort(num);
      int k = sc.nextInt();
      System.out.println(num.get(k - 1) + " " + num.get(k) + " " + num.get(k + 1));
      int l = 1;
      int r = Integer.MAX_VALUE;
      while (r > l + 1) {
        int mid = l + (r - l) / 2;
        //   System.out.println(l + " " + mid + " " + r);
        if (f(mid, n, k)) {
          l = mid;
        } else
          r = mid;
      }
      System.out.println(l);
    }
  }
  //2 4 6 8 10

  private static boolean f(int mid, int n, int k) {
    long cnt = 0;
    for (int i = 1; i <= n; i++) {
      int ri = n * i;
      if (mid >= ri) {
        //take all
        cnt += n;
      } else if (i <= mid) {
        cnt += (mid - 1) / i;
      }
    }
    //  System.out.println("-- " + cnt + " " + mid + " " + k);
    return cnt <= k;
  }
}

//N logN