package codeforce.div3.r797;

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
import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.joining;


/**
 * @author pribic (Priyank Doshi)
 * @see <a href="https://codeforces.com/contest/1690/problem/B" target="_top">https://codeforces.com/contest/1690/problem/B</a>
 * @since 07/06/22 8:20 PM
 */
public class B {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int[] arr = new int[n];
        int max = 0;
        for (int i = 0; i < n; i++) {
          arr[i] = sc.nextInt();
        }
        int[] brr = new int[n];
        for (int i = 0; i < n; i++) {
          brr[i] = sc.nextInt();
          if (brr[i] == 0)
            max = Math.max(max, arr[i]);
        }
        //remove max from all
        for (int i = 0; i < n; i++) {
          if (brr[i] == 0) {
            arr[i] = 0;
          } else {
            arr[i] -= max;
          }
        }
        //if any is small then false;
        boolean valid = true;
        for (int i = 0; i < n; i++)
          if (arr[i] < brr[i])
            valid = false;
        if (valid) { // all in a  >= b
          //check for 0 in b
          Set<Integer> diffs = new HashSet<>();

          // now all non zero in b left. find their diff, which has to be same
          for (int i = 0; i < n; i++) {
            if (brr[i] != 0)
              diffs.add(arr[i] - brr[i]);
          }
          if (diffs.size() > 1 || (diffs.size() == 1 && diffs.iterator().next() < 0))
            valid = false;
        }
        System.out.println(valid ? "YES" : "NO");
      }
    }

  }


  /*
  
  2 4 6 8 10
  
  0 0 0 0 0
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