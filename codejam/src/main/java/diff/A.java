package diff;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class A {

  private String name;

  private String fname;

  private String lname;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  private Integer id;

  public List<PropertyNode> mapping = new ArrayList<>();

  public static class PropertyNode<T> {

    String propertyName;
    Supplier<T> getter;

    public PropertyNode(String propertyName, Supplier<T> getter) {
      this.propertyName = propertyName;
      this.getter = getter;
    }

  }

  public A(String name, String fname, String lname, Integer id) {
    this.name = name;
    this.fname = fname;
    this.lname = lname;
    this.id = id;
    this.mapping.add(new PropertyNode<String>("name", this::getName));
    this.mapping.add(new PropertyNode<Integer>("id", this::getId));
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getFname() {
    return fname;
  }

  public void setFname(String fname) {
    this.fname = fname;
  }

  public String getLname() {
    return lname;
  }

  public void setLname(String lname) {
    this.lname = lname;
  }
}
