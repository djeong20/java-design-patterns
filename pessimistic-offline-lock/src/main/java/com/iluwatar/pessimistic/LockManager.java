package com.iluwatar.pessimistic;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * This class manages customers information access permission.
 * @author Donghyeon Jeong
 */
public class LockManager implements Manager {
  private final transient Map<Long, Customer> customers; // Customer Information
  private final transient Map<Long, Long> accessPermissions; // Access Permission Information
  private final transient Lock mutex; // Mutex lock
  private transient long newCustomerId; // New customer's ID
  public transient int total; // Total number of customers

  /**
   * Lock Manager Default Constructor.
   * Initialize private variables.
   */
  public LockManager() {
    customers = new HashMap<>();           // key: customer id, value: customer info
    accessPermissions = new HashMap<>();  // key: customer id, value: owner id
    mutex = new ReentrantLock(true);
    newCustomerId = 0;
    total = 0;
  }

  /**
   * Insert new customer information.
   * @param newCustomer - Customer
   */
  @Override
  public void insert(final Customer newCustomer) {
    newCustomer.setID(newCustomerId);
    customers.put(newCustomerId, newCustomer);
    accessPermissions.put(newCustomerId, (long) -1);
    newCustomerId++;
    total++;
  }

  /**
   * Delete current customer's information.
   * @param customerId - Customer ID
   * @param ownerId - Administrator ID
   */
  @Override
  public void delete(final Long customerId, final Long ownerId) {
    final long currentOwner = accessPermissions.get(customerId);

    if (currentOwner == ownerId) {
      customers.remove(customerId);
      accessPermissions.remove(customerId);
      total--;
    }
  }

  /**
   *  Returns customer if it's not owned by any administrator.
   *  If customer is obtained lock the access permission.
   *  @param customerId - Customer ID
   *  @param ownerId - Administrator ID
   *  @return customer - customer
   */
  @Override
  public Customer getCustomer(final Long customerId, final Long ownerId) {
    mutex.lock();
    Customer customer = null;

    final long currentOwnerId = accessPermissions.get(customerId);

    // If no one have the access permission
    if (currentOwnerId == -1 || currentOwnerId == ownerId) {
      // The one who requested has the only access to the customer's information
      accessPermissions.put(customerId, ownerId);

      customer = customers.get(customerId);
    }
    mutex.unlock();
    
    return customer;
  }

  /**
   * Release access permission to customer's information.
   * if it's owned by the administrator who owns access permission.
   *  @param customer - customer
   *  @param ownerId - Administrator ID
   */
  @Override
  public void release(final Customer customer, final Long ownerId) {
    mutex.lock();

    if (customer != null) {
      final long customerId = customer.getID();
      final long currentOwnerId = accessPermissions.get(customerId);

      if (currentOwnerId == ownerId) {
        accessPermissions.replace(customerId, (long) -1);
      }
    }

    mutex.unlock();
  }
}
