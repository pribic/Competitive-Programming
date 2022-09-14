package codeforce.div2.r689;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class A {
  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int t = sc.nextInt();
      while (t-- > 0) {
        int n = sc.nextInt();
        int k = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < k;i++) sb.append('a');
        char[] c = {'b', 'c', 'a'};
        int index = 0;
        for(int i = k; i < n; i++,index++) sb.append(c[index % 3]);
        System.out.println(sb.toString());
      }
    }
  }
}
