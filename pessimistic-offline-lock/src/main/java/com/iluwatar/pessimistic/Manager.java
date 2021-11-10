package com.iluwatar.pessimistic;

import java.util.ArrayList;

public interface Manager {

  public Customer getCustomer(Long customerId, Long ownerId);

  public void updateCustomerInfo(Customer customer, Long ownerId);

  public void insert(Customer newCustomer);

  public void delete(Long customerId, Long ownerId);
}
