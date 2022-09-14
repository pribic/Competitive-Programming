package codeforce.div3.r725;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.TreeSet;
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
 * @see <a href="https://codeforces.com/contest/1538/problem/C" target="_top">https://codeforces.com/contest/1538/problem/C</a>
 * @since 10/06/21 8:31 PM
 */
public class C {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int l = sc.nextInt();
        int r = sc.nextInt();
        List<Integer> arr = new ArrayList<>();
        for (int i = 0; i < n; i++) {
          arr.add(sc.nextInt());
        }
        Collections.sort(arr);
        long cnt = 0;
        for (int i = 0; i < n - 1; i++) {
          int left = binary1(arr, i, l - arr.get(i)); // returns idx of number <= last 
          int right = binary2(arr, i, r - arr.get(i));
          //System.out.println(i + " " + left + " " + right);
          if (left != -1 && right != -1)
            cnt += right - left + 1;
        }
        System.out.println(cnt);
      }
    }
  }

  // return <= x
  private static int binary2(List<Integer> arr, int i, int x) {
    if(arr.get(i + 1) > x)
      return -1;
    int l = i + 1;
    int r = arr.size();
    while (r > l + 1) {
      int mid = l + (r - l) / 2;
      if (arr.get(mid) <= x)
        l = mid;
      else
        r = mid;
    }
    return l;
  }
 
  // 1 2 3 4 5 || 5-8
  // 1 2 3
  // return >= x
  private static int binary1(List<Integer> arr, int i, int x) {
    if (arr.get(arr.size() - 1) < x)
      return -1;
    int l = i;
    int r = arr.size() - 1;
    while (r > l + 1) {
      int mid = l + (r - l) / 2;
      if (arr.get(mid) >= x)
        r = mid;
      else
        l = mid;
    }
    return r;
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