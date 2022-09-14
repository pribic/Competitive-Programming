package codeforce.div2.r712;

import java.util.Scanner;

/**
 * @author pribic (Priyank Doshi)
 * @since 03/04/21
 */
public class A {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        String s = sc.next();
        if (s.replaceAll("a", "").isEmpty()) {
          System.out.println("NO");
        } else {
          System.out.println("YES");
          for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) != 'a') {
              if(i < s.length()/2) {
                //abaaa
                s = s.substring(0, s.length() - i) + 'a' + s.substring(s.length() - i);  
              } else {
                //aaaba
                s = s.substring(0, s.length() - i - 1) + 'a' + s.substring(s.length() - i - 1);
              }
              break;
            }
          }
          System.out.println(s);
        }
      }
    }
  }
}