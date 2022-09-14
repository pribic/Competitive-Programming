package codeforce.educational.r106;

import java.util.Scanner;

/**
 * @author pribic (Priyank Doshi)
 * @since 18/03/21
 */
public class B {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
       // int n = sc.nextInt();
        String s = sc.next();
        int index = s.indexOf("11");
        if (index == -1) {
          System.out.println("YES");
        } else {
          int index1 = s.indexOf("00", index);
          System.out.println(index1 == -1 ? "YES" : "NO");
        }
      }
    }
  }
}