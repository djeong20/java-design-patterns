package com.iluwatar.pessimisticofflinelock;

import java.util.ArrayList;

public interface Manager {

  public Customer find(Customer customer, Long owner_id);

  public void insert(Customer customer, Long owner_id);

  public void update(Customer customer, Long owner_id);

  public void delete(Customer customer, Long owner_id);
}
