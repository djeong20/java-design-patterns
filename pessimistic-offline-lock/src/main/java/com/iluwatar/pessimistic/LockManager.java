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

  // Lock Manager Default Constructor
  public LockManager() {
    customers = new HashMap<>();           // key: customer id, value: customer info
    accessPermissions = new HashMap<>();  // key: customer id, value: owner id
    mutex = new ReentrantLock(true);
  }

  // Lock Manager Constructor using list of customer
  public LockManager(List<Customer> customerList) {
    customers = new HashMap<>();
    accessPermissions = new HashMap<>();
    mutex = new ReentrantLock(true);

    for (Customer new_customer : customerList) {
      insert(new_customer);
    }
  }

  public Customer getCustomer(Long customerId, Long ownerId) {
    // TODO: Need to acquire mutex lock
    mutex.lock();
    try {
      Long current_owner_id = accessPermissions.get(customerId);
//      System.out.println("Trying to access customer " + customerId + " by " + ownerId + "...");

      // if no owner
      if (current_owner_id == -1) {
        // the one who requested has only access to customer
//        System.out.println("Customer " + customerId + " is acquired by " + ownerId);
        accessPermissions.put(customerId, ownerId);
        mutex.unlock();

        return customers.get(customerId);
      }
      else {
//        System.out.println("Failed to access. Owned by " + current_owner_id);

        mutex.unlock();
        return null;
      }

    } catch (Exception e) {
      e.printStackTrace();
    }

    return null;
  }

  public void updateCustomerInfo(Customer customer, Long ownerId) {
    // if admin has access permission, update customer info
    long customer_id = customer.getID();
    long current_owner_id = accessPermissions.get(customer_id);

    if (current_owner_id == ownerId) {
      customers.replace(customer_id, customer);
    }
  }

  public void insert(Customer newCustomer) {
    newCustomer.setID(newCustomerId);
    customers.put(newCustomerId, newCustomer); // insert new customer
    accessPermissions.put(newCustomerId, (long) -1);
    newCustomerId++;
  }

  public void delete(Long customerId, Long ownerId) {
    Long current_owner_id = accessPermissions.get(customerId);

    if (current_owner_id.longValue() == ownerId.longValue()) {
      customers.remove(customerId);
      accessPermissions.remove(customerId);
    }
  }

  public void release(Long customerId, Long ownerId) {
    mutex.lock();
    try {
      Long current_owner_id = accessPermissions.get(customerId);
//      System.out.println("Attempt to release customer lock " + customerId);
//      System.out.println("Current owner: " + current_owner_id + " Requested admin : " + ownerId);

      if (current_owner_id.longValue() == ownerId.longValue()) {
//        System.out.println("Release customer " + customerId + " by " + current_owner_id);
        accessPermissions.replace(customerId, (long) -1);
      }
      else {
//        System.out.println("Failed: ID unmatched");
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
    mutex.unlock();
  }
}
