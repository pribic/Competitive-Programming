package codeforce.educational.r105;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author pribic (Priyank Doshi)
 * @since 02/03/21
 */
public class C {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int m = sc.nextInt();
        long[] boxes = new long[n];
        for (int i = 0; i < n; i++) {
          boxes[i] = sc.nextLong();
        }
        long[] specials = new long[m];
        for (int i = 0; i < m; i++) {
          specials[i] = sc.nextLong();
        }
        // false false false ... true
        //
        int firstPositive;
        if(boxes[0] > 0) 
          firstPositive = 0;
        else if(boxes[n - 1] < 0){
          firstPositive = -1;
        } else {
          int l = 0;
          int r = n - 1;
          while (r > l + 1) {
            int mid = l + (l + r) / 2;
            if(boxes[mid] > 0) {
              r = mid;
            } else {
              l = mid;
            }
          }
          firstPositive = r;
        }
        
        int firstSpecial;
        if(specials[0] > 0) {
          firstSpecial = 0;
        } else if(specials[n - 1] < 0) {
          firstSpecial = -1;
        } else {
          int l = 0;
          int r = n - 1;
          while (r > l + 1) {
            int mid = l + (r + l) / 2;
            if(specials[mid] > 0) {
              r = mid;
            } else {
              l = mid;
            }
          }
          firstSpecial = r;
        }
        
        long ans = 0;
        
        if(firstPositive > 0 && firstSpecial >= 0) {
          
        }
        
        System.out.println(ans);
      }
    }
  }
}