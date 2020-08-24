package diff;


public class DiffRunner {
  public static void main(String[] args) {
    A a1 = new A("name1", "fname1", "lname1", 1);
    A a2 = new A("name1", "fname2", "lname2", 2);
    new DiffRunner().findDiff(a1, a2);
  }
  
  DiffNode findDiff(A left, A right) {
    DiffNode root = new DiffNode();
    for(int i = 0; i < left.mapping.size(); i++) {
      String propertyName = left.mapping.get(i).propertyName;
      A.PropertyNode leftProp = left.mapping.get(i);
      A.PropertyNode rightProp = right.mapping.get(i);
      Object leftValue = leftProp.getter.get();
      Object rightValue = rightProp.getter.get();
      System.out.println(String.format("PropertyName=%s, Left=%s, Right=%s, Same=%s",  propertyName, leftValue, rightValue, leftValue.equals(rightValue)));
    }
    return null;
  }
}
