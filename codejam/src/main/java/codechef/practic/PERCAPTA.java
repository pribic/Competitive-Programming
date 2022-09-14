package codechef.practic;

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

import static java.lang.System.in;
import static java.lang.System.out;
import static java.util.stream.Collectors.joining;


/**
 * @author pribic (Priyank Doshi)
 * @see <a href="https://www.codechef.com/problems/PERCAPTA" target="_top">https://www.codechef.com/problems/PERCAPTA</a>
 * @since 14/09/21 6:37 PM
 */
public class PERCAPTA {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] income = new int[n];
        for (int i = 0; i < n; i++) {
          income[i] = sc.nextInt();
        }
        int[] population = new int[n];
        for (int i = 0; i < n; i++) {
          population[i] = sc.nextInt();
        }
        DSU dsu = new DSU(n, income, population);
        while (m-- > 0) {
          int u = sc.nextInt();
          int v = sc.nextInt();
          dsu.union(u, v);
        }

        int incomeMax = dsu.income(1);
        int popMax = dsu.population(1);
        int idMax = 1;
        for (int i = 2; i <= n; i++) {
          int incom = dsu.income(i);
          int pop = dsu.population(i);
          if (incomeMax * pop < popMax * incom
            || (incomeMax * pop == popMax * incom && (dsu.size(idMax) < dsu.size(i)))) {
            incomeMax = incom;
            popMax = pop;
            idMax = i;
          }
        }
        System.out.println(dsu.size(idMax));
      }
    }
  }

  static class DSU {
    int[] parent;
    int[] income;
    int[] population;
    int[] size;

    DSU(int sz, int[] income, int[] population) {
      parent = new int[sz + 1];
      size = new int[sz + 1];
      this.income = new int[sz + 1];
      this.population = new int[sz + 1];
      for (int i = 1; i <= sz; i++) {
        parent[i] = i;
        size[i] = 1;
        this.income[i] = income[i - 1];
        this.population[i] = population[i - 1];
      }
    }

    int get(int x) {
      return parent[x] = (x == parent[x] ? x : get(parent[x]));
    }

    int income(int x) {
      return income[get(x)];
    }

    int population(int x) {
      return population[get(x)];
    }

    int size(int x) {
      return size[get(x)];
    }

    void union(int a, int b) {
      int pa = get(a);
      int pb = get(b);
      if (pa != pb) {
        int sza = size[pa];
        int szb = size[pb];
        if (sza < szb) {
          //we make sza as parent
          int t = pa;
          pa = pb;
          pb = t;
        }
        //we want pa as new parent
        parent[pb] = pa;
        size[pa] += size[pb];
        population[pa] += population[pb];
        income[pa] += income[pb];
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


/*

g, g + 1, g + 2 can be gcd


 */