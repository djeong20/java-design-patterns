package com.iluwatar.pessimistic;

public interface Manager {
  /**
   * Insert new customer information.
   * @param newCustomer - Customer
   */
  public void insert(Customer newCustomer);

  /**
   * Delete current customer's information.
   * @param customerId - Customer ID
   * @param ownerId - Administrator ID
   */
  public void delete(Long customerId, Long ownerId);

  /**
   * Returns customer if it's not owned by any administrator and lock the access permission.
   *  @param customerId - Customer ID
   *  @param ownerId - Administrator ID
   *  @return customer - customer (null if it's owned)
   */
  public Customer getCustomer(Long customerId, Long ownerId);

  /**
   * Release access permission to customer's information.
   * if it's owned by the administrator who owns access permission.
   *  @param customer - customer
   *  @param ownerId - Administrator ID
   */
  public void release(Customer customer, Long ownerId);
}
