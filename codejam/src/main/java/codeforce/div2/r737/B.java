package codeforce.div2.r737;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Collections;
import java.util.PriorityQueue;
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
import static java.util.stream.Collectors.joining;


/**
 * @author pribic (Priyank Doshi)
 * @see <a href="https://codeforces.com/contest/1557/problem/B" target="_top">https://codeforces.com/contest/1557/problem/B</a>
 * @since 09/08/21 8:19 PM
 */
public class B {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    TreeSet<Integer> set = new TreeSet<>();
    set.add(1);
    set.add(5);
    set.add(10);
    set.add(15);
    out.println(set.ceiling(15));
    out.println(set.floor(15));
    out.println(set.lower(15));
    out.println(set.higher(15));
    
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] arr = new int[n + 1];
        for (int i = 0; i < n; i++) {
          arr[i] = sc.nextInt();
        }
        arr[n] = -(int) 1e9 - 5;
        int mincut = 0;
        List<List<Integer>> list = new ArrayList<>();
        List<Integer> first = new ArrayList<>();
        first.add(arr[0]);
        list.add(first);
        for (int i = 1; i <= n; i++) {
          if (arr[i - 1] > arr[i]) {
            mincut++;
            first = new ArrayList<>();
            first.add(arr[i]);
            list.add(first);
          } else {
            list.get(list.size() - 1).add(arr[i]);
          }
        }
        list.remove(list.size() - 1);
        //System.out.println(mincut);
        //System.out.println(list);
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        for (int i = 0; i < list.size(); i++) {
          pq.add(new Pair(list.get(i).get(0), i, 0));
        }
        List<Integer> order = new ArrayList<>();
        while (!pq.isEmpty()) {
          Pair pair = pq.remove();
          order.add(pair.idx);
          List<Integer> list1 = list.get(pair.idx);
          if (pair.id < list1.size() - 1) {
            pq.add(new Pair(list1.get(pair.id + 1), pair.idx, pair.id + 1));
          }
        }
        List<Integer> order1 = new ArrayList<>();
        order1.add(order.get(0));
        for(int i = 1; i < order.size(); i++) {
          if(!order.get(i - 1).equals(order.get(i)))
            order1.add(order.get(i));
        }
        //System.out.println("order = " + order);
        //System.out.println("order1 = " + order1);
        Set<Integer> unique = new HashSet<>(order1);
        //System.out.println("order1.size() = " + order1.size());
        //System.out.println("unique.size() = " + unique.size());
        //System.out.println("flattern = " + flattern);
        System.out.println(k >= mincut + order1.size() - unique.size()   ? "Yes" : "No");
      }
    }
  }

  static class Pair implements Comparable<Pair> {
    int val, idx, id;

    public Pair(int val, int idx, int id) {
      this.val = val;
      this.idx = idx;
      this.id = id;
    }

    @Override
    public int compareTo(Pair o) {
      return val - o.val;
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


//[[], [, ], [, ], [, ], [, ], []]
//-20 -10 -5 -4 -4 0 3 4 5 10