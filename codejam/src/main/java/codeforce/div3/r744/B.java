package codeforce.div3.r744;

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
 * @see <a href="https://codeforces.com/contest/1579/problem/B" target="_top">https://codeforces.com/contest/1579/problem/B</a>
 * @since 28/09/21 8:13 PM
 */
public class B {
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
        int idx = 0;
        List<Integer> ans = new ArrayList<>();
        while (idx < n) {
          //find min element from idx to n - 1
          int min = arr[idx];
          int minI = idx;
          for (int i = idx + 1; i < n; i++) {
            if (arr[i] < min) {
              min = arr[i];
              minI = i;
            }
          }

          //now bring minI to idx
          if (minI != idx) {
            ans.add(idx + 1);
            ans.add(minI + 1);
            ans.add(minI - idx);

            //cyclic rotate
            List<Integer> sub = new ArrayList<>();
            for (int i = idx; i <= minI; i++) {
              sub.add(arr[i]);
            }
            arr[idx] = sub.get(sub.size() - 1);
            int ii = 0;
            for (int i = idx + 1; i <= minI; i++, ii++)
              arr[i] = sub.get(ii);
          }
          idx++;
        }
        System.out.println(ans.size()/3);
        for (int i = 0; i < ans.size(); i += 3) {
          System.out.println(ans.get(i) + " " + ans.get(i + 1) + " " + ans.get(i + 2));
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


//1 2 3 5 4 

