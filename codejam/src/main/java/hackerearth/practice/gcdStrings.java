package hackerearth.practice;

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
 * @see <a href="https://www.hackerearth.com/practice/basic-programming/recursion/recursion-and-backtracking/practice-problems/algorithm/gcd-strings/" target="_top">https://www.hackerearth.com/practice/basic-programming/recursion/recursion-and-backtracking/practice-problems/algorithm/gcd-strings/</a>
 * @since 04/07/21 4:13 PM
 */
public class gcdStrings {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int a = sc.nextInt();
        int b = sc.nextInt();
        System.out.println(solve(a, b));
      }
    }
  }

  static int mod = (int) 1e9 + 7;

  //adds times 0 at the end with mod
  private static int solve(int x, int y) {
    // out.println(x + " " + y);
    if (x % y == 0) {
      int ans = 1;
      return bitShift(ans, x - 1);
    }
    int ans = solve(y, x % y); // 10
    //out.println(Integer.toBinaryString(ans));
    int ans1 = 1;
    int idxY = Integer.toBinaryString(y).length() - 1;
    for (int i = 0; i < Integer.toBinaryString(x).length(); i++) {
      ans1 = ans1 << 1;
      ans1 = ans1 % mod;
      if (((ans >> idxY) & 1) == 1) {
        ans1 = ans1 + 1;
        ans1 = ans1 % mod;
      }
      idxY--;
      if (idxY == -1)
        idxY = Integer.toBinaryString(y).length() - 1;
    }
    return ans1 % mod;
  }

  private static int bitShift(int ans, int times) {
    //System.out.println("in " + ans + " " + times);
    ans %= mod;
    for (int i = 0; i < times; i++) {
      ans = ans << 1;
      ans %= mod;
    }
    //System.out.println("out " + ans);
    return ans;
  }

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