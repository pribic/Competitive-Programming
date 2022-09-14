package codeforce.div2.r703;

import java.util.Scanner;

/**
 * @author pribic (Priyank Doshi)
 * @since 18/02/21
 */
public class A {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
          int n = sc.nextInt();
        long[] stacks = new long[n];
        for (int i = 0; i < n; i++) {
          stacks[i] = sc.nextInt();
        }
        for(int i = 0; i < n - 1; i++) {
          long old = stacks[i];
          stacks[i] = Math.min(stacks[i], i);
          stacks[i + 1] += Math.max(0, old - i); 
        }
        boolean isStricklyIncreasing = true;
        for(int i = 0; i < n - 1; i++) {
          if(stacks[i] >= stacks[i + 1]) {
            isStricklyIncreasing = false;
            break;
          }
        }
        System.out.println(isStricklyIncreasing ? "YES" : "NO");
      }
    }
  }
}