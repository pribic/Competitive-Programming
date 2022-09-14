package codeforce.technocup;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class D {
  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int n = sc.nextInt();
      sc.nextLine();
      int diff = 0;
      boolean flag = false;
      int[] ans = new int[n];
      int index = 0;
      for (int tt = 0; tt < 2 * n; tt++) {
        String[] tokens = sc.nextLine().split(" ");
        switch (tokens[0]) {
          case "+":
            diff++;
            break;
          case "-":
            diff--;
            if(diff < 0) {
              flag = true;
            }
            ans[index++] = Integer.parseInt(tokens[1]);
        }

      }
      if(flag) {
        System.out.println("NO");
      } else {
        for(int x : ans) 
          System.out.print(x + " ");
        System.out.println();
      }
    }
  }
}
