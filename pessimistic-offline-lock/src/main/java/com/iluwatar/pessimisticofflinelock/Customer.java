package com.iluwatar.pessimisticofflinelock;

public class Customer {
  private Long id;
  private String name;

  public Customer() {}

  public Customer(Long id, String name) {
    this.id = id;
    this.name = name;
  }

  public Long getID() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setID(Long new_id) {
//     Assert.notNull("Cannot set a null ID", ID);
    this.id = new_id;
  }

  public void setName(String new_name) {
    this.name = new_name;
  }
}
