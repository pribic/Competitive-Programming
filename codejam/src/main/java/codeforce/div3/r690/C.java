package codeforce.div3.r690;

import java.util.Scanner;

public class C {

  /**
   * 10 -> 19
   * 11 -> 29
   * 12 -> 39
   * 13 -> 49
   * 14 -> 59
   * 15 -> 69
   * 16 -> 79
   * 17 -> 89
   * 18 -> 189
   * 19 -> 289
   * 20 -> 389
   * 21 -> 489
   * 22 -> 589
   * 23 -> 689
   * 24 -> 789
   * 25 -> 1789
   * 26 -> 2789
   * 27 -> 3789
   * 28 -> 4789
   * 29 -> 5789
   * 30 -> 6789
   * 31 -> 16789
   * 28  -> 19
   *
   * @param args
   */
  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int t = sc.nextInt();
      while (t-- > 0) {
        for (int x = 1; x <= 1; x++) {
        //  System.out.println(x + "=");
           x = sc.nextInt();
          if (x <= 9) {
            System.out.println(x);
          } else if (x <= 17) {
            System.out.println((x - 9 + "") + "9");
          } else if (x <= 24) {
            System.out.println((x - 17 + "") + "89");
          } else if (x <= 30) {
            System.out.println((x - 24 + "") + "789");
          } else if (x <= 35) {
            System.out.println((x - 30 + "") + "6789");
          } else if (x <= 39) {
            System.out.println((x - 35 + "") + "56789");
          } else if (x <= 42) {
            System.out.println((x - 39 + "") + "456789");
          } else if (x <= 44) {
            System.out.println((x - 42 + "") + "3456789");
          } else if (x <= 45) {
            System.out.println((x - 44 + "") + "23456789");
          } else {
            System.out.println(-1);
          }
        }
      }
    }
  }
}
