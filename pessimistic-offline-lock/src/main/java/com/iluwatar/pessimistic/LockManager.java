package com.iluwatar.pessimistic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockManager implements Manager {
  private Map<Long, Customer> customers; // customer have owner (id) once someone calls customer
  private Map<Long, Long> accessPermissions;
  private Lock mutex;
  private long newCustomerId = 0;
  public int total = 0;

  /**
   * Lock Manager Default Constructor.
   */
  public LockManager() {
    customers = new HashMap<>();           // key: customer id, value: customer info
    accessPermissions = new HashMap<>();  // key: customer id, value: owner id
    mutex = new ReentrantLock(true);
  }

  /**
   * Lock Manager Constructor using list of customers.
   */
  public LockManager(List<Customer> customerList) {
    customers = new HashMap<>();
    accessPermissions = new HashMap<>();
    mutex = new ReentrantLock(true);

    for (Customer newCustomer : customerList) {
      insert(newCustomer);
    }
  }

  /**
   * Insert new customer information.
   */
  public void insert(Customer newCustomer) {
    newCustomer.setID(newCustomerId);
    customers.put(newCustomerId, newCustomer);
    accessPermissions.put(newCustomerId, (long) -1);
    newCustomerId++;
    total++;
  }

  /**
   * Delete current customer's information.
   */
  public void delete(Long customerId, Long ownerId) {
    Long currentOwnerId = accessPermissions.get(customerId);

    if (currentOwnerId.longValue() == ownerId.longValue()) {
      customers.remove(customerId);
      accessPermissions.remove(customerId);
      total--;
    }
  }

  /**
   * Returns customer if it's not owned by any administrator.
   */
  public Customer getCustomer(Long customerId, Long ownerId) {
    // TODO: Need to acquire mutex lock
    mutex.lock();
    try {
      Long currentOwnerId = accessPermissions.get(customerId);

      // If no one have the access permission
      if (currentOwnerId == -1 || currentOwnerId == ownerId) {
        // The one who requested has the only access to the customer's information
        accessPermissions.put(customerId, ownerId);
        mutex.unlock();

        return customers.get(customerId);
      } else {
        mutex.unlock();
        return null;
      }

    } catch (Exception e) {
      e.printStackTrace();
    }

    return null;
  }

  /**
   * Release access permission to customer's information.
   * if it's owned by the administrator who owns access permission.
   */
  public void release(Customer customer, Long ownerId) {
    mutex.lock();
    try {
      if (customer != null) {
        long customerId = customer.getID();

        Long currentOwnerId = accessPermissions.get(customerId);

        if (currentOwnerId.longValue() == ownerId.longValue()) {
          accessPermissions.replace(customerId, (long) -1);
          customer = null;
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    mutex.unlock();
  }
}
