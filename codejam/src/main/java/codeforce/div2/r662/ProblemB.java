package codeforce.div2.r662;

import java.util.Arrays;
import java.util.Scanner;

public class ProblemB {
  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int n = sc.nextInt();
      int[] arr = new int[100000 + 1];
      for (int i = 0; i < n; i++) {
        arr[sc.nextInt()]++;
      }
      int square = 0;
      int rectangle = 0;

      for (int i = 0; i < n; i++) {
        square = square + arr[i] / 4;
        rectangle = rectangle + arr[i] / 2;
      }
   //   Arrays.stream(arr).forEach(x -> System.out.print(x + " "));
      int q = sc.nextInt();
      for (int i = 0; i < q; i++) {
        String op = sc.next();
       // System.out.println("op = " + op);
        int val = sc.nextInt();
        switch (op) {
          case "+":
            arr[val]++;
            if(arr[val] % 4 == 0)
              square++;
            if(arr[val] % 2 == 0)
              rectangle++;
            break;
          case "-":
            arr[val]--;
            if(arr[val] % 4 == 3)
              square--;
            if(arr[val] % 2 == 1)
              rectangle--;
            break;
        }
  //      System.out.println("square = " + square);
    //    System.out.println("rectangle = " + rectangle);
        if (square >= 2 || (square == 1 && rectangle >= 4))
          System.out.println("YES");
        else
          System.out.println("NO");
      //  Arrays.stream(arr).forEach(x -> System.out.print(x + " "));

      }

    }
  }
}
