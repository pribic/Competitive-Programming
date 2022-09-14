package codeforce.div2.r688;

import java.util.Arrays;
import java.util.Scanner;

public class C {
  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int t = sc.nextInt();
      while (t-- > 0) {
        int n = sc.nextInt();
        int[][] grid = new int[n][n];
        Pair[] rows = new Pair[10];
        Pair[] cols = new Pair[10];
        Arrays.fill(rows, new Pair());
        Arrays.fill(cols, new Pair());
        for (int i = 0; i < n; i++) {
          String input = sc.next();
          for (int j = 0; j < n; j++) {
            int d = input.charAt(j) - '0';
            grid[i][j] = d;
            //update this row matrix
            rows[d].left = Math.min(rows[d].left, j);
            rows[d].right = Math.max(rows[d].right, j);

            cols[d].left = Math.min(cols[d].left, i);
            cols[d].right = Math.max(cols[d].right, i);
          }
        }

        //   print(rows);
        //   print(cols);
        long ans[] = new long[10];
        for (int i = 0; i < n; i++) {
          for (int j = 0; j < n; j++) {
            int d = grid[i][j];
            //find right most digit on this line and left most digit in this line;
            long base = Math.max(Math.abs(rows[d].left - j), Math.abs(rows[d].right - j));
            long height = Math.max(i, n - i - 1);
            ans[d] = Math.max(ans[d], base * height);

            //find another point in same col
            base = Math.max(Math.abs(cols[d].left - i), Math.abs(cols[d].right - i));
            height = Math.max(j, n - j - 1);
            ans[d] = Math.max(ans[d], base * height);
          }
        }
        for (long x : ans) {
          System.out.print(x + " ");
        }
        System.out.println();
      }
    }
  }

  private static void print(Pair[][] rows) {
    for (int i = 0; i < rows.length; i++) {
      for (int j = 0; j < rows[i].length; j++) {
        System.out.print(rows[i][j] + "::");
      }
      System.out.println();
    }
  }

  private static void fill(Pair[][] rows) {
    for (int i = 0; i < rows.length; i++) {
      for (int j = 0; j < rows[i].length; j++) {
        rows[i][j] = new Pair();
      }
    }
  }

  static class Pair {
    int left = Integer.MAX_VALUE;
    int right = Integer.MIN_VALUE;

    public Pair() {
    }

    public Pair(int left, int right) {
      this.left = left;
      this.right = right;
    }

    @Override
    public String toString() {
      return "left=" + left + ", right=" + right;
    }
  }
}
