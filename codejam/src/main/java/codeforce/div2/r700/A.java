package codeforce.div2.r700;

import java.util.Scanner;

/**
 * @author pribic (Priyank Doshi)
 * @since 07/02/21
 */
public class A {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        String s = sc.next();
        StringBuilder sb = new StringBuilder();
        boolean alice = true;
        for(char x : s.toCharArray()) {
          if(alice) {
            if(x == 'a')
              sb.append('b');
            else
              sb.append('a');
          } else {
            if(x == 'z') 
              sb.append('y');
            else
              sb.append('z');
          }
          alice = !alice;
        }
        System.out.println(sb);
      }
    }
  }
}