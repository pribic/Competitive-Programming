package atcoder.beginnercontest.r179;

import java.util.Scanner;

public class PluralForm {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      String s = sc.next();
      if (s.endsWith("s"))
        s += "e";
      System.out.println(s + "s");
    }
  }
}
