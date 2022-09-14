package codeforce.div3.r713;

import java.util.Scanner;

/**
 * @author pribic (Priyank Doshi)
 * @since 10/04/21
 */
public class C {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int a = sc.nextInt();
        int b = sc.nextInt();
        char[] palindrome = sc.next().toCharArray();
        int len = palindrome.length;
        for (int i = 0; i < len; i++) {
          if (palindrome[i] == '?' && palindrome[len - 1 - i] != '?') {
            palindrome[i] = palindrome[len - 1 - i];
          }
        }
        int backupA = a;
        int backupB = b;
        for(char x : palindrome) {
          if(x == '1')
            b--;
          else if(x == '0')
            a--;
        }
        boolean found = true;
        for (int i = 0; i < len / 2; i++) {
          char replacementChar = '2';
          if (palindrome[i] == '?') {
            if (a >= 2) {
              replacementChar = '0';
              a -= 2;
            } else if (b >= 2) {
              replacementChar = '1';
              b -= 2;
            }
            if (replacementChar == '2') {
              found = false;
            } else {
              palindrome[i] = replacementChar;
              palindrome[len - 1 - i] = replacementChar;
            }
          }
        }
        if (len % 2 == 1) {
          if (palindrome[len / 2] == '?') {
            //need to replace this by 0 or 1
            if (a > 0) {
              palindrome[len / 2] = '0';
              a--;
            } else if (b > 0) {
              palindrome[len / 2] = '1';
              b--;
            }
          }
        }
        a = backupA;
        b = backupB;
        if (isAnyQuestionMark(palindrome) || !isPalindrome(palindrome) || !cntMatches(palindrome, a, b)) {
          found = false;
        }
        if (found) {
          for (char x : palindrome) {
            System.out.print(x);
          }
          System.out.println();
        } else {
          System.out.println(-1);
        }
      }
    }
  }

  private static boolean isAnyQuestionMark(char[] palindrome) {
    for (char x : palindrome) {
      if (x == '?')
        return true;
    }
    return false;
  }

  private static boolean cntMatches(char[] palindrome, int a, int b) {
    for (char x : palindrome) {
      switch (x) {
        case '0':
          a--;
          break;
        case '1':
          b--;
          break;
        default:
          throw new IllegalArgumentException("illegal character");
      }
    }
    return a == 0 && b == 0;
  }

  private static boolean isPalindrome(char[] palindrome) {
    for (int i = 0; i < palindrome.length / 2; i++) {
      if (palindrome[i] != palindrome[palindrome.length - 1 - i])
        return false;
    }
    return true;
  }

}