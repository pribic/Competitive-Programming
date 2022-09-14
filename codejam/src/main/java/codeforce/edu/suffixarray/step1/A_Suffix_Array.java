package codeforce.edu.suffixarray.step1;

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
 * @see <a href="https://codeforces.com/edu/course/2/lesson/2/1/practice/contest/269100/problem/A" target="_top">https://codeforces.com/edu/course/2/lesson/2/1/practice/contest/269100/problem/A</a>
 * @since 12/12/21 3:06 PM
 */
public class A_Suffix_Array {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = 1;//sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        String s = sc.next();
        Integer[] suff = StringUtil.suffix_array((s + "$").toCharArray());
        System.out.println(Arrays.toString(suff));
      }
    }
  }

  static class StringUtil {
    static Integer[] suffix_array(char[] s) {
      int n = s.length;
      //suffix array index here shows the starting of suffix 
      Integer[] p = new Integer[n];
      //class array, one id for each, 1 based
      int[] c = new int[n];
      int[] f = new int[256];
      for (int ch : s)
        f[ch]++; // frequency
      for (int i = 1; i < f.length; i++)
        f[i] += f[i - 1]; //prefix sum of frequency
      for (int i = n - 1; i >= 0; i--)
        p[--f[s[i]]] = i;
      int classId = 1;
      c[0] = classId;
      for (int i = 1; i < n; i++) {
        if (s[p[i]] != s[p[i - 1]])
          classId++;
        c[p[i]] = classId;
      }
      print("p base ", p);
      print("c base", c);
      for (int k = 1; (1 << k) < n; k++) { // will run log(n) time
        out.println("k = " + k);
        int[][] pairs = new int[n][2];
        for (int i = 0; i < n; i++) {
          pairs[i] = new int[]{c[p[i] % n], (c[(p[i] + (1 << (k - 1))) % n])};
        }
        out.println("pairs before sort = " + Arrays.deepToString(pairs));
        Arrays.sort(p, (x, y) -> {
          int[] pair1 = pairs[x];
          int[] pair2 = pairs[y];
          if (pair1[0] == pair2[0])
            if (pair1[1] == pair2[1])
              return x - y;
            else
              return pair1[1] - pair2[1];
          return pair1[0] - pair2[0];
        });
        print("p after sort ", p);
        out.println("pairs after sort = " + Arrays.deepToString(pairs));
        classId = 1;
        c[0] = 1;
        for (int i = 1; i < n; i++) {
          if (pairs[i][0] != pairs[i - 1][0] || pairs[i - 1][1] != pairs[i - 1][1])
            classId++;
          c[i] = classId;
        }
        print("c after sort", c);
      }
      return p;
    }

    private static void print(String id, Integer[] p) {
      out.print(id + " == ");
      for (int ii : p)
        out.print(ii + " ");
      out.println();
    }

    private static void print(String id, int[] p) {
      out.print(id + " == ");
      for (int ii : p)
        out.print(ii + " ");
      out.println();
    }

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