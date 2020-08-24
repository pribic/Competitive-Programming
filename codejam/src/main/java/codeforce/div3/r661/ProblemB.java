package codeforce.div3.r661;

import java.util.Scanner;

/**
 * @author pdoshi 
 */
public class ProblemB {
  
  
  public static void main(String[] args) {
    try(Scanner sc = new Scanner(System.in)) {
      int t = sc.nextInt();
      for (int tt = 0; tt < t; tt++) {
        int n = sc.nextInt();
        int[] a = new int[n];
        int[] b = new int[n];
        int mina = Integer.MAX_VALUE; //3
        int minb = Integer.MAX_VALUE; //2
        for (int i = 0; i < n; i++) {
          a[i] = sc.nextInt();
          mina = Math.min(mina, a[i]);
        }
        for (int i = 0; i < n; i++) {
          b[i] = sc.nextInt();
          minb = Math.min(minb, b[i]);
        }
        long ans = 0;
        for (int i = 0; i < n; i++) { //3 3
          if(a[i] == mina) {
            ans += b[i] - minb;
          } else if( b[i] == minb) {
            ans += a[i] - mina;
          } else {
            int diffa = a[i] - mina;
            int diffb = b[i] - minb;
            if(diffa == diffb) {
              ans += diffa;
            } else if( diffa > diffb) {
              ans += diffb;
              a[i] -= diffb;
              b[i] -= diffb;
              ans += (a[i] - mina);
              ans += (b[i] - minb);
            } else {
              ans += diffa;
              a[i] -= diffa;
              b[i] -= diffa;
              ans += (a[i] - mina);
              ans += (b[i] - minb);
            }
          }
          
        }
        System.out.println(ans);
      }
    }
  }
}
