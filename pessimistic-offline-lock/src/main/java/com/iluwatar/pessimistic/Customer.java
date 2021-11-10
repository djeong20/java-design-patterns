package com.iluwatar.pessimistic;

public class Customer {
  private Long id;
  private String name;

  public Customer() {}

  public Customer(String name) {
    this.name = name;
  }

  public Long getID() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setID(Long newId) {
    this.id = newId;
  }

  public void setName(String newName) {
    this.name = newName;
  }
}
