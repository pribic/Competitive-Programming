package codeforce.educational.r100;

import java.util.Scanner;

public class A {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int t = sc.nextInt();
      while (t-- > 0) {
        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = sc.nextInt();
        long sum = a + b + c;
        if (sum % 9 != 0)
          System.out.println("NO");
        else {
          int min = Math.min(a, Math.min(b, c));
          if(sum > min * 9L)
            System.out.println("NO");
          else
            System.out.println("YES");
        }
      }
    }
  }
}
