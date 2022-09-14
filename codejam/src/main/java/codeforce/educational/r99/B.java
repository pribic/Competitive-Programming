package codeforce.educational.r99;

import java.util.Scanner;

public class B {

  public static void main(String[] args) {
    try(Scanner sc = new Scanner(System.in)) {
         int t = sc.nextInt();
         while (t-- > 0) {
           int x = sc.nextInt();
           int sum = 0;
           int i = 1;
           for( ;i <= 1000000;i++) {
             sum += i;
             if(sum >=x)
               break;
           }
           if(sum - 1 == x)
             i++;
           System.out.println(i);
         }
    }
  }
}
