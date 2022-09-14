package codeforce.assiut.recursion;

import java.util.Scanner;

import static java.util.stream.Collectors.joining;

/**
 * @author pribic (Priyank Doshi)
 * @since 10/05/21
 */
public class ICountVowels {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = 1;//sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        String str = sc.nextLine();
        int ans = countVovel(str.toLowerCase(), 0);
        System.out.println(ans);
      }
    }
  }

  private static int countVovel(String str, int idx) {
    if (idx == str.length())
      return 0;
    if (str.charAt(idx) == 'a' || str.charAt(idx) == 'e' || str.charAt(idx) == 'i' || str.charAt(idx) == 'o' || str.charAt(idx) == 'u') {
      return 1 + countVovel(str, idx + 1);
    } else {
      return countVovel(str, idx + 1);
    }
  }
}