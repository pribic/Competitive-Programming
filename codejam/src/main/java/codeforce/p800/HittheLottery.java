package codeforce.p800;

import java.util.Scanner;

public class HittheLottery {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int n = sc.nextInt();
      long ans = 0;

      for (int i : new int[]{100, 20, 10, 5, 1}) {
        if (n >= i) {
          long bill = n / i;
          ans += bill;
          n -= (i * bill);
        }
      }
      System.out.println(ans);
    }
  }
}
