package codeforce.div3.r686;

import java.util.Scanner;

public class A {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
        int t = sc.nextInt();
        while (t-- > 0) {
          int n = sc.nextInt();
          for(int i = 2; i <= n; i++)
            System.out.print(i + " ");
          System.out.println(1);
        }
    }
  }
}
