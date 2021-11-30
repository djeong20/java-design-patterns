package com.iluwatar.implicitlock;

/**
 * This class contains client information.
 * @author Donghyeon Jeong
 */
public class Client {
  private long clientId; // Client ID

  /**
   * Default Constructor for Client class.
   */
  public Client() {}

  /**
   * Client Constructor by assigning Client ID.
   * @param id - client ID
   */
  public Client(long id) {
    this.clientId = id;
  }

  /**
   * Sets Client ID.
   * @param clientId - client ID
   */
  public void setClientId(long clientId) {
    this.clientId = clientId;
  }

  /**
   * Returns Client ID.
   * @return clientId - Client ID
   */
  public long getClientId() {
    return clientId;
  }
}
