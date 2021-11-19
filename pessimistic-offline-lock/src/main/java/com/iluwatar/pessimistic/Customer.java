package com.iluwatar.pessimistic;

public class Customer {
  private Long id;
  private String name;

  /**
   * Customer Default Constructor.
   */
  public Customer() {}

  /**
   * Customer Constructor that assigns customer name.
   * @param name - Customer name
   */
  public Customer(String name) {
    this.name = name;
  }

  /**
   * Sets the customer ID.
   * @param newId - Customer ID
   */
  public void setID(Long newId) {
    this.id = newId;
  }

  /**
   * Returns the customer ID.
   * @return id - Customer ID
   */
  public Long getID() {
    return id;
  }

  /**
   * Sets the customer name.
   * @param newName - Customer name
   */
  public void setName(String newName) {
    this.name = newName;
  }

  /**
   * Returns the customer name.
   * @return name- Customer name
   */
  public String getName() {
    return name;
  }

}
