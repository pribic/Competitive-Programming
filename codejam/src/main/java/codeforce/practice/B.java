package codeforce.practice;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * @author pribic (Priyank Doshi)
 * @since 13/04/21
 */
public class B {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = 1;//sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] cost = new int[n + 1];
        for (int i = 1; i <= n; i++) {
          cost[i] = sc.nextInt();
        }
        boolean[] visited = new boolean[n + 1];
        visited[0] = true;
        Map<Integer, List<Integer>> adjacencyList = new HashMap<>();
        while (m-- > 0) {
          int p1 = sc.nextInt();
          int p2 = sc.nextInt();
          adjacencyList.putIfAbsent(p1, new ArrayList<>());
          adjacencyList.putIfAbsent(p2, new ArrayList<>());
          adjacencyList.get(p1).add(p2);
          adjacencyList.get(p2).add(p1);
        }
        long ans = 0;
        for (int node = 1; node <= n; node++)
          if (!visited[node]) {
            ans += bfs(visited, adjacencyList, node, cost);
          }
        System.out.println(ans);
      }

    }
  }

  private static long bfs(boolean[] visited, Map<Integer, List<Integer>> adjacencyList, int node, int[] cost) {
    ArrayDeque<Integer> queue = new ArrayDeque<>();
    int minCost = Integer.MAX_VALUE;
    queue.addFirst(node);
    while (!queue.isEmpty()) {
      int nod = queue.removeFirst();
      minCost = Math.min(minCost, cost[nod]);
      if(adjacencyList.containsKey(nod)) {
        for (int neighbour : adjacencyList.get(nod)) {
          if (!visited[neighbour]) {
            queue.addLast(neighbour);
          }
        }
      }
      visited[nod] = true;
    }
    return minCost;
  }
}