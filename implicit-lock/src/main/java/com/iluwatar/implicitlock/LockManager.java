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
  private final transient Set<Client> lockedClients; // List of locked clients
  private final transient Lock mutex; // Mutex lock

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
  public boolean lock(Client client) {
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
  public boolean release(Client client) {
    boolean result;
    mutex.lock();

    try {
      if (lockedClients.contains(client)) {
        lockedClients.remove(client);
        result = true;
      } else {
        result = false;
      }
    } finally {
      mutex.unlock();
    }

    return result;
  }

  /**
   *  Check whether client is locked or not.
   *  @param client - Customer
   *  @return result - boolean
   */
  public boolean contains(Client client) {
    return lockedClients.contains(client);
  }
}
