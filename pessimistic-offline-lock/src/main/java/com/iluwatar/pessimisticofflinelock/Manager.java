package com.iluwatar.pessimisticofflinelock;

import java.util.ArrayList;

public interface Manager {
  public Customer find(Long id);

  public void insert(Customer obj);

  public void update(Customer obj);

  public void delete(Customer obj);
}
