package codeforce.edu.DSU.step1;

import java.util.Arrays;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class ProblemA {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int n = sc.nextInt();
      int m = sc.nextInt();

      DSU dsu = new DSU(n);
      sc.nextLine();
      for (int i = 0; i < m; i++) {
        String[] s = sc.nextLine().split(" ");
        int a, b;
        switch (s[0]) {
          case "join":
            a = parseInt(s[1]);
            b = parseInt(s[2]);
            dsu.union(a, b);
            break;
          case "add":
            a = parseInt(s[1]);
            b = parseInt(s[2]);
            dsu.add(a, b);
            break;
          case "get":
            a = parseInt(s[1]);
            System.out.println(dsu.exp(a));
            break;
        }
      }
    }
  }

  static class DSU {
    int[] parent;
    int[] size;
    int[] exp;

    DSU(int n) {
      parent = new int[n + 1];
      size = new int[n + 1];
      exp = new int[n + 1];
      for (int i = 0; i < parent.length; i++) {
        parent[i] = i;
        size[i] = 1;
        exp[i] = 0;
      }
    }

    void print() {
      System.out.println("Arrays.toString(parent) = " + Arrays.toString(parent));
      System.out.println("Arrays.toString(size) = " + Arrays.toString(size));
      System.out.println("Arrays.toString(exp) = " + Arrays.toString(exp));
    }
    
    int find(int a) {
      if (a == parent[a])
        return a;
      parent[a] = find(parent[a]);
      return parent[a];
    }

    int exp(int a) {
      return exp[find(a)];
    }

    void union(int a, int b) {
      int p1 = find(a);
      int p2 = find(b);
      if (p1 == p2)
        return;
      if (size[p1] > size[p2]) {
        parent[p2] = p1;
        size[p1] += size[p2];
        exp[p1] += exp[p2];
      } else {
        parent[p1] = p2;
        size[p2] += size[p1];
        exp[p2] += exp[p1];
      }
    }

    public void add(int a, int v) {
      int p = find(a);
      exp[p] += v;
    }
  }
}
