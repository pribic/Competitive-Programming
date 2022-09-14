package codeforce.company.graknforces2020;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class A_CircleColoring {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int t = sc.nextInt();
      for (int i = 0; i < t; i++) {
        int n = sc.nextInt();
        int[] a = new int[n];
        for (int j = 0; j < n; j++) {
          a[j] = sc.nextInt();
        }
        int[] b = new int[n];
        for (int j = 0; j < n; j++) {
          b[j] = sc.nextInt();
        }
        int[] c = new int[n];
        for (int j = 0; j < n; j++) {
          c[j] = sc.nextInt();
        }
        int[] p = new int[n];
        p[0] = a[0];
        for (int j = 1; j < n; j++) {
          if (a[j] != p[j - 1])
            p[j] = a[j];
          else if (b[j] != p[j - 1])
            p[j] = b[j];
          else
            p[j] = c[j];
        }

        if (p[0] == p[n - 1]) {
          if (a[n - 1] != p[0] && a[n-1] != p[n-2])
            p[n - 1] = a[n - 1];
          else if (b[n - 1] != p[0] && b[n-1] != p[n-2])
            p[n - 1] = b[n - 1];
          else
            p[n - 1] = c[n - 1];
        }

        for (int j = 0; j < n; j++) {
          System.out.print(p[j] + " ");
        }

        System.out.println();
      }

    }
  }
}
