package codeforce.globalround.y2021.r17;

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
import static java.util.stream.Collectors.toList;


/**
 * @author pribic (Priyank Doshi)
 * @see <a href="https://codeforces.com/contest/1610/problem/B" target="_top">https://codeforces.com/contest/1610/problem/B</a>
 * @since 23/11/21 8:19 PM
 */
public class B {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int[] arr = new int[n];
        int[] freq = new int[n + 1];
        for (int i = 0; i < n; i++) {
          arr[i] = sc.nextInt();
          freq[arr[i]]++;
        }
        /*
        in even len palindrom, all frequency needs to be even
        in odd len palindrom, one odd freq and all other even
        
        in short, atmax one odd freq and all other even freq - necessary but not sufficient condition
        
        its always better to all occurence of chose one
        
        
         */

        boolean valid = true;
        for (int l = 0, r = n - 1; l < r; l++, r--) {
          if (arr[l] != arr[r]) {
            int ll = l;
            int rr = r;
            valid = false;
            if (palin(IntStream.of(arr).filter(num -> num != arr[ll]).boxed().collect(toList())))
              valid = true;
            else if (palin(IntStream.of(arr).filter(num -> num != arr[rr]).boxed().collect(toList())))
              valid = true;
            break;
          }
        }
        System.out.println(valid ? "YES" : "NO");
      }
    }
  }

  private static boolean palin(List<Integer> arr1) {
    for (int l = 0, r = arr1.size() - 1; l < r; l++, r--)
      if (!arr1.get(l).equals(arr1.get(r)))
        return false;
    return true;
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