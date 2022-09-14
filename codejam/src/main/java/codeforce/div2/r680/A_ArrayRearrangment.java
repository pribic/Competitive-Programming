package codeforce.div2.r680;

import java.util.Arrays;
import java.util.Scanner;

public class A_ArrayRearrangment {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int t = sc.nextInt();
      while (t-- > 0) {
        int n = sc.nextInt();
        int x = sc.nextInt();
        int[] a = new int[n];
        int[] b = new int[n];
        for (int i = 0; i < n; i++) {
          a[i] = sc.nextInt();
        }
        for (int i = 0; i < n; i++) {
          b[i] = sc.nextInt();
        }

        Arrays.sort(a);
        Arrays.sort(b);
        boolean flag = false;
        for (int i = 0; i < n; i++) {
          if(a[i] + b[n - 1 - i] > x)
            flag = true;
        }

        System.out.println(flag ? "NO" : "YES");
      }
    }
  }
}
