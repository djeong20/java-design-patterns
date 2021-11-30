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
  private LockManager lockManager;
  private Set<Client> clients;

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
   * If client exists in the list and is not locked, delete from list and return true.
   * If not, return false.
   * @param client - New client
   * @return result - boolean
   */
  public boolean deleteClients(Client client) {
    boolean result = false;
    long clientId = client.getClientId();

    for (Client currentClient : clients) {
      if (clientId == currentClient.getClientId()) {
        // TODO: Check if client is locked
        result = true;
        break;
      }
    }

    if (result) {
      clients.remove(client);
    }

    return result;
  }

}
