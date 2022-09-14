package kickstart.Y2021.round1E.PalindromicCrossWord;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;


/**
 * @author pribic (Priyank Doshi)
 * @see <a href="https://codingcompetitions.withgoogle.com/kickstart/round/000000000043585c/0000000000859dcd" target="_top">https://codingcompetitions.withgoogle.com/kickstart/round/000000000043585c/0000000000859dcd</a>
 * @since 22/08/21 10:26 AM
 */
public class Solution {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        System.out.print("Case #" + tt + ": ");
        int n = sc.nextInt();
        int m = sc.nextInt();
        char[][] grid = new char[n][m];
        for (int i = 0; i < n; i++) {
          grid[i] = sc.next().toCharArray();
        }
        DSU dsu = new DSU(n * m + 1);
        for (int i = 0; i < n; i++) {
          for (int j = 0; j < m; j++) {
            if (grid[i][j] != '#') {
              //we have a palindrome start
              int k = j + 1;
              while (k < m && grid[i][k] != '#')
                k++;
              //k points to the end of palindrome
              for (int l = j, r = k - 1; l < r; l++, r--)
                dsu.union(i * m + l, i * m + r);
              j = k; //j points to # so that we can begin next search after this cell
            }
          }
        }

        for (int j = 0; j < m; j++) {
          for (int i = 0; i < n; i++) {
            if (grid[i][j] != '#') {
              int k = i + 1;
              while (k < n && grid[k][j] != '#')
                k++;
              for (int l = i, r = k - 1; l < r; l++, r--)
                dsu.union(l * m + j, r * m + j);
              i = k;
            }
          }
        }
        char[] letter = new char[n * m];
        for (int i = 0; i < n; i++) {
          for (int j = 0; j < m; j++) {
            if (grid[i][j] != '#' && grid[i][j] != '.') {
              letter[dsu.findParent(i * m + j)] = grid[i][j];
            }
          }
        }

        int count = 0;
        StringBuilder finalGrid = new StringBuilder();
        for (int i = 0; i < grid.length; i++) {
          for (int j = 0; j < grid[0].length; j++) {
            if (grid[i][j] == '.' && letter[dsu.findParent(i * m + j)] >= 'A' && letter[dsu.findParent(i * m + j)] <= 'Z') {
              grid[i][j] = letter[dsu.findParent(i * m + j)];
              count++;
            }
            finalGrid.append(grid[i][j]);
          }
          finalGrid.append("\n");
        }
        System.out.println(count);
        System.out.println(finalGrid);
      }
    }
  }

  static class DSU {
    int[] parent;
    int[] size;

    DSU(int n) {
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

    void union(int a, int b) {
      int par1 = findParent(a);
      int par2 = findParent(b);
      if (par1 != par2) {
        if (getSize(a) >= getSize(b)) {
          updateInternals(par1, par2);
        } else {
          updateInternals(par2, par1);
        }
      }
    }

    private void updateInternals(int parent, int child) {
      this.parent[child] = parent;
      size[parent] += size[child];
    }

    int getSize(int a) {
      return size[findParent(a)];
    }
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