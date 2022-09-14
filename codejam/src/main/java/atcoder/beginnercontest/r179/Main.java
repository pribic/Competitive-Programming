package atcoder.beginnercontest.r179;

import java.util.*;
import java.io.*;

public class Main {
  public static void main(String args[]) {
    FastScanner in = new FastScanner();
    long n = in.nextLong();
    long ans = 0;
    for (int a = 1; a < n; a++) {
      long low = 0, high = n;
      while (low + 1 < high) {
        long mid = low + (high - low) / 2;
        if (a * mid < n) {
          low = mid;
        } else
          high = mid;
      }
      ans = ans + low;
    }
    System.out.println(ans);
  }

  ///////////////////////////
  static class FastScanner {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer("");

    String next() {
      while (!st.hasMoreTokens())
        try {
          st = new StringTokenizer(br.readLine());
        } catch (IOException e) {
          e.printStackTrace();
        }
      return st.nextToken();
    }

    int nextInt() {
      return Integer.parseInt(next());
    }

    int[] nextArray(int n) {
      int[] a = new int[n];
      for (int i = 0; i < n; i++)
        a[i] = nextInt();
      return a;
    }

    long nextLong() {
      return Long.parseLong(next());
    }
  }
}
