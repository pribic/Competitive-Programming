import java.util.Scanner;

/**
 * @author pribic (Priyank Doshi)
 * @since 03/04/21
 */
public class CPUtil {

  public static int gcd(int aa, int bb) { //16 12
    System.out.println(aa + " " + bb + "=");
    while (bb != 0) {
      int t = aa;
      aa = bb;
      bb = t % bb;
    }
    System.out.println(aa);
    return aa;
  }


}