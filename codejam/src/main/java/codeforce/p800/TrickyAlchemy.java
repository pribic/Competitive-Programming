package codeforce.p800;

import java.util.Scanner;

public class TrickyAlchemy {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int a = sc.nextInt();
      int b = sc.nextInt();
      int x = sc.nextInt();
      int y = sc.nextInt();
      int z = sc.nextInt();
      long yellow = 2L * x + y;
      long blue = y + 3L * z;
      System.out.println(Math.max(0, yellow - a) + Math.max(0, blue - b));
    }
  }
}
