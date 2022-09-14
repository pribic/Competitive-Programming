package codeforce.div3.r674;

import java.util.Scanner;

public class B_SymmetricMatrix {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int t = sc.nextInt();
      for (int i = 0; i < t; i++) {
        int n = sc.nextInt();
        int m = sc.nextInt();
        boolean flag = false;
        for (int j = 0; j < n; j++) {
          int a1 = sc.nextInt();
          int b1 = sc.nextInt();
          int c1 = sc.nextInt();
          int d1 = sc.nextInt();
          if(b1 == c1)
            flag = true;
        }
        if(m % 2 == 0 && flag)
          System.out.println("YES");
        else
          System.out.println("NO");
      }
    }
  }
}
