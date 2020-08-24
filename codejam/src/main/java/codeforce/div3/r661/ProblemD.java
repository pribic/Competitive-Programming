package codeforce.div3.r661;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

/**
 * @author pdoshi
 */
public class ProblemD {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int t = sc.nextInt();
      for (int tt = 0; tt < t; tt++) {
        int nn = sc.nextInt();
        String str = sc.next();
        int[] ans = new int[str.length()];
        int[] groups = new int[str.length()];
        Arrays.fill(groups, -1);

        int index = 0;
        int maxK = -1;

        Queue<Integer> lastZero = new LinkedList<>();
        Queue<Integer> lastOne = new LinkedList<>();

        Map<Integer, Queue<Integer>> map = new HashMap<>();
        map.put(0, lastZero);
        map.put(1, lastOne);
        int group = 0;
        for (char c : str.toCharArray()) {
          Queue<Integer> crossQueue =  map.get(1 - (c - '0'));
          Queue<Integer> straightQueue = map.get(c - '0');
          if (crossQueue.isEmpty()) {
            group++;
            straightQueue.add(group);
            ans[index] = group;
          } else {
            ans[index] = crossQueue.remove();
            straightQueue.add(ans[index]);
          }
          index++;
        }
        System.out.println(group);
        for (int ii : ans) {
          System.out.print(ii + " ");
        }
        System.out.println();
      }
    }
  }
}

/**
 * 0011
 * <p>
 * 0 -> 1 ->
 * 0 -> 1 ->
 * <p>
 * 111111
 * <p>
 * 1 ->
 * 1 ->
 * 1 ->
 * 1 ->
 * 1 ->
 * <p>
 * 10101
 * <p>
 * 1 -> 0 > 1 > 0 > 1
 * <p>
 * 01010000
 * <p>
 * 0 > 1 >
 * <p>
 * 1 > 0 > 1 >
 * <p>
 * 0 > 1
 * <p>
 * 0 > 0
 * <p>
 * 1001110011
 * <p>
 * 1 > 0 > 1 > 0 > 1
 * 0 > 1 > 0 > 1
 * 1
 **/