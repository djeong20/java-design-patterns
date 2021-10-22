package com.iluwatar.pessimisticofflinelock;

public class LockManager implements Manager {
    private Manager impl;

    public LockManager(Manager impl) {
        this.impl = impl;
    }

    public Customer find(Long id) {
        // TODO: Need to acquire mutex lock
        return impl.find(id);
    }

    public void insert(Customer obj) {
        impl.insert(obj);
    }

    public void update(Customer obj) {
        impl.update(obj);
    }

    public void delete(Customer obj) {
        impl.delete(obj);
    }
}
