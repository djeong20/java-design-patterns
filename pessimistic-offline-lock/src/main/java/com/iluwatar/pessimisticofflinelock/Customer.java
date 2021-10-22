package com.iluwatar.pessimisticofflinelock;

public class Customer {
    private Long id;

    public Customer(Long id) {
        this.id = id;
    }

    public Customer() {}

    public Long getID() {
        return id;
    }

    public void setID(Long id) {
        // Assert.notNull("Cannot set a null ID", ID);
        this.id = id;
    }
}
