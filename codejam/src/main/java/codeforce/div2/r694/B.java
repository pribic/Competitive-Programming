package codeforce.div2.r694;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * @author pribic (Priyank Doshi)
 * @since 05/01/21
 */
public class B {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int x = sc.nextInt();
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
          queue.add(sc.nextInt());
          queue.add(1);
        }
        /**
         * 8 
         * 12 -> 3
         * 18  20 | 6X3 
         */
        long sum = 0;
        boolean foundNonDivisible = false;
        while (!queue.isEmpty()) {
          int num = queue.remove();
          int cnt = queue.remove();
          sum += ((long) num * cnt);
          //System.out.print(num + " " + cnt + " || ");
          if (num % x != 0) {
            foundNonDivisible = true;
          }
          if (!foundNonDivisible) {
            queue.add(num / x);
            queue.add(x * cnt);
          }
        }
        System.out.println(sum);
      }
    }
  }
}