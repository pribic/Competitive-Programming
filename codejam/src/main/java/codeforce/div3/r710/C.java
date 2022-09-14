package codeforce.div3.r710;

import java.util.Scanner;

/**
 * @author pribic (Priyank Doshi)
 * @since 25/03/21
 */
public class C {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        String a = sc.next();
        String b = sc.next();
        if(a.length() < b.length()) {
          String t = a;
          a = b;
          b = t;
        }
        //a is bigger, b is smaller
        int ans = Integer.MAX_VALUE;
        for(int i = 0; i < b.length(); i++) {
          for(int j = i + 1; j <= b.length(); j++) {
            String sub = b.substring(i, j);
            if(a.contains(sub)) {
              int len = j - i;
              ans = Math.min(ans, b.length() - len + a.length() - len);
            }
          }
        }
        System.out.println(ans == Integer.MAX_VALUE ? (a.length() + b.length()) : ans);
      }
    }
  }
}