package com.iluwatar.pessimisticofflinelock;

public interface Manager {
  public Customer find(Long id);

  public void insert(Customer obj);

  public void update(Customer obj);

  public void delete(Customer obj);
}
