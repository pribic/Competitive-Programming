package codeforce.div2.r685;

import java.util.Scanner;

public class A {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int t = sc.nextInt();
      while (t-- > 0) {
        int n =sc.nextInt();
        int ans = 0;
        if(n == 1)
          System.out.println(0);
        else if(n == 2)
          System.out.println(1);
        else if(n==3 || n % 2 == 0)
          System.out.println(2);
        else
          System.out.println(3);
          
      }
    }
  }
}







