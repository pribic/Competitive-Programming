package codeforce.div3.r690;

import java.util.Scanner;

public class B {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int t = sc.nextInt();
      while (t-- > 0) {
        int n = sc.nextInt();
        String s = sc.next();
        if (s.startsWith("2020")
          || s.endsWith("2020")
          || (s.startsWith("2") && s.endsWith("020"))
          || (s.startsWith("20") && s.endsWith("20"))
          || (s.startsWith("202") && s.endsWith("0")))
          System.out.println("YES");
        else
          System.out.println("NO");

      }
    }
  }
}
