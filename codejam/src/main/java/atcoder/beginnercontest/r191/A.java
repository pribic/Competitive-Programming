package atcoder.beginnercontest.r191;

import java.util.Scanner;

/**
 * @author pribic (Priyank Doshi)
 * @since 13/02/21
 */
public class A {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = 1;//sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int v,t,s,d;
        v = sc.nextInt();
        t = sc.nextInt();
        s = sc.nextInt();
        d = sc.nextInt();
        if(v*t <= d && d <= v*s) System.out.println("No");
        else System.out.println("Yes");
      }
    }
  }
}