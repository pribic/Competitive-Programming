package codeforce.div2.r680;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class C_Division {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int t = sc.nextInt();
      while (t-- > 0) {
        long p = sc.nextLong();
        long q = sc.nextLong();
        if (p < q) {
          System.out.println(p);
          continue;
        }
        if (p == q) {
          System.out.println(p / 2);
          continue;
        }
        if(p % q != 0) {
          System.out.println(p);
          continue;
        }
        for (long i1 = (long) Math.sqrt(p); i1 >= 1; i1--) {
          if (p % i1 == 0) {
            if (p / i1 % q != 0) {
              System.out.println(p / i1);
              break;
            }
          }
        }
      }
    }
  }

}
