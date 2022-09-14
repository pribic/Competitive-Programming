package codeforce.p800;

import java.util.Scanner;

public class SummerCamp {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int n = sc.nextInt();
      if (n <= 9) {
        System.out.println(n);
      } else if (n <= 189) {
        n -= 9;
        int slot = (n + 1) / 2;
        int num = 9 + slot;
        System.out.println(n % 2 == 1 ? num/10 : num % 10);
      } else if (n <= 2889) {
        n -= 189;
        int slot = (n + 2) / 3;
        int num = 189 + slot;
        if (n%3 == 1) {
          System.out.println(n/100);
        } else if(n%3 == 2) {
          System.out.println();
        } else {
          
        }
      }
    }
  }
}
