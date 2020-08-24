package cses.IntroductoryProblmes;

import java.util.Objects;
import java.util.Scanner;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class WeirdAlgoridhm {

  public static void main(String[] args) {

    Stream.of(new User("user1"), new User(null)).map(User::getUserId).filter(Objects::nonNull).map(String::toLowerCase).forEach(System.out::println);
    try (Scanner sc = new Scanner(System.in)) {
      int n = sc.nextInt();
      IntStream.iterate(n, a -> (a % 2 == 0) ? a / 2 : (a * 3 + 1))
        .filter(a -> a != 1)
        .forEachOrdered(x -> System.out.println(x + " "));
      System.out.print("1");
    }
  }
  
  static class User {
    String userId;

    public User(String userId) {
      this.userId = userId;
    }

    public String getUserId() {
      return userId;
    }
  }
}
