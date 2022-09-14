package hackercup.Y2021.round2;

import diff.A;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class ProblemA {
  static FastScanner sc = new FastScanner(new File("/Users/pdoshi/Downloads/ARunway_sample.txt"));

  public static void main(String[] args) throws IOException {
    File f = new File("/Users/pdoshi/hackercup/q2021/problemARunway_" + System.currentTimeMillis() + ".txt");
    //File f = new File("./problemA1" + System.currentTimeMillis() + ".txt");
    f.createNewFile();
    int T = sc.nextInt();
    for (int tt = 1; tt <= T; tt++) {
      FileWriter fw = new FileWriter(f, true);
      fw.append("Case #" + tt + ": ");
      int n = sc.nextInt();
      int m = sc.nextInt();
      int[] s = new int[m];
      Map<Integer, Integer> cur = new HashMap<>();
      for (int i = 0; i < m; i++) {
        s[i] = sc.nextInt();
        cur.put(s[i], cur.getOrDefault(s[i], 0) + 1);
      }

      int[][] p = new int[n][m];
      for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
          p[i][j] = sc.nextInt();
        }
      }
      long changes = 0;
      int firstTime = m;
      for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
          int candidate = p[i][j];
          cur.put(candidate, cur.getOrDefault(candidate, 0) - 1);
          if (cur.get(candidate) == -1) {
            if (firstTime > 0)
              firstTime--;
            else
              changes++;
            cur.remove(candidate);
          }
        }
        cur.clear();
        for (int j = 0; j < m; j++) {
          cur.put(p[i][j], cur.getOrDefault(p[i][j], 0) + 1);
        }
      }
      print(changes, fw);

      print("\n", fw);
      fw.close();
    }

  }

  public static void main1(String[] args) throws IOException {
    File f = new File("/Users/pdoshi/hackercup/q2021/problemARunway_" + System.currentTimeMillis() + ".txt");
    //File f = new File("./problemA1" + System.currentTimeMillis() + ".txt");
    f.createNewFile();
    int T = sc.nextInt();
    for (int tt = 1; tt <= T; tt++) {
      FileWriter fw = new FileWriter(f, true);
      fw.append("Case #" + tt + ": ");
      int n = sc.nextInt();
      int m = sc.nextInt();
      int[] s = new int[m];
      Map<Integer, ArrayList<Integer>> currentUnused = new HashMap<>();
      Map<Integer, ArrayList<Integer>> currentUsed = new HashMap<>();
      boolean[] used = new boolean[m];
      for (int i = 0; i < m; i++) {
        s[i] = sc.nextInt();
        currentUnused.putIfAbsent(s[i], new ArrayList<>());
        currentUnused.get(s[i]).add(i);
      }
      System.out.println("currentUnused = " + currentUnused);
      System.out.println("currentUsed = " + currentUsed);
      System.out.println("used = " + Arrays.toString(used));
      int[][] p = new int[n][m];
      for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
          p[i][j] = sc.nextInt();
        }
      }
      long changes = 0;
      Map<Integer, ArrayList<Integer>> nextUnused = new HashMap<>();
      Map<Integer, ArrayList<Integer>> nextUsed = new HashMap<>();

      for (int i = 0; i < n; i++) {
        //first we try to match current row nums with previous row nums, greedily, as much as possible
        boolean[] matched = new boolean[m];
        for (int j = 0; j < m; j++) {
          int candidate = p[i][j];
          if (currentUsed.containsKey(candidate)) {
            currentUsed.get(candidate).remove(currentUsed.get(candidate).size() - 1);
            if (currentUsed.get(candidate).size() == 0)
              currentUsed.remove(candidate);
            matched[j] = true;
          } else if (currentUnused.containsKey(candidate)) {
            currentUnused.get(candidate).remove(currentUnused.get(candidate).size() - 1);
            if (currentUnused.get(candidate).size() == 0)
              currentUnused.remove(candidate);
            matched[j] = true;
          }
        }
        List<Integer> ususedPos = new ArrayList<>();
        for (List<Integer> val : currentUnused.values())
          ususedPos.addAll(val);
        List<Integer> usedPos = new ArrayList<>();
        for (List<Integer> val : currentUsed.values())
          usedPos.addAll(val);
        //we tried all used / unused indexes, now we try remaining ones
        //now we consider unmatched
        for (int j = 0; j < m; j++) {
          if (!matched[j]) {
            if (!ususedPos.isEmpty()) {
              int idx = ususedPos.get(ususedPos.size() - 1);
              used[idx] = true;
              ususedPos.remove(ususedPos.size() - 1);
            } else {
              int idx = usedPos.get(usedPos.size() - 1);
              changes++;
            }
          }
        }

        currentUnused.clear();
        currentUsed.clear();
        for (int j = 0; j < m; j++) {
          if (used[j]) {
            currentUsed.putIfAbsent(p[i][j], new ArrayList<>());
            currentUsed.get(p[i][j]).add(j);
          } else {
            currentUnused.putIfAbsent(p[i][j], new ArrayList<>());
            currentUnused.get(p[i][j]).add(j);
          }
        }
        System.out.println("currentUnused = " + currentUnused);
        System.out.println("currentUsed = " + currentUsed);
        System.out.println("used = " + Arrays.toString(used));
        System.out.println("changes = " + changes);
      }
      print(changes, fw);

      print("\n", fw);
      fw.close();
    }

  }

  private static void print(Map<Integer, ArrayList<Integer>> currentUnused) {
    for (int key : currentUnused.keySet()) {
      System.out.println(key + "=" + currentUnused.get(key));
    }
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


