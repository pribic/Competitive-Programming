package codeforce.div2.r767;

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
 * @see <a href="https://codeforces.com/contest/1629/problem/C" target="_top">https://codeforces.com/contest/1629/problem/C</a>
 * @since 22/01/22 8:35 PM
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
        Set<Integer> left = new HashSet<>();
        Map<Integer, List<Integer>> right = new HashMap<>();
        for (int i = 0; i < n; i++)
          add(right, arr[i], i);
        List<Integer> b = new ArrayList<>();
        int idx = 0;
        while (idx < n) {
          //we are at idx, lets find 0 1 2 etc
          boolean move = false;
          for (int target = 0; target < 100000; ) {
            //can we find target in right side
            if (right.containsKey(target)) {
              //consume it and return
              int nxt = remove(right, target);
              left.add(target);
              for (int i = idx; i < nxt; i++) {
                remove(right, arr[i]);
                left.add(arr[i]);
              }
              idx = nxt + 1;
              move = true;
              //find out next target
              for (int t1 = target + 1; t1 < 100000; t1++) {
                if (!left.contains(t1)) {
                  target = t1;
                  break;
                }
              }
            } else {
              b.add(target);
              break;
            }
          }
          if (!move)
            idx++;
        }
        System.out.println(b.size());
        for (int bb : b)
          System.out.print(bb + " ");
        System.out.println();
      }
    }
  }

  private static int remove(Map<Integer, List<Integer>> right, int target) {
    List<Integer> ll = right.get(target);
    if (ll.size() == 1)
      right.remove(target);
    int ret = ll.get(0);
    ll.remove(0);
    return ret;
  }

  private static void add(Map<Integer, List<Integer>> numberToIdx, int num, int idx) {
    numberToIdx.putIfAbsent(num, new ArrayList<>());
    numberToIdx.get(num).add(idx);
  }
  
  
/*
5 1 | 2 0



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