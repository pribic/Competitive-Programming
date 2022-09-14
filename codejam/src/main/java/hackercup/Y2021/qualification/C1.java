package hackercup.Y2021.qualification;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import static java.util.stream.Collectors.joining;


/**
 * @author pribic (Priyank Doshi)
 * @see <a href="https://www.facebook.com/codingcompetitions/hacker-cup/2021/qualification-round/problems/C1" target="_top">https://www.facebook.com/codingcompetitions/hacker-cup/2021/qualification-round/problems/C1</a>
 * @since 31/08/21 2:22 PM
 */
public class C1 {
  static FastScanner sc = new FastScanner(new File("/Users/pdoshi/Downloads/gold_mine_chapter_1_input.txt"));

  public static void main(String[] args) throws IOException {
    try (PrintWriter out = new PrintWriter(System.out)) {
      File f = new File("/Users/pdoshi/hackercup/q2021/problemC1" + System.currentTimeMillis() + ".txt");
      f.createNewFile();
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        FileWriter fw = new FileWriter(f, true);
        fw.append("Case #" + tt + ": ");
        int n = sc.nextInt();
        long[] gold = new long[n];
        for (int i = 0; i < n; i++) {
          gold[i] = sc.nextLong();
        }
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < n - 1; i++) {
          int u = sc.nextInt() - 1;
          int v = sc.nextInt() - 1;
          graph.putIfAbsent(u, new ArrayList<>());
          graph.putIfAbsent(v, new ArrayList<>());
          graph.get(u).add(v);
          graph.get(v).add(u);
        }
        List<Long> pathgold = new ArrayList<>();
        if(graph.get(0) == null) {
          print(gold[0], fw);
          fw.append("\n");
          fw.close();
          continue;
        }
        for (int v : graph.get(0)) {
          pathgold.add(dfs(graph, v, 0, gold));
        }
        Collections.sort(pathgold);
        print(pathgold.size() == 1 ? pathgold.get(0) + gold[0] : pathgold.get(pathgold.size() - 1) + pathgold.get(pathgold.size() - 2) + gold[0], fw);
        fw.append("\n");
        fw.close();
      }
    }
  }

  private static long dfs(Map<Integer, List<Integer>> graph, int v, int parent, long[] gold) {
    long maxgold = 0;
    for (Integer child : graph.get(v)) {
      if (child != parent) {
        maxgold = Math.max(maxgold, dfs(graph, child, v, gold));
      }
    }
    return maxgold + gold[v];
  }

  private static void print(Object o, FileWriter fw) throws IOException {
    System.out.println(o);
    fw.write(o.toString());
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