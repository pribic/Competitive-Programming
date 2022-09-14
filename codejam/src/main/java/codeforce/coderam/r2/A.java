package codeforce.coderam.r2;

import java.util.Scanner;

/**
 * @author pribic (Priyank Doshi)
 * @since 15/02/21
 */
public class A {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = 1;//sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        String str = sc.next();
        int[] freq = new int[130];
        for(char x : str.toCharArray()) freq[x]++;
        int ans = Integer.MAX_VALUE;
        for(char x : "coderams".toCharArray()) ans = Math.min(ans, freq[x]);
        System.out.println(ans);
      }
    }
  }
}