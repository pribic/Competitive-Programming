package codeforce.div2.r688;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class A {
  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int t = sc.nextInt();
      while (t-- > 0) {
        int n = sc.nextInt();
        int m = sc.nextInt();
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
          set.add(sc.nextInt());
        }
        int ans = 0;
        for (int i = 0; i < m; i++) {
          int val = sc.nextInt();
          if (set.contains(val))
            ans++;
        }
        System.out.println(ans);
      }
    }
  }
}
