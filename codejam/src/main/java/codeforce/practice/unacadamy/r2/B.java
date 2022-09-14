package codeforce.practice.unacadamy.r2;

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
 * @see <a href="https://codeforces.com/gym/346557/problem/B" target="_top">https://codeforces.com/gym/346557/problem/B</a>
 * @since 28/09/21 11:30 AM
 */
public class B {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = 1;//sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
          arr[i] = sc.nextInt();
        }
        int[] diff = new int[n - 1];
        for (int i = 0; i < n - 1; i++) {
          if (arr[i + 1] > arr[i])
            diff[i] = 1;
        }
        int idx = 0;
        int first, second;
        while (idx < diff.length && diff[idx] == 1)
          idx++;
        first = idx;
        while (idx < diff.length && diff[idx] == 0)
          idx++;
        second = idx;
        while (idx < diff.length && diff[idx] == 1)
          idx++;
        if (idx == diff.length) {
          for (int l = first, r = second; l < r; l++, r--) {
            int t = arr[l];
            arr[l] = arr[r];
            arr[r] = t;
          }
          boolean sorted = true;
          for (int i = 0; i < n - 1; i++) {
            if(arr[i + 1] < arr[i])
              sorted = false;
          }
          if (sorted) {
            System.out.println("yes");
            first++;
            second++;
            System.out.println(first + " " + second);
          } else {
            System.out.println("no");
          }
        } else {
          System.out.println("no");
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