package codeforce.div2.r684;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class B {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int t = sc.nextInt();
      while (t-- > 0) {
        int n = sc.nextInt();
        int k = sc.nextInt();
        List<Integer> arr = new ArrayList<>(n);
        for (int i = 0; i < n * k; i++) {
          arr.add(sc.nextInt());
        }
        long ans = 0;
        Collections.sort(arr);
       // System.out.println("arr = " + arr);
        int index = (k-1) * n + (n-1)/2;
        int diff = n*k - index;
       // System.out.println("index = " + index);
       // System.out.println("diff = " + diff);
        for (int i = 0; i < k; i++, index -= diff) {
         // System.out.println("taking " + arr.get(index));
          ans += arr.get(index);
        }
        System.out.println(ans);
      }
    }
  }
}

//0 1 2 3 4 5 || 6 7 8 9 10 11 || 12









