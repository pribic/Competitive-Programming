package codeforce.educational.r105;

import java.util.Scanner;
import java.util.Stack;

/**
 * @author pribic (Priyank Doshi)
 * @since 02/03/21
 */
public class A {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        String str = sc.next();
        char cc = str.charAt(0);
        str = str.replaceAll(cc + "", "(");
        int index = cc - 'A';
        boolean ans = false;
        //ABCABCBB
        //()(()())
        String[] brackets = new String[]{"(", ")"};
        outer:
        for(int i = 0; i < 2; i++) {
          for (int j = 0; j < 2; j++) {
            String b = brackets[i];
            String c = brackets[j];
            String str1 = str.replaceAll((char)('A' + ((index + 1 )% 3)) + "", b);
            str1 = str1.replaceAll((char)('A' + ((index + 2 )% 3)) + "", c);
            if(isValid(str1)) {
              ans = true;
              break outer;
            }
          }
        }
        System.out.println(ans ? "YES" : "NO");
      }
    }
  }
  
  private static boolean isValid(String str) {
    Stack<Character> stack = new Stack<>();
    for(char c : str.toCharArray()) {
      if(c == '(') stack.push('(');
      else{
        if(stack.isEmpty()) return false;
        stack.pop();
      }
    }
    return stack.isEmpty();
  }
}