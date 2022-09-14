package codeforce.educational.r126;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.StringJoiner;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

import static java.lang.System.in;
import static java.lang.System.out;
import static java.util.stream.Collectors.joining;


/**
 * @author pribic (Priyank Doshi)
 * @see <a href="https://codeforces.com/contest/1661/problem/C" target="_top">https://codeforces.com/contest/1661/problem/C</a>
 * @since 09/04/22 8:48 PM
 */
public class C {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
          arr[i] = sc.nextInt();
        }
        int max = Arrays.stream(arr).max().getAsInt();
        int steps = solve(arr.clone(), max);
        steps = Math.min(steps, solve(arr.clone(), max + 1));
        System.out.println(steps);
      }
    }
  }

  private static int solve(int[] arr, int max) {

    int l = 0;
    int r = 8;

    while (r > l + 1) {
      int mid = l + (r - l) / 2;
      boolean invalid = false;
      int ones = (mid + 1) / 2;
      int twos = mid / 2;
      int steps = 0;
      for (int a : arr) {
        if(a == max)
          continue;
        int diff = max - a;
        if (diff % 2 == 1) {
          if (ones == 0)
            invalid = true;
          ones--;
          diff--;
        }
        int twoNeeded = diff / 2;
        // use as many twos as possible 
        if (twos >= twoNeeded) {
          twos -= twoNeeded;
        } else {
          twoNeeded -= twos;
          //now use ones
          if (ones >= 2 * twoNeeded) {
            ones -= 2 * twoNeeded;
          } else {
            invalid = true;
          }
        }
      }
      if (invalid)
        l = mid;
      else
        r = mid;
    }
    return l;
  }
  /*
  
  4 4 4 4 4 4 5 5 5
  
   */

  static class FastScanner {
    BufferedReader br;
    StringTokenizer st;

    public FastScanner(File f) {
      try {
        br = new BufferedReader(new FileReader(f));
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      }
    }

    public FastScanner(InputStream f) {
      br = new BufferedReader(new InputStreamReader(f), 32768);
    }

    String next() {
      while (st == null || !st.hasMoreTokens()) {
        String s = null;
        try {
          s = br.readLine();
        } catch (IOException e) {
          e.printStackTrace();
        }
        if (s == null)
          return null;
        st = new StringTokenizer(s);
      }
      return st.nextToken();
    }

    boolean hasMoreTokens() {
      while (st == null || !st.hasMoreTokens()) {
        String s = null;
        try {
          s = br.readLine();
        } catch (IOException e) {
          e.printStackTrace();
        }
        if (s == null)
          return false;
        st = new StringTokenizer(s);
      }
      return true;
    }

    int nextInt() {
      return Integer.parseInt(next());
    }

    long nextLong() {
      return Long.parseLong(next());
    }

    double nextDouble() {
      return Double.parseDouble(next());
    }
  }
}