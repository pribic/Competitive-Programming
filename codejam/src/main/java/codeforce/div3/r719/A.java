package codeforce.div3.r719;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * @author pribic (Priyank Doshi)
 * @since 05/05/21
 */
public class A {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        String str = sc.next();
        StringBuilder sb = new StringBuilder();
        char prev = '.';
        for(char c : str.toCharArray()) {
          if(c != prev) {
            sb.append(c);
          }
          prev = c;
        }
        Set<Character> seen = new HashSet<>();
        boolean valid = true;
        for (char c : sb.toString().toCharArray()) {
          if(seen.contains(c))
            valid = false;
          seen.add(c);
        }
        System.out.println(valid ? "YES" : "NO");
      } 
    }
  }
}