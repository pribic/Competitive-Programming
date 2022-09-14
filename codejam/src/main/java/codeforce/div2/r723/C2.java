package codeforce.div2.r723;


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
import java.util.StringJoiner;
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
 * @see <a href="https://codeforces.com/contest/1526/problem/C2" target="_top">https://codeforces.com/contest/1526/problem/C2</a>
 * @since 04/06/21 2:41 PM
 */
public class C2 {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = 1;//sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int[] portion = new int[n];
        for (int i = 0; i < n; i++) {
          portion[i] = sc.nextInt();
        }
        long curHealth = 0;
        PriorityQueue<Integer> taken = new PriorityQueue<>();
        for (int p : portion) {
          if (curHealth + p >= 0) {
            taken.add(p);
            curHealth += p;
          } else { //all the negative portions
            //so it means i cannot take portion p , i would remove the largest portion i have taken and take p instead
            if (!taken.isEmpty() && taken.peek() < p) {
              int negP = taken.remove();
              taken.add(p);
              curHealth += p - negP;
            }
          }
        }
        System.out.println(taken.size());
      }
    }
  }

  static class MultiSet<T> extends TreeSet<T> {
    Map<T, Integer> frequency;

    public MultiSet() {
      this.frequency = new HashMap<>();
    }

    @Override
    public boolean add(T t) {
      frequency.put(t, frequency.getOrDefault(t, 0) + 1);
      return super.add(t);
    }


    @Override
    public boolean remove(Object o) {
      if (!frequency.containsKey(o))
        return false;
      frequency.put((T) o, frequency.getOrDefault(o, 1) - 1);
      if (frequency.get(o) == 0) {
        frequency.remove(o);
        super.remove(o);
      }
      return true;
    }

    @Override
    public int size() {
      int cnt = 0;
      for (T key : frequency.keySet())
        cnt += frequency.get(key);
      return cnt;
    }

    @Override
    public String toString() {
      return new StringJoiner(", ", "[", "]")
        .add("frequency=" + frequency)
        .add(super.toString())
        .toString();
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