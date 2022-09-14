package kickstart.Y2020.round1C.Problem2;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Solution {
  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);
    int T = sc.nextInt();
    for (int tt = 1; tt <= T; tt++) {
      System.out.printf("Case #%d: ", tt);
      int R = sc.nextInt();
      int C = sc.nextInt();
      int[][] wall = new int[R][C];
      // letters from A to Z
      int[] in = new int[26];
      int[] out = new int[26];
      Set<Integer>[] graph = new HashSet[26];

      int presentCharCnt = 0;
      for (int i = 0; i < R; i++) {
        char[] curRow = sc.next().toCharArray();
        for (int j = 0; j < C; j++) {
          int cur = curRow[j] - 'A';
          wall[i][j] = cur;
          if (graph[cur] == null) {
            presentCharCnt++;
            graph[cur] = new HashSet<>();
          }
        }
      }
      for (int i = 1; i < R; i++) {
        for (int j = 0; j < C; j++) {
          int u = wall[i][j];
          int v = wall[i - 1][j];
          if (u != v && graph[u].add(v)) {
            out[u]++;
            in[v]++;
          }
        }
      }
      ArrayDeque<Integer> nodes = new ArrayDeque<>();
      //start with nodes having in degree = 0
      for (int i = 0; i < in.length; i++) {
        if (in[i] == 0 && graph[i] != null)
          nodes.add(i);
      }
      StringBuilder dependency = new StringBuilder();
      presentCharCnt -= nodes.size();
      while (!nodes.isEmpty()) {
        int cur = nodes.pop();
        dependency.append((char) (cur + 'A'));
        // remove all the edges going out from cur.
        for (int v : graph[cur]) {
          //remove this edge, 
          out[cur]--;
          in[v]--;
          if (in[v] == 0) {
            nodes.add(v);
            presentCharCnt--;
          }
        }
      }
      System.out.println(presentCharCnt == 0 ? dependency.toString() : -1);
    }

    sc.close();
  }
/*
learnings:
if we use adjacency list, keep in mind to not add same edge twice, hence use set if needed
avoid self loops, 
sometimes for building graph, if we are not sure that same edge can come again or not, then its better to 
fill in and out degree later once edges are set up correctly

 */
  
  
  private static boolean graphHasEdges(int[][] dependencyGraph) {
    for (int i = 0; i < dependencyGraph.length; i++) {
      for (int j = 0; j < dependencyGraph[i].length; j++)
        if (dependencyGraph[i][j] == 1)
          return true;
    }
    return false;
  }

  private static int incomingEdges(int[][] dependencyGraph, Integer edge) {
    Set<Integer> ans = new HashSet<>();
    for (int i = 0; i < 26; i++) {
      if (dependencyGraph[i][edge] == 1)
        ans.add(i);
    }
    return ans.size();
  }

  private static Set<Integer> findEdgeFromNode(int[][] dependencyGraph, Integer node) {
    Set<Integer> ans = new HashSet<>();
    for (int i = 0; i < 26; i++) {
      if (dependencyGraph[node][i] == 1)
        ans.add(i);
    }
    return ans;
  }

  private static Set<Integer> beginners(int[][] dependencyGraph, Set<Integer> inputs) {
    Set<Integer> ans = new LinkedHashSet<>();
    for (int j = 0; j < dependencyGraph[0].length; j++) {
      int sum = 0;
      for (int i = 0; i < dependencyGraph.length; i++) {
        if (isPartOfInput(inputs, j, i))
          sum += dependencyGraph[i][j];
      }
      if (sum == 0) {
        if (inputs.contains(j))
          ans.add(j);
      }
    }
    return ans;
  }

  private static boolean isPartOfInput(Set<Integer> inputs, int j, int i) {
    return inputs.contains(i) && inputs.contains(j);
  }


}

