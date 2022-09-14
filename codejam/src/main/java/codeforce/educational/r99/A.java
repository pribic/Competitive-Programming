package codeforce.educational.r99;

import java.util.Scanner;

public class A {

  public static void main(String[] args) {
    try(Scanner sc = new Scanner(System.in)) {
         int t = sc.nextInt();
         while (t-- > 0) {
           System.out.println(sc.next().length());
         }
    }
  }
}
