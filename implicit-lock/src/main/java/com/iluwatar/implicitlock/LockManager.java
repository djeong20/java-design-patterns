package com.iluwatar.implicitlock;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * This class manages customers information access permission.
 * @author Donghyeon Jeong
 */
public class LockManager {
  private Set<Client> lockedClients;
  private Lock mutex;

  /**
   * Lock Manager Default Constructor.
   * Initialize private variables.
   */
  public LockManager() {
    lockedClients = new HashSet<>();
    mutex = new ReentrantLock(true);
  }

  /**
   *  Lock client access if it's not owned by any other administrator.
   *  If lock is success, return true.
   *  @param client - Customer
   *  @return result - boolean
   */
  boolean lock(Client client) {
    boolean result;

    mutex.lock();

    try {
      if (lockedClients.contains(client)) {
        result = false;
      } else {
        lockedClients.add(client);
        result = true;
      }
    } finally {
      mutex.unlock();
    }

    return result;
  }

  /**
   *  Release client access if it's locked.
   *  If release is success, return true.
   *  @param client - Customer
   *  @return result - boolean
   */
  boolean release(Client client) {
    boolean result;

    mutex.lock();

    try {
      if (lockedClients.contains(client)) {
        result = false;
      } else {
        lockedClients.remove(client);
        result = true;
      }
    } finally {
      mutex.unlock();
    }

    return result;
  }
}
