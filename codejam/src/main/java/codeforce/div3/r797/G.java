package codeforce.div3.r797;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Iterator;
import java.util.NavigableMap;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.TreeMap;
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
 * @see <a href="https://codeforces.com/contest/1690/problem/G" target="_top">https://codeforces.com/contest/1690/problem/G</a>
 * @since 08/06/22 1:07 PM
 */
public class G {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      StringBuilder ans = new StringBuilder();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int q = sc.nextInt();
        int[] arr = new int[n];
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
          arr[i] = sc.nextInt();
          if (arr[i] < min) {
            map.put(i, arr[i]);
            min = arr[i];
          }
        }
        

        while (q-- > 0) {
          int k = sc.nextInt() - 1;
          int d = sc.nextInt();
          arr[k] -= d; // this is absolute value
          Map.Entry<Integer, Integer> floorEntity = map.floorEntry(k);
          int val = floorEntity.getValue(); // this is the current calculated at this position
          if (arr[k] >= val) {
            ans.append(map.size()).append(" ");
            continue;
          }
          Iterator<Integer> iter;
          if (!map.containsKey(k)) {
            iter = map.tailMap(floorEntity.getKey()).keySet().iterator();
            if (iter.hasNext())
              iter.next();
          } else {
            iter = map.tailMap(k).keySet().iterator();
          }

          while (iter.hasNext()) {
            int key1 = iter.next();
            int val2 = map.get(key1);
            if (val2 >= arr[k])
              iter.remove();
          }
          map.put(k, arr[k]);
          ans.append(map.size()).append(" ");
        }
        ans.append("\n");
      }
      System.out.println(ans);
    }
  }
  /*
3

4 2
6 2 3 7
3 2
4 7

tree map : sorted on index
1 : 6
2 : 2
3 : 1
4 : 0

5 4
5 9 3 2 4
2 4
5 2
1 5
3 2

tree map:

1 : 5
3 : 3
4 : 2


13 4
769 514 336 173 181 373 519 338 985 709 729 702 168
12 581
6 222
7 233
5 117

  
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