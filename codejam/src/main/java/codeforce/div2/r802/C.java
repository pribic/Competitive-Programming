package codeforce.div2.r802;

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
 * @see <a href="https://codeforces.com/contest/1700/problem/C" target="_top">https://codeforces.com/contest/1700/problem/C</a>
 * @since 19/06/22 4:07 PM
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
        long[] preDp = new long[n]; // p[i] = number of operations needed to make this prefix equal to arr[i]
        long[] suffDp = new long[n];
        for (int i = 1; i < n; i++) {
          preDp[i] = preDp[i - 1] + Math.abs(arr[i] - arr[i - 1]);
        }
        for (int i = n - 2; i >= 0; i--) {
          suffDp[i] = suffDp[i + 1] + Math.abs(arr[i] - arr[i + 1]);
        }


        long finalAns = Long.MAX_VALUE;
        for (int time = 0; time < 2; time++) {
          long ans = 0;
          for (int i = 1; i < n; i++) {
            if (arr[i] > arr[i - 1]) {
              ans += 2L * Math.abs(arr[i] - arr[i - 1]);
            } else if (arr[i] < arr[i - 1]) {
              ans += Math.abs(arr[i] - arr[i - 1]);
            }
          }
          
          finalAns = Math.min(finalAns, ans);
          //reverse
          for (int l = 0, r = n - 1; l < r; l++, r--) {
            int t = arr[l];
            arr[l] = arr[r];
            arr[r] = t;
          }
        }
        System.out.println(finalAns + Math.abs(arr[n - 1]));
      }
    }
  }
  
  /*
    1 -2 3 -4 5
    
    -2 -2 3 -4 5
    -9 -9 -9 -9 0
    
    
    
    3 + 5 + 7 + 9
     
     
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