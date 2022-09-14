package codeforce.edu.DSU.step1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * @author pribic (Priyank Doshi)
 * @since 16/04/21
 */
public class B {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = 1;//sc.nextInt();
      FastReader fr = new FastReader();
      for (int tt = 1; tt <= T; tt++) {
        int n = fr.nextInt();
        int q = fr.nextInt();
        UnionFind uf = new UnionFind(n);
        while (q-- > 0) {
          String op = fr.next();
          if (op.equals("get")) {
            int element = fr.nextInt() - 1;
            System.out.println(uf.minimum(element) + " " + uf.maximum(element) + " " + uf.size(element));
          } else {
            uf.union(fr.nextInt() - 1, fr.nextInt() - 1);
          }
        }
      }
    }
  }

  static class UnionFind {
    int[] parent;
    int[] size;
    int[] minimum;
    int[] maximum;

    UnionFind(int n) {
      parent = new int[n];
      size = new int[n];
      minimum = new int[n];
      maximum = new int[n];
      for (int i = 0; i < n; i++) {
        parent[i] = i;
        size[i] = 1;
        minimum[i] = i + 1;
        maximum[i] = i + 1;
      }
    }

    void union(int a, int b) {
      int parentA = get(a);
      int parentB = get(b);
      if (parentA != parentB) {
        if (size[parentA] <= size[parentB]) {
          parent[parentA] = parentB; //merging A into B_Inversions_2. B_Inversions_2 is new parent of group A and B_Inversions_2
          size[parentB] += size[parentA];
          minimum[parentB] = Math.min(minimum[parentA], minimum[parentB]);
          maximum[parentB] = Math.max(maximum[parentA], maximum[parentB]);
        } else {
          parent[parentB] = parentA; //merging B_Inversions_2 into A. A is new parent of group A and B_Inversions_2
          size[parentA] += size[parentB];
          minimum[parentA] = Math.min(minimum[parentA], minimum[parentB]);
          maximum[parentA] = Math.max(maximum[parentA], maximum[parentB]);
        }
      }
    }

    int get(int n) {
      return parent[n] = (n == parent[n] ? n : get(parent[n]));
    }

    int minimum(int n) {
      return minimum[get(n)];
    }

    int maximum(int n) {
      return maximum[get(n)];
    }

    int size(int n) {
      return size[get(n)];
    }

  }

  static class FastReader {
      BufferedReader br;
      StringTokenizer st;
  
      public FastReader() {
        br = new BufferedReader(new InputStreamReader(System.in));
      }
  
      String next() {
        while (st == null || !st.hasMoreElements()) {
          try {
            st = new StringTokenizer(br.readLine());
          } catch (IOException e) {
            e.printStackTrace();
          }
        }
        return st.nextToken();
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
  
      String nextLine() {
        String str = "";
        try {
          str = br.readLine();
        } catch (IOException e) {
          e.printStackTrace();
        }
        return str;
      }
    }
}