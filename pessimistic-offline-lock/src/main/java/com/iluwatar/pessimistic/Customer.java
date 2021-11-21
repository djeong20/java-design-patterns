package com.iluwatar.pessimistic;

/**
 * This class contains every customer information.
 * @author Donghyeon Jeong
 */
public class Customer {
  private transient Long customerId; // Customer ID
  private String name; // Customer name

  /**
   * Default Constructor for Customer class.
   */
  public Customer() {}

  /**
   * Customer Constructor that assigns customer name.
   * @param name - Customer name
   */
  public Customer(final String name) {
    this.name = name;
  }

  /**
   * Sets the customer ID.
   * @param newId - Customer ID
   */
  public void setID(final Long newId) {
    this.customerId = newId;
  }

  /**
   * Returns the customer ID.
   * @return id - Customer ID
   */
  public Long getID() {
    return this.customerId;
  }

  /**
   * Sets the customer name.
   * @param newName - Customer name
   */
  public void setName(final String newName) {
    this.name = newName;
  }

  /**
   * Returns the customer name.
   * @return name- Customer name
   */
  public String getName() {
    return this.name;
  }

}
