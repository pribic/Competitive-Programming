package codeforce.div2.r671;

import java.util.Scanner;

public class Killjoy {

  public static void main(String[] args) {
    try(Scanner sc = new Scanner(System.in)) {
        int t = sc.nextInt();
      for (int tt = 0; tt < t; tt++) {
        int n = sc.nextInt();
        int x = sc.nextInt(); //kill joy's rating
        int[] arr = new int[n];
        boolean allsame = false;
        int diff = 0;
        for (int i = 0; i < n; i++) {
          arr[i] = sc.nextInt();
          allsame = allsame || (arr[i] == x);
          diff += (arr[i] - x);
        }
        if (allsame) {
          System.out.println(0);
        } 
        else if(diff == 0) {
          System.out.println(1);
        } else {
          System.out.println(2);
        }
      } 
    }
  }
}
