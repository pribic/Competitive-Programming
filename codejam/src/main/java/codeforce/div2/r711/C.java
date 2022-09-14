package codeforce.div2.r711;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 1100
 * <p>
 * 101101
 * <p>
 * ()()() -> ((()))
 *
 * @author pribic (Priyank Doshi)
 * @since 03/04/21
 */
public class C {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        String s = sc.next();
        if (s.charAt(0) == '0' || s.charAt(n - 1) == '0') {
          System.out.println("NO");
        } else {
          List<Integer> indexOfZero = count(s, '0');
          if (indexOfZero.size() % 2 == 1) {
            System.out.println("NO");
          } else {
            List<Integer> list = new ArrayList<>();
            int start = 0;
            boolean flag = true;
            for (int i = 0; i < indexOfZero.size(); i += 2) {
              int a = indexOfZero.get(i);
              int b = indexOfZero.get(i + 1);
              if(a == start) {
                flag = false;
                break;
              }
              int diff = b - a - 1;
              if (diff % 2 == 0) {
                int end = b + (a - start);
                if(end >= n) {
                  flag = false;
                  break;
                }
                list.add(end - start + 1);
                start = end + 1;
              } else {
                flag = false;
                break;
              }

            }
            if(start < n) {
              list.add(n - start);
            }
            if(flag) {
              System.out.println("YES");
              StringBuilder sb = new StringBuilder();
              for (int num : list) {
                for (int i = 0; i < num / 2; i++) {
                  sb.append('(');
                }
                for (int i = num / 2; i < num; i++) {
                  sb.append(')');
                }
              }
              System.out.println(sb);
              StringBuilder sb1 = new StringBuilder();
              for(int i = 0; i < sb.length(); i++) {
                if(s.charAt(i) == '1') {
                  sb1.append(sb.charAt(i));
                } else {
                  sb1.append(sb.charAt(i) == '(' ? ')' : '(');
                }
              }
              System.out.println(sb1);
            } else {
              System.out.println("NO");
            }
          }
        }
      }
    }
  }

  private static List<Integer> count(String s, char c) {
    List<Integer> indexes = new ArrayList<>();
    for (int i = 0; i < s.length(); i++) {
      if (s.charAt(i) == c) {
        indexes.add(i);
      }
    }
    return indexes;
  }
}


//(())((()))
//