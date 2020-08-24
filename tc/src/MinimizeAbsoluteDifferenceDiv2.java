public class MinimizeAbsoluteDifferenceDiv2 {
  int [] findTripple(int x0, int x1, int x2) {
    x0 = 10000*x0;
    x1 = 10000*x1;
    x2 = 10000*x2;
    
    double p1 = a(x0,x1,x2);
    double p2 = a(x0, x2, x1);
    double p3 = a(x1, x0, x2);
    double p4 = a(x1, x2, x0);
    double p5 = a(x2, x0, x1);
    double p6 = a(x2, x1, x0);
    
    if(isaBoolean(p1, p2, p3, p4, p5, p6)) {
      return new int[] {x0,x1,x2};
    }
    if(isaBoolean(p2, p1, p3, p4, p5, p6)) {
      return new int[] {x0,x2,x1};
    }
    if(isaBoolean(p3, p2, p1, p4, p5, p6)) {
      return new int[] {x1,x0,x2};
    }
    if(isaBoolean(p4, p2,p3,p1,p5,p6)) {
      return new int[] {x1,x2,x0};
    }
    if(isaBoolean(p5, p2, p3, p4, p1, p6)) {
      return new int[] {x2,x0,x1};
    }
    if(isaBoolean(p6, p2, p3, p4, p5, p1)) {
      return new int[] {x2,x1,x0};
    }
    return new int[3];
  }

  private boolean isaBoolean(double p1, double p2, double p3, double p4, double p5, double p6) {
    return p1 <= p2 && p1 <= p3 && p1 <= p4 && p1 <= p5 && p1 <= p6;
  }

  double a(double a, double b, double c) {
    return (double) Math.abs(a/b -c);
  }
}
