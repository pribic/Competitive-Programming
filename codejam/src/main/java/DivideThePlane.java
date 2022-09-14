import java.util.Scanner;


/**
 * -------
 * - - - -
 * 6 4
 * 2 2 8 6 9 4--
 * <p>
 * <p>
 * <p>
 * <p>
 * 10 8
 * 1 1 1 5 2 4 4 8 6 7
 * 1 1
 * 666
 * 2 2
 * 2 4
 * <p>
 * <p>
 * 4
 * <p>
 * 5
 * <p>
 * 1 1 3
 * <p>
 * 6
 * <p>
 * 2 2 2
 * <p>
 * 7
 * <p>
 * 3 3 1
 * <p>
 * 23 11 11 1
 * <p>
 * 8 8 7
 * <p>
 * 9 9 1
 * <p>
 * 8 4 4
 * <p>
 * 20
 * <p>
 * 10 5 5
 */
public class DivideThePlane {

  public long makeCuts(int H, int V, long N) {
    long result = 0;
    long x = H + 1;
    long y = V + 1;
    while (x * y < N) {
      if(x <= y)
        x++;
      else
        y++;
      result++;
    }
    return result;
  }

  private boolean check(long H, long V, long n, long mid) {
    long h = Math.min(H, V);
    long v = Math.max(H, V);
    long initial = (h + 1L) * (v + 1L);
    if (initial >= n) return true;
    long diff = v - h;
    if (mid <= diff) {
      h += mid;
      return (h + 1L) * (v + 1L) >= n;
    } else {
      h = v;
      mid -= diff;

      h += mid / 2;
      v += mid / 2;
      v += mid % 2;
      return (h + 1L) * (v + 1L) >= n;
    }
  }

  public static void main(String[] args) {
    System.out.println(new DivideThePlane().makeCuts(0 ,0,1));
    System.out.println(new DivideThePlane().makeCuts(0 ,0,3));
    System.out.println(new DivideThePlane().makeCuts(4 ,0,3));
    System.out.println(new DivideThePlane().makeCuts(4 ,0,20));
    System.out.println(new DivideThePlane().makeCuts(1 ,3,35));
    System.out.println(new DivideThePlane().makeCuts(99999 ,99997,10000000000L));
    System.out.println(new DivideThePlane().makeCuts(4 ,7,12345));
    long time = System.currentTimeMillis();
    System.out.println(new DivideThePlane().makeCuts(1 ,1000000,100000000000000L));
    System.out.println(System.currentTimeMillis() - time);
  }
}
 