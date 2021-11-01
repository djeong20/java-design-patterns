package com.iluwatar.pessimisticofflinelock;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockManager implements Manager {
  private final HashMap<Long, Customer> customers;
  private final Lock _mutex;

  public LockManager() {
    customers = new HashMap<Long, Customer>();
    _mutex = new ReentrantLock(true);
  }

  public Customer find(Long id) {
    // TODO: Need to acquire mutex lock
    _mutex.lock();
    try {
      Customer customer = null;

      if (!customer.getLock()) {
        customer = customers.get(id);
        customer.setLock(true);
      }

      _mutex.unlock();
      return customer;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public void insert(Customer obj) {
    _mutex.lock();
    try {
      Customer customer = null;

      if (!customer.getLock()) {
        customer = customers.put(obj.getID(), obj);
        customer.setLock(true);
      }


    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void update(Customer obj) {
    _mutex.lock();
    try {
      customers.replace(obj.getID(), obj);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void delete(Customer obj) {
    _mutex.lock();
    try {
      customers.remove(obj.getID());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void release_lock() {
    _mutex.unlock();
  }
}
