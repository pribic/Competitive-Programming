package kickstart.Y2020.round1C.Problem2;

import java.util.ArrayList;
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
      int R = sc.nextInt();
      int C = sc.nextInt();
      char[][] wall = new char[R][C];
      Set<Integer> inputs = new HashSet<>();
      String ans = "";
      for (int i = 0; i < R; i++) {
        String s = sc.next();
        wall[i] = s.toCharArray();
        for(char c : s.toCharArray()) {
          inputs.add(c - 'A');
        }
      }
      int[][] dependencyGraph = new int[26][26];
      for (int j = 0; j < C; j++) {
        for (int i = 0; i < R - 1; i++) {
          if (wall[i][j] != wall[i + 1][j]) {
            char independent = wall[i + 1][j];
            char dependent = wall[i][j];
            dependencyGraph[independent - 'A'][dependent - 'A'] = 1;
          }
        }
      }
      Set<Integer> S = beginners(dependencyGraph, inputs);
      Set<Integer> L = new LinkedHashSet<>();
      while (!S.isEmpty()) {
        Integer node = S.iterator().next();
        S.remove(node);
        L.add(node);
        Set<Integer> edgeFromNode = findEdgeFromNode(dependencyGraph, node);
        for(Integer edge : edgeFromNode) {
          dependencyGraph[node][edge] = 0;
          if(incomingEdges(dependencyGraph, edge) == 0) {
            S.add(edge);
          }
        }
      }
      if(graphHasEdges(dependencyGraph)) {
        ans = "-1";
      } else {
        for(Integer integer : L) 
          ans = ans + (char)(integer + 'A');
      }
      System.out.println(String.format("Case #%d: %s", tt, ans));
    }

    sc.close();
  }

  private static boolean graphHasEdges(int[][] dependencyGraph) {
    for(int i = 0; i < dependencyGraph.length; i++) {
      for(int j = 0; j < dependencyGraph[i].length; j++)
        if(dependencyGraph[i][j] == 1)
          return true;
    }
    return false;
  }

  private static int incomingEdges(int[][] dependencyGraph, Integer edge) {
    Set<Integer> ans = new HashSet<>();
    for(int i = 0; i < 26; i++) {
      if(dependencyGraph[i][edge] == 1)
        ans.add(i);
    }
    return ans.size();
  }

  private static Set<Integer> findEdgeFromNode(int[][] dependencyGraph, Integer node) {
    Set<Integer> ans = new HashSet<>();
    for(int i = 0; i < 26; i++) {
      if(dependencyGraph[node][i] == 1)
        ans.add(i);
    }
    return ans;
  }

  private static Set<Integer> beginners(int[][] dependencyGraph, Set<Integer> inputs) {
    Set<Integer> ans = new LinkedHashSet<>();
    for (int j = 0; j < dependencyGraph[0].length; j++) {
      int sum = 0;
      for (int i = 0; i < dependencyGraph.length; i++) {
        if(isPartOfInput(inputs, j, i))   
          sum += dependencyGraph[i][j];
      }
      if(sum == 0) {
        if(inputs.contains(j))
          ans.add(j);
      }
    }
    return ans;
  }

  private static boolean isPartOfInput(Set<Integer> inputs, int j, int i) {
    return inputs.contains(i) && inputs.contains(j);
  }


}

