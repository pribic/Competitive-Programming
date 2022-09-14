package codeforce.educational.r129;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

import static java.lang.System.out;
import static java.util.stream.Collectors.joining;


/**
 * @author pribic (Priyank Doshi)
 * @see <a href="https://codeforces.com/contest/1681/problem/D" target="_top">https://codeforces.com/contest/1681/problem/D</a>
 * @since 23/05/22 8:33 PM
 */
public class D {
  static FastScanner sc = new FastScanner(System.in);

  static int MAXX = Integer.MAX_VALUE / 2;
  // static List<Long> nums = new ArrayList<>();
  // static Set<Long> nums1 = new HashSet<>();
  static Map<Long, Integer> dp;

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = 1;//sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        long n = sc.nextLong();
        long x = sc.nextLong();
        dp = new HashMap<>();
        int steps = solve(n, x);
        System.out.println(steps == MAXX ? -1 : steps);
        //System.out.println(nums.size() + " set size " + new HashSet<>(nums).size());
      }
    }
  }

  private static int solve(long n, long x) {
    //out.println("x = " + x);
    //nums.add(x);
    List<Integer> digits = getDigits(x);
    if (digits.size() == n)
      return 0;
    if (digits.size() > n)
      return MAXX;//this indicates an invalid path, so move back
    if (dp.containsKey(x))
      return dp.get(x);
    Set<Integer> dd = new HashSet<>(digits);
    int steps = MAXX;
    for (int d : dd) {
      //try with this
      if (d > 1 && (x * d > 0)) {
        int ss = solve(n, x * d);
        if (ss != MAXX) // hence a valid path this is
          steps = Math.min(steps, 1 + ss);
      }
    }
    dp.put(x, steps);
    return steps;
  }

  private static List<Integer> getDigits(long x) {
    List<Integer> ls = new ArrayList<>();
    while (x > 0) {
      ls.add((int) (x % 10));
      x /= 10;
    }
    return ls;
  }
  
  /*
  3 2
  
  x = 2 -> 4 -> 16 -> 96 ->
  
  42
  
   
  
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