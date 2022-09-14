package codeforce.div4.r2;

import java.util.Scanner;

/**
 * @author pribic (Priyank Doshi)
 * @since 28/01/21
 */
public class B {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int[] arr = new int[n];
        int oddNumber = 0;
        int evenNumCnt = 0;
        for (int i = 0; i < n; i++) {
          arr[i] = sc.nextInt();
          if(arr[i] % 2 == 1)
            oddNumber = arr[i];
          else
            evenNumCnt++;
        }
        if(oddNumber == 0) {
          System.out.println(-1);
        } else {
          System.out.println(evenNumCnt);
        }
        
      }
    }
  }
}