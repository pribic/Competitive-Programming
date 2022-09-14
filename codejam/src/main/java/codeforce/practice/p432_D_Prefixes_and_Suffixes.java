package codeforce.practice;

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
 * @see <a href="https://codeforces.com/problemset/problem/432/D" target="_top">https://codeforces.com/problemset/problem/432/D</a>
 * @since 07/12/21 9:01 PM
 */
public class p432_D_Prefixes_and_Suffixes {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = 1;//sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        String s = sc.next();
        int[] z = StringUtil.z_function(s);
        List<Integer> zVals = new ArrayList<>();
        List<Integer> preSuf = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
          zVals.add(z[i]);
          if (i + z[i] - 1 == s.length() - 1)
            preSuf.add(z[i]);
        }
        Collections.sort(zVals);
        Collections.sort(preSuf);
        StringBuilder sb = new StringBuilder();
        System.out.println(preSuf.size() + 1);
        for (int len : preSuf) {
          // find how many nums >= len
          if (zVals.get(zVals.size() - 1) < len)
            continue;
          int l = -1;
          int r = zVals.size() - 1;
          while (r > l + 1) {
            int mid = l + (r - l) / 2;
            if (zVals.get(mid) >= len)
              r = mid;
            else
              l = mid;
          }
          sb.append(len).append(" ").append(zVals.size() - r + 1).append("\n");
        }
        sb.append(s.length()).append(" 1").append("\n");
        System.out.println(sb);

      }
    }
  }

  static class StringUtil {
    static int[] prefix_function(String s) {
      return prefix_function(s, 0, s.length());
    }

    // s[st..end)
    static int[] prefix_function(String s, int st, int end) {
      //out.println("s = " + s);
      int n = end - st;
      int[] pi = new int[n];
      for (int i = 1; i < n; i++) {
        int j = pi[i - 1];
        while (j > 0 && s.charAt(st + i) != s.charAt(st + j))
          j = pi[j - 1];
        if (s.charAt(st + i) == s.charAt(st + j))
          j++;
        pi[i] = j;
      }
      return pi;
    }

    static int[] z_function(String s) {
      int n = s.length();
      int[] z = new int[n];
      for (int i = 1, l = 0, r = 0; i < n; i++) { //[l,r] represents substring which is prefix also
        if (r >= i)
          z[i] = Math.min(r - i + 1, z[i - l]);
        while (i + z[i] < s.length() && s.charAt(i + z[i]) == s.charAt(z[i]))
          z[i]++;
        if (i + z[i] - 1 > r) {
          l = i;
          r = i + z[i] - 1;
        }
      }
      return z;
    }
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