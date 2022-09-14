package kickstart.Y2021.round1E.PalindromicCrossWord;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
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
import static java.util.stream.Collectors.joining;


/**
 * @author pribic (Priyank Doshi)
 * @see <a href="https://codingcompetitions.withgoogle.com/kickstart/round/000000000043585c/0000000000859dcd" target="_top">https://codingcompetitions.withgoogle.com/kickstart/round/000000000043585c/0000000000859dcd</a>
 * @since 22/08/21 10:26 AM
 */
public class SolutionInCompetetion {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        System.out.print("Case #" + tt + ": ");
        int n = sc.nextInt();
        int m = sc.nextInt();
        char[][] grid = new char[n + 2][m + 2];
        char[] row = new char[m];
        for (int i = 0; i < n + 2; i++) {
          if (i != 0 && i != n + 1)
            row = sc.next().toCharArray();
          for (int j = 0; j < m + 2; j++) {
            if (i == 0 || j == 0 || i == n + 1 || j == m + 1)
              grid[i][j] = '#';
            else
              grid[i][j] = row[j - 1];
          }
        }
        char[][] backup = copy(grid);
        Pair[][][] blocks = new Pair[n + 2][m + 2][4];
        //for(char[] rr : grid) System.out.println(Arrays.toString(rr));
        for (int i = 0; i < n + 2; i++) {
          for (int j = 0; j < m + 2; j++) {
            if (grid[i][j] == '#') {
              blocks[i][j][0] = new Pair(i, j);
              blocks[i][j][1] = new Pair(i, j);
              blocks[i][j][2] = new Pair(i, j);
              blocks[i][j][3] = new Pair(i, j);
            } else {
              blocks[i][j][0] = blocks[i - 1][j][0];
              blocks[i][j][3] = blocks[i][j - 1][3];
            }
          }
        }
        for (int i = n + 1; i >= 0; i--) {
          for (int j = m + 1; j >= 0; j--) {
            if (grid[i][j] != '#') {
              blocks[i][j][1] = blocks[i][j + 1][1];
              blocks[i][j][2] = blocks[i + 1][j][2];
            }
          }
        }

        Deque<Pair> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[n + 2][m + 2];
        for (int i = 0; i < grid.length; i++) {
          for (int j = 0; j < grid[i].length; j++) {
            if (grid[i][j] != '#' && grid[i][j] != '.') {
              q.offer(new Pair(i, j));
            }
          }
        }
        while (!q.isEmpty()) {
          int sz = q.size();
          for (int i = 0; i < sz; i++) {
            Pair p = q.removeFirst();
            if (visited[p.x][p.y])
              continue;
            visited[p.x][p.y] = true;
            Pair[] neighbors = blocks[p.x][p.y];
            Pair horizontalPartner = new Pair(p.x, neighbors[1].y - (p.y - neighbors[3].y));
            Pair verticalPartner = new Pair(neighbors[2].x - (p.x - neighbors[0].x), p.y);
            grid[horizontalPartner.x][horizontalPartner.y] = grid[p.x][p.y];
            grid[verticalPartner.x][verticalPartner.y] = grid[p.x][p.y];
            q.offer(horizontalPartner);
            q.offer(verticalPartner);
          }
        }

        StringBuilder finalGrid = new StringBuilder();
        for (int i = 1; i < n + 1; i++) {
          for (int j = 1; j < m + 1; j++)
            finalGrid.append(grid[i][j]);
          finalGrid.append("\n");
        }
        System.out.println(count(grid, backup));
        System.out.println(finalGrid);
      }
    }
  }
  
  static class DSU {
    int[] data;
    int[] parent;
    int[] size;

    DSU(int n) {
      data = new int[n];
      parent = new int[n];
      size = new int[n];
      for (int i = 0; i < n; i++) {
        parent[i] = i;
        size[i] = 1;
      }
    }

    int findParent(int x) {
      return parent[x] = x == parent[x] ? x : findParent(parent[x]);
    }

    boolean sameGroup(int a, int b) {
      return findParent(a) == findParent(b);
    }

    void merge(int a, int b) {
      int par1 = findParent(a);
      int par2 = findParent(b);
      if (par1 != par2) {
        int sz1 = getSize(a);
        int sz2 = getSize(b);
        if (sz1 >= sz2) {
          parent[par2] = par1;
          size[par1] += size[par2];
        } else {
          parent[par1] = par2;
          size[par2] += size[par1];
        }
      }
    }

    int getSize(int a) {
      return size[findParent(a)];
    }
  }

  private static char[][] copy(char[][] grid) {
    char[][] backup = new char[grid.length][grid[0].length];
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[i].length; j++)
        backup[i][j] = grid[i][j];
    }
    return backup;
  }

  private static int count(char[][] grid, char[][] backup) {
    int cnt = 0;
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[i].length; j++)
        if (backup[i][j] == '.' && grid[i][j] != '.')
          cnt++;
    }
    return cnt;
  }

  static class Pair {
    int x, y;

    public Pair(int x, int y) {
      this.x = x;
      this.y = y;
    }

    @Override
    public String toString() {
      return x + " " + y;
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