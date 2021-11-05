package com.iluwatar.pessimisticofflinelock;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockManager implements Manager {
  private final HashMap<Customer, Long> customers; // customer have owner (id) once someone calls customer
  private final Lock _mutex;

  public LockManager() {
    customers = new HashMap<Customer, Long>();
    _mutex = new ReentrantLock(true);
  }

  public Customer find(Customer customer, Long owner_id) {
    // TODO: Need to acquire mutex lock
    _mutex.lock();
    try {
      Long current_owner_id = customers.get(customer);

      // if no owner
      if (current_owner_id == -1) {
        // the one who requested has only access to customer
        customers.replace(customer, owner_id);
        _mutex.unlock();
        return customer;
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public void insert(Customer customer, Long owner_id) {
    Long current_owner_id = customers.get(customer);

    if (current_owner_id == null) {
      customers.put(customer, owner_id);
    }
  }

  public void update(Customer customer, Long owner_id) {
    Long current_owner_id = customers.get(customer);

    if (current_owner_id == owner_id) {
      customers.replace(customer, owner_id);
    }
  }

  public void delete(Customer customer, Long owner_id) {
    Long current_owner_id = customers.get(customer);

    if (current_owner_id == owner_id) {
      customers.remove(customer);
    }
  }

  public void release(Customer customer, Long owner_id) {
    _mutex.lock();
    try {
      Long current_owner_id = customers.get(customer);

      if (current_owner_id == owner_id) {
        // -1 indicates there's no owner
        customers.replace(customer, (long) -1);
      }

      _mutex.unlock();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
