import java.util.*;
import java.math.*;

import static java.lang.Math.*;

public class CutTheCube {
  public static void main(String[] args) {
    new CutTheCube().findWinner(1, 1, 1);
  }

  public int findWinner(int L, int B, int H) {
    if ((L % 2) * (B % 2) * (H % 2) == 0)
      return 1;
    return 2;


  }

}
