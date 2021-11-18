package com.iluwatar.pessimistic;

public interface Manager {
  public void insert(Customer newCustomer);

  public void delete(Long customerId, Long ownerId);

  public Customer getCustomer(Long customerId, Long ownerId);

  public void release(Customer customer, Long ownerId);
}
