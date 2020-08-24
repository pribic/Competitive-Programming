package algoridhm.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;
import java.util.function.Predicate;

public class DFS {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int n = sc.nextInt();
      int e = sc.nextInt();
      Map<Integer, ArrayList<Integer>> adjacencyList = new HashMap<>();
      for (int i = 0; i < e; i++) {
        int u = sc.nextInt();
        int v = sc.nextInt();
        if (adjacencyList.containsKey(u)) {
          List<Integer> localList = adjacencyList.get(u);
          localList.add(v);
        } else
          adjacencyList.put(u, new ArrayList<>(Arrays.asList(v)));
      }
      int s = sc.nextInt();
      Stack<Integer> stack = new Stack<>();
      stack.add(s);
      Set<Integer> visited = new HashSet<>();
      while (!stack.isEmpty()) {
        Integer node = stack.pop();
        System.out.println(node);
        visited.add(node);
        List<Integer> integers = adjacencyList.get(node);
        Predicate<Integer> contains = visited::contains;
        if (integers != null) {
          integers.stream().filter(contains.negate()).forEach(element -> stack.push(element));
        }
      }
    }
  }
}
