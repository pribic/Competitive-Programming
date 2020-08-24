package codeforce.div2.r651;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class ProblemC {

  static Map<String, String> map = new HashMap<>();

  public static void main(String[] args) {
    map.put("Ashishgup", "FastestFinger");
    map.put("FastestFinger", "Ashishgup");
    try (Scanner sc = new Scanner(System.in)) {
      int t = sc.nextInt();
      for (int ii = 0; ii < t; ii++) {
        long n = sc.nextLong();
        System.out.println(winner(n, "Ashishgup"));
      }
    }
  }

  private static String winner(long n, String current) {
    printIf(() -> n + " input" + " " + current);
    String next = map.get(current);
    if (n == 1) {
      printIf(() -> n + " n one" + " " + current + " winnder ->" + next);
      return next;
    }
    if (n % 2 == 1) {
      printIf(() -> n + " n odd" + " " + current + " winnder ->" + current);
      return current;
    }
    String win1 = winner(n - 1, next);
    if (win1 == current) {
      printIf(() -> n - 1 + " n - 1" + " " + current + " winnder ->" + current);
      return current;
    }
    for (long factor : printDivisors(n)) {
      win1 = winner(n / factor, next);
      if (win1 == current) {
        printIf(() -> n + " " + n / factor + " factor" + " " + current + " winnder ->" + next);
        return current;
      }
    }
    return next;
  }

  static long[] printDivisors(long n) {
    List<Long> integers = new ArrayList<>();
    // Note that this loop runs till square root 
    for (long i = 2; i <= Math.sqrt(n); i++) {
      if (n % i == 0) {
        // If divisors are equal, print only one 
        if (n / i == i)
          addIfOdd(integers, i);// integers.add(i);

        else {
          addIfOdd(integers, i);// integers.add(i);
          addIfOdd(integers, n / i);// integers.add(n / i);
        }
      }
    }
    long[] ans = new long[integers.size()];
    int index = 0;
    for (int i = 0; i < integers.size(); i++) {
      ans[i] = integers.get(i);
    }
    return ans;
  }

  static void addIfOdd(List<Long> list, long n) {
    if (n % 2 == 1)
      list.add(n);
  }
  
  static void printIf(Supplier<String> consumer) {
    //System.out.println(consumer.get());
  }
}


