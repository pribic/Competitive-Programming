package codeforce.div2.r681;

import java.util.Scanner;

public class A_KidsSeating {

  public static void main(String[] args) {
    try(Scanner sc = new Scanner(System.in)) {
        int t = sc.nextInt();
        while (t-- > 0) {
          int n = sc.nextInt();
          for(int i = 4 * n; i > 2 * n; i -= 2) {
            System.out.print(i + " ");
          }
          System.out.println();
        }
    }
  }
}

//20 18 16 14 12
