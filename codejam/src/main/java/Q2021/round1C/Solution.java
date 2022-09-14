package Q2021.round1C;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Scanner;

/**
 * @author pribic (Priyank Doshi)
 * @since 01/05/21
 */
public class Solution {


  public static void main(String[] args) {
    /*int[] dp1 = new int[2000];
    boolean[] visited1 = new boolean[2000];
    Arrays.fill(dp1, 2001);
    System.out.println(solve(17, 7, 0, dp1, visited1));*/
    //loadTest();

    try (Scanner sc = new Scanner(System.in)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        System.out.print("Case #" + tt + ": ");
        int[] dp = new int[maxx];
        boolean[] visited = new boolean[maxx];
        int start = Integer.parseInt(sc.next(), 2);
        int end = Integer.parseInt(sc.next(), 2);
        int ans = solve1(start, end, 0, dp, visited);
        System.out.println(ans == -1 ? "IMPOSSIBLE" : ans);
      }
    }
  }

  /**
   * 10000
   *max 15
   * cnt = 44959
   * 
   * 100
   * max 19
   * cnt = 6906
   * 
   * 
   * 
   */
  static int maxx = 50000; 
  private static void loadTest() {
    long ss = System.currentTimeMillis();
    long max = -1;
    long cnt = 0;
    for (int i = 0; i <= 255; i++) {
      for (int j = 0; j <= 255; j++) {
        int[] dp = new int[maxx];
        boolean[] visited = new boolean[maxx];
        int start = i;
        int end = j;
        int ans = solve1(start, end, 0, dp, visited);
        max = Math.max(max, ans);
        if(ans != -1)
          cnt++;
        //System.out.println(start + " " + end + "=" + (ans == -1 ? "IMPOSSIBLE" : ans));
      }
    }
    System.out.println("max " + max);
    System.out.println("cnt = " + cnt);
    System.out.println("maxHead = " + maxHead);
    System.out.println("time " + (System.currentTimeMillis() - ss));
  }
  static int maxHead = -1;
  private static int solve1(int start, long end, int turn, int[] dp, boolean[] visited) {
    Queue<Integer> queue = new ArrayDeque<>();
    queue.add(start);
    queue.add(-1);
    while (queue.size() > 1) {
      int head = queue.poll();
      maxHead = Math.max(maxHead, head);
      if (head == -1) {
        turn = turn + 1;
        queue.add(-1);
        continue;
      }
      if (head == end) {
        return turn;
      }
      if (head < visited.length &&!visited[head]) {
        visited[head] = true;
        queue.add(head * 2);
        queue.add(not(head));
      }
    }
    return -1;
  }

  private static int solve(int start, long end, int turn, int[] dp, boolean[] visited) {
    if (visited[start])
      return -1;
    System.out.println("start " + start);
    if (start == end) {
      dp[start] = Math.min(dp[start], turn);
      return turn;
    }
    if (start >= 65)
      return -1;
    if (dp[start] == -1)
      return -1;
    if (dp[start] != 2001)
      return dp[start];
    int twice = -1;
    int noting = -1;
    visited[start] = true;
    if (!visited[start * 2])
      twice = solve(start * 2, end, turn + 1, dp, visited);
    if (!visited[not(start)])
      noting = solve(not(start), end, turn + 1, dp, visited);
    if (twice == -1 && noting == -1) {
      return -1;
    }
    if (twice == -1) {
      dp[start] = Math.min(dp[start], noting);
      return noting;
    }
    if (noting == -1) {
      dp[start] = Math.min(dp[start], twice);
      return twice;
    }
    return dp[start] = Math.min(twice, noting);
  }

  private static int not(int num) {
    return num == 0 ? 1 : 2 * Integer.highestOneBit(num) - 1 - num;
  }
}