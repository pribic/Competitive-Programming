package codeforce.educational.r107;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author pribic (Priyank Doshi)
 * @since 12/04/21
 */
public class D {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = 1;//sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int k = sc.nextInt();
        String[] pattern = new String[k];
        for (int i = 0; i < k; i++) {
          char startingChar = (char) ('a' + i);
          StringBuilder sb = new StringBuilder();
          sb.append(startingChar);
          for (int j = 1; j < k - i; j++) {
            sb.append(startingChar).append((char) (startingChar + j));
          }
          pattern[i] = sb.toString();
        }
        //System.out.println("Arrays.toString(pattern) = " + Arrays.toString(pattern));
        StringBuilder ans = new StringBuilder();
        int idx = 0;
        while (n > 0) {
          if(n >= pattern[idx].length()) {
            ans.append(pattern[idx]);
            n -= pattern[idx].length();
            idx = (idx + 1) % k;
          } else {
            ans.append(pattern[idx], 0, n);
            idx++;
            n = 0;
          }
        }
        System.out.println(ans);
      }
    }
  }
}