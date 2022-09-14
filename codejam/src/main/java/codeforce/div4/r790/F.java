package codeforce.div4.r790;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;


/**
 * @author pribic (Priyank Doshi)
 * @see <a href="https://codeforces.com/contests/1676/problem/F" target="_top">https://codeforces.com/contests/1676/problem/F</a>
 * @since 10/05/22 8:00 PM
 */
public class F {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] arr = new int[n];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
          arr[i] = sc.nextInt();
          map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
        }
        List<Integer> eligible = new ArrayList<>();
        for (int key : map.keySet())
          if (map.get(key) >= k)
            eligible.add(key);
        Collections.sort(eligible);
        if (eligible.isEmpty()) {
          System.out.println(-1);
        } else {
          //now I need to find max continuous nums streak
          int cnt = 1;
          int max = 1;
          int l = eligible.get(0);
          int r = eligible.get(0);
          int prev = eligible.get(0);
          for (int i = 1; i < eligible.size(); i++) {
            int cur = eligible.get(i);
            if (cur == prev + 1) {
              cnt++;
            } else {
              if (cnt > max) {
                max = cnt;
                r = prev;
                l = prev - cnt + 1;
              }
              cnt = 1;
            }
            prev = cur;
          }
          if (cnt > max) {
            max = cnt;
            r = prev;
            l = prev - cnt + 1;
          }
          System.out.println(l + " " + r);
        }
        
      }
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