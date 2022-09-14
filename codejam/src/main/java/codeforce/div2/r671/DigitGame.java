package codeforce.div2.r671;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class DigitGame {

  public static void main(String[] args) {
   /* for (int i = 1; i < 10; i++) {
      System.out.println(1);
      System.out.println(i);
    }
    for (int i = 10; i < 100; i++) {
      System.out.println(2);
      System.out.println(i);
    }
    for (int i = 100; i < 1000; i++) {
      System.out.println(3);
      System.out.println(i);
    }
    for (int i = 1000; i <= 1000; i++) {
      System.out.println(1);
      System.out.println(i);
    }*/
    try (Scanner sc = new Scanner(System.in)) {
      int t = sc.nextInt();
      for (int tt = 0; tt < t; tt++) {
        int len = sc.nextInt();
        int n = sc.nextInt();

        List<Integer> digits = new ArrayList<>();
        while (n > 0) {
          int d = n % 10;
          digits.add(d);
          n /= 10;
        }
        Collections.reverse(digits);
        if (len % 2 == 1) {
          //depends upon raze
          boolean anyOdd = false;
          for (int i = 0; i < len; i += 2) {
            if(digits.get(i) % 2 == 1) {
              anyOdd = true;
              break;
            }
          }
          System.out.println(anyOdd ? "1" : "2");
        } else {
          //depends upon another
          boolean anyEven = false;
          for (int i = 1; i < len; i+=2) {
            if(digits.get(i) % 2 == 0) {
              anyEven = true;
              break;
            }
          }
          System.out.println(anyEven ? "2" : "1");
        }
      }
    }
  }
}
