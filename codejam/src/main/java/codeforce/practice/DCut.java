package codeforce.practice;

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
import java.util.StringJoiner;
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
import static java.util.Collections.emptySet;
import static java.util.stream.Collectors.joining;


/**
 * @author pribic (Priyank Doshi)
 * @see <a href="https://codeforces.com/contest/1516/problem/D" target="_top">https://codeforces.com/contest/1516/problem/D</a>
 * @since 22/05/21 6:18 PM
 */
public class DCut {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    Map<Integer, Integer> primeMapping = new HashMap<>();
    Map<Integer, Set<Integer>> primeFactors = buildPrimeFactors(primeMapping);

    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = 1;//sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int q = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
          arr[i] = sc.nextInt();
        }
        //for each index i, we want to know how much numbers we can take 
        //so we assume segment starts at i, grow it as much as possible
        //nxt[i] = index of starting of next block
        int[] nxt = new int[n];

        //for (int i = 2; i < 15; i++)
        //System.out.println(i + " " + primeFactors.get(i).toString());
        buildNext(primeFactors, n, arr, nxt, primeMapping);
        //System.out.println("Arrays.toString(nxt) = " + Arrays.toString(nxt));
        // i know from each i, index of next block for 1 jump
        // lets use this to build all 2^j length jumps
        SparseTable st = new SparseTable(nxt);
        //System.out.println(st);
        StringBuilder ansSb = new StringBuilder();
        while (q-- > 0) {
          int l = sc.nextInt() - 1;
          int r = sc.nextInt() - 1;
          int ans = 0;
          for (int i = 16; i >= 0; i--) {
            //can we make 2^i jump
            if (st.nthJump(l, 1 << i) <= r + 1) {
              l = st.nthJump(l, 1 << i);
              ans += 1 << i;
            }
          }
          if (l <= r) {
            ans++;
          }
          ansSb.append(ans).append("\n");
        }
        System.out.println(ansSb);
      }
    }
  }

  private static void buildNext(Map<Integer, Set<Integer>> primeFactors, int n, int[] arr, int[] nxt, Map<Integer, Integer> primeMapping) {
    int l = 0;
    int r = 1;
    boolean[] activePrimeFactors = new boolean[primeMapping.size()]; //new HashSet<>(primeFactors.get(arr[0]));
    for (int p : primeFactors.get(arr[0])) activePrimeFactors[primeMapping.get(p)] = true;
    while (l < n) {
      while (r < n && primeFactors.get(arr[r]).stream().noneMatch(p -> activePrimeFactors[primeMapping.get(p)])) {
        for (int p : primeFactors.get(arr[r])) activePrimeFactors[primeMapping.get(p)] = true;
        r++;
      } // r points to the element which we could not include.. beware that r can be n also meaning all the elements exhausted
      nxt[l] = r;
      //remove factors of l
      for (int p : primeFactors.get(arr[l])) activePrimeFactors[primeMapping.get(p)] = false;
      l++;
    }
  }

  private static Map<Integer, Set<Integer>> buildPrimeFactors(Map<Integer, Integer> map) {
    Map<Integer, Set<Integer>> primeFactors = new HashMap<>();
    primeFactors.put(1, emptySet());
    boolean[] primes = new boolean[100000 + 1];
    List<Integer> primeList = new ArrayList<>();
    primeList.add(1);
    Arrays.fill(primes, true);
    for (int factor = 2; factor <= 100000; factor++) {
      if (primes[factor]) {
        for (int j = factor; j <= 100000; j += factor) {
          primeFactors.putIfAbsent(j, new HashSet<>());
          primeFactors.get(j).add(factor);
          primes[j] = false;
        }
        primes[factor] = true;
        primeList.add(factor);
      }
    }
    int cnt = 0;
    for (int p : primeList) {
      map.put(p, cnt++);
    }
    return primeFactors;
  }

  static class SparseTable {
    int[][] jumps; // jumps[i][j] = index of next block after making 2^j jumps from i

    public SparseTable(int[] jump) {
      jumps = new int[jump.length + 1][17];
      for (int i = 0; i < jumps.length; i++)
        Arrays.fill(jumps[i], Integer.MAX_VALUE / 2);
      for (int i = 0; i < jump.length; i++)
        jumps[i][0] = jump[i];
      for (int j = 1; j < 17; j++) {
        for (int i = 0; i + (1 << j) <= jump.length; i++) {
          int halfWay = jumps[i][j - 1];
          if (halfWay < jump.length)
            jumps[i][j] = jumps[halfWay][j - 1];
        }
      }
    }

    int nthJump(int i, int n) {
      //we want to make n jumps from i.. n = 22, so we make i to 16 then 4 then 2
      for (int j = 16; j >= 0; j--) { //try max jump first
        if (((n >> j) & 1) == 1) {
          i = jumps[i][j];
        }
      }
      return i; //returns index of next block after making n jumps
    }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder();
      for (int[] row : jumps) {
        for (int cell : row)
          sb.append(cell).append(" ");
        sb.append("\n");
      }
      return sb.toString();
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
      br = new BufferedReader(new InputStreamReader(f));
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