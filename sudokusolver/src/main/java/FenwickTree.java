import java.util.Scanner;

public class FenwickTree {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int[] input = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
    int[] handmade = new int[]{};
    BIT fenwickTree = new BIT(handmade);
    char in;
    while ((in = sc.next().charAt(0)) != 'E') {
      if (in == 'Q')
        System.out.println(fenwickTree.query(sc.nextInt(), sc.nextInt()));
      else
        fenwickTree.update(sc.nextInt(), sc.nextInt());
    }
    String priyank = "priyank";
    if (priyank != null) {
      System.out.println("priyank = " + priyank);
    }
    
    sc.close();
  }

  static class BIT {
    int[] arr;

    BIT(int[] arr) {
      this.arr = arr;
    }

    void update(int index, int newVal) {

    }

    int query(int startIndex, int endIndex) {
      return get(endIndex) - get(startIndex);
    }

    private int get(int index) {
      return 0;
    }

  }
}
