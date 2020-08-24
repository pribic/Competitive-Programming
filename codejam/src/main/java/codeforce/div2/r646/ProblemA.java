package codeforce.div2.r646;

import java.util.Scanner;
import java.util.stream.IntStream;

public class ProblemA {
  public static void main(String[] args) {

      System.out.println("IntStream.range(0, 10).reduce((a,b) -> a + b) = " + IntStream.range(2, 3).reduce((a, b) -> a + b).getAsInt());
//    Scanner sc = new Scanner(System.in);
//    int t = sc.nextInt();
//   ll :  for (int tt = 0; tt < t; tt++) {
//      int n = sc.nextInt();
//      int x = sc.nextInt();
//      int[] input = new int[n];
//      int odd = 0;
//      int even = 0;
//      for (int i = 0; i < n; i++) {
//        input[i] = sc.nextInt();
//        odd += input[i] & 1;
//      }
//      even = n - odd;
//
//      for (int i = 0; i < x; i += 2) {
//        if(odd > 0 && (even >= (x-1) || (odd+1)/2 >= (x-1))) {
//          System.out.println("Yes");
//          continue ll;
//        }
//        even++;
//        odd -= 2;
//      }
//      System.out.println("No");
//    }
//    sc.close();
  }
  
  
}
