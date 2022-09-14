package codeforce.vk;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
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
 * @see <a href="https://codeforces.com/contest/1530/problem/D" target="_top">https://codeforces.com/contest/1530/problem/D</a>
 * @since 17/07/21 8:47 PM
 */
public class D {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int[] wishes = new int[n + 1];
        int[] freq = new int[(int) 1e5 + 1];
        for (int i = 1; i <= n; i++) {
          wishes[i] = sc.nextInt();
          freq[wishes[i]]++;
        }
        int[] backup = wishes.clone();
        List<Integer> missingNumbers = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
          if (freq[i] == 0) missingNumbers.add(i);
        }
       /* boolean[] badPosition = new boolean[n + 1];
        for (int i = 1; i <= n; i++) {
          if (wishes[i] == i || freq[wishes[i]] > 1)
            badPosition[i] = true;
        }*/
        List<Integer> priorityMissingNumbersIdx = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        int idx = 0;
        for (int i = 1; i <= n; i++) {
          if (i == missingNumbers.get(idx) && (freq[wishes[i]] > 1 && i == wishes[i])) {
            priorityMissingNumbersIdx.add(i);
            set.add(i);
            idx++;
          }
        }
        // priority -> 1 3 7
        List<Integer> another = new ArrayList<>(); // 3 7 1
        for (int i = 1; i <= priorityMissingNumbersIdx.size(); i++) {
          another.add(priorityMissingNumbersIdx.get(i % priorityMissingNumbersIdx.size()));
        }
        if (priorityMissingNumbersIdx.size() > 1)
          for (int i = 0; i < priorityMissingNumbersIdx.size(); i++) {
            int cur = priorityMissingNumbersIdx.get(i);
            int nxt = another.get(i);
            freq[wishes[cur]]--;
            wishes[cur] = nxt;
            freq[wishes[cur]]++;
          }
        idx = 0;
        for (int i = 1; i <= n; i++) {
          if (freq[wishes[i]] > 1 || wishes[i] == i) {
            while (!set.contains(missingNumbers.get(idx))) idx++;
            freq[wishes[i]]--;
            wishes[i] = missingNumbers.get(idx);
            freq[wishes[i]]++;
            idx++;
          }
        }

        int diff = 0;
        for (int i = 0; i <= n; i++) {
          if (wishes[i] != backup[i]) diff++;
        }
        System.out.println(diff);
        for (int i = 1; i <= n; i++)
          System.out.print(wishes[i] + " ");
        System.out.println();
        
        
        
/*
7
6 4 6 2 4 5 6

 */
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