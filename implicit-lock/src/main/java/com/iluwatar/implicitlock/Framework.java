package com.iluwatar.implicitlock;

import java.util.HashSet;
import java.util.Set;

/**
 * Framework class manages list of clients and lock manager.
 * This is used to guarantee locking client everywhere when is locked anywhere.
 * It also prevents having clients with duplicate ids.
 * @author Donghyeon Jeong
 */
public class Framework {
  private final transient LockManager lockManager; // Lock manager
  private final transient Set<Client> clients; // List of clients

  /**
   * Default constructor for Framework class.
   */
  public Framework() {
    lockManager = new LockManager();
    clients = new HashSet<>();
  }

  /**
   * Add client to the list of clients.
   * If client id already exists, don't add the client and return false.
   * If not, add client and return true.
   * @param client - Client
   */
  public boolean addClient(Client client) {
    boolean result = true;
    long clientId = client.getClientId();

    for (Client currentClient : clients) {
      if (clientId == currentClient.getClientId()) {
        result = false;
        break;
      }
    }

    if (result) {
      clients.add(client);
    }

    return result;
  }

  /**
   * Delete client from the list of clients.
   * If client exists in the list and unlocked, delete from list and return true.
   * If not, return false.
   * @param client - New client
   * @return result - boolean
   */
  public boolean deleteClients(Client client) {
    boolean result = false;

    if (clients.contains(client) && !lockManager.contains(client)) {
      // Check if client is locked
      clients.remove(client);
      result = true;
    }

    return result;
  }

  /**
   * Get client with the request of transaction
   * If client exists in the list and is not locked, return client
   * If not, return null.
   * @param clientId - Client ID
   * @return result - Client
   */
  public Client loadCustomer(long clientId) {
    Client result = null;

    for (Client client : clients) {
      // Framework calls lock manager and lock client
      if (clientId == client.getClientId() && lockManager.lock(client)) {
        result = client;
      }
    }

    return result;
  }
}
