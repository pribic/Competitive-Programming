package kickstart.Y2022.round1F.WaterContainerSystem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.joining;


/**
 * @author pribic (Priyank Doshi)
 * @see <a href="https://codingcompetitions.withgoogle.com/kickstart/round/00000000008cb409/0000000000bef79e" target="_top">https://codingcompetitions.withgoogle.com/kickstart/round/00000000008cb409/0000000000bef79e</a>
 * @since 18/09/22 11:05 pm
 */
public class Solution {
  static FastScanner sc = new FastScanner(System.in);

  private static ArrayList<Integer>[] al;
  private static int[] levels;
  private static Map<Integer, List<Integer>> levelVsNodes;

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        System.out.print("Case #" + tt + ": ");
        int n = sc.nextInt();
        int q = sc.nextInt();
        al = new ArrayList[n];
        levels = new int[n];
        levelVsNodes = new HashMap<>();
        for (int i = 0; i < n; i++) {
          al[i] = new ArrayList<>();
        }
        for (int i = 0; i < n - 1; i++) {
          int u = sc.nextInt() - 1;
          int v = sc.nextInt() - 1;
          al[u].add(v);
          al[v].add(u);
        }
        dfsLevel(0, -1, 0);
        int[] levelsAddWater = new int[n];
        int qq = q; // total we added these much water
        while (q-- > 0) {
          int u = sc.nextInt() - 1;
          levelsAddWater[levels[u]]++;
        }
        int maxLevel = IntStream.of(levels).max().getAsInt();
        // now we know how many water is present at each level, we need to bring down water and then propagate remaining to next level
        int countFull = 0;
        for (int i = 0; i <= maxLevel; i++) {
          //how many nodes we have at this level
          int nodeCount = levelVsNodes.get(i).size();
          if (qq >= nodeCount) {
            //all nodes will be full at this layer
            countFull += nodeCount;
            qq -= nodeCount;
          } else {
            break;
          }
        }
        System.out.println(countFull);
      }
    }
  }

  private static void dfsLevel(int u, int p, int level) {
    levels[u] = level;
    levelVsNodes.putIfAbsent(level, new ArrayList<>());
    levelVsNodes.get(level).add(u);
    for (int v : al[u]) {
      if (v != p)
        dfsLevel(v, u, level + 1);
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