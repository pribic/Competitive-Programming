package codeforce.practice;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.PriorityQueue;
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
 * @see <a href="https://codeforces.com/group/gwssXKhiVi/contest/365850/problem/C" target="_top">https://codeforces.com/group/gwssXKhiVi/contest/365850/problem/C</a>
 * @since 24/01/22 10:36 PM
 */
public class contest8_40_C {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = 1;//sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int[] arr = new int[n];
        Map<Integer, Integer> freq = new HashMap<>();
        for (int i = 0; i < n; i++) {
          arr[i] = sc.nextInt();
          freq.put(arr[i], freq.getOrDefault(arr[i], 0) + 1);
        }
        Set<Integer> unique = new HashSet<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int key : freq.keySet())
          if (freq.get(key) == 1)
            unique.add(key);
          else
            pq.add(key);
        while (!pq.isEmpty()) {
          Integer oldnum = pq.remove();
          Integer newnum = 2 * oldnum;
          freq.put(oldnum, freq.get(oldnum) - 2);
          if (freq.get(oldnum) == 0)
            freq.remove(oldnum);
          else if (freq.get(oldnum) == 1)
            unique.add(oldnum);
          else
            pq.add(oldnum);
          freq.put(newnum, freq.getOrDefault(newnum, 0) + 1);
          if (freq.get(newnum) == 1)
            unique.add(newnum);
          else if (freq.get(newnum) == 2) // this was unique, move it back to pq {
          {
            unique.remove(newnum);
            pq.add(newnum);
          }
        }
        System.out.println(unique.size());
        for (int num : unique)
          System.out.print(num + " ");
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