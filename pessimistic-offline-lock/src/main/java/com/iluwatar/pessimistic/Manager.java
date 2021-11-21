package com.iluwatar.pessimistic;

/**
 * Manager Interface.
 */
public interface Manager {
  /**
   * Insert new customer information.
   * @param newCustomer - Customer
   */
  void insert(Customer newCustomer);

  /**
   * Delete current customer's information.
   * @param customerId - Customer ID
   * @param ownerId - Administrator ID
   */
  void delete(Long customerId, Long ownerId);

  /**
   *  Returns customer if it's not owned by any administrator.
   *  If customer is obtained lock the access permission.
   *  @param customerId - Customer ID
   *  @param ownerId - Administrator ID
   *  @return customer - Customer
   */
  Customer getCustomer(Long customerId, Long ownerId);

  /**
   * Release access permission to customer's information.
   * if it's owned by the administrator who owns access permission.
   *  @param customer - customer
   *  @param ownerId - Administrator ID
   */
  void release(Customer customer, Long ownerId);
}
