package cses.IntroductoryProblmes;

import java.util.Objects;
import java.util.Scanner;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MissingNumber {
  public static void main(String[] args) {

    try (Scanner sc = new Scanner(System.in)) {
      int n = sc.nextInt();
      long sum = 0;
      for (int i = 0; i < n -1; i++) {
        sum += sc.nextInt();
      }
      System.out.print( (((long)(long)n * (n+1)) /2) - sum);
    }
  }

}
