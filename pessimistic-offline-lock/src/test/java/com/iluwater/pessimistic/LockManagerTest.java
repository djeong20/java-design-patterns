package com.iluwater.pessimistic;

import com.iluwatar.pessimistic.Customer;
import com.iluwatar.pessimistic.LockManager;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNull;

class LockManagerTest {

    @Test
    void testInsert() {
        var lockManager = new LockManager();
        assertEquals(lockManager.total, 0);

        lockManager.insert(new Customer("Timothy Cole"));
        assertEquals(lockManager.total, 1);

        lockManager.insert(new Customer("Jake Hill"));
        assertEquals(lockManager.total, 2);

        lockManager.insert(new Customer("Ben Webster"));
        assertEquals(lockManager.total, 3);
    }

    @Test
    void testDelete() {
        var lockManager = new LockManager();
        assertEquals(lockManager.total, 0);

        lockManager.insert(new Customer("Timothy Cole"));
        lockManager.insert(new Customer("Jake Hill"));
        lockManager.insert(new Customer("Ben Webster"));
        assertEquals(lockManager.total, 3);

        long martinID = 100L;
        long davidId = 102L;

        lockManager.getCustomer(1L, martinID);
        lockManager.delete(1L, martinID);
        assertEquals(lockManager.total, 2);

        lockManager.delete(0L, davidId);
        assertEquals(lockManager.total, 2);

        lockManager.getCustomer(0L, davidId);
        lockManager.delete(0L, davidId);
        assertEquals(lockManager.total, 1);
    }

    @Test
    void testLock() {
        var lockManager = new LockManager();
        lockManager.insert(new Customer("Timothy Cole"));
        lockManager.insert(new Customer("Jake Hill"));
        lockManager.insert(new Customer("Ben Webster"));

        long martinId = 100L;
        long davidId = 102L;

        Customer martinCustomer = lockManager.getCustomer(1L, martinId);
        Customer davidCustomer = lockManager.getCustomer(1L, davidId);

        assertEquals(martinCustomer.getName(), "Jake Hill");
        assertNull(davidCustomer);
    }

    @Test
    void testRelease() {
        var lockManager = new LockManager();
        lockManager.insert(new Customer("Timothy Cole"));
        lockManager.insert(new Customer("Jake Hill"));
        lockManager.insert(new Customer("Ben Webster"));

        long martinId = 100L;
        long davidId = 102L;

        Customer martinCustomer = null;
        Customer davidCustomer = null;

        martinCustomer = lockManager.getCustomer(1L, martinId);
        davidCustomer = lockManager.getCustomer(1L, davidId);

        assertEquals(martinCustomer.getName(), "Jake Hill");
        assertNull(davidCustomer);

        lockManager.release(martinCustomer, martinId);

        davidCustomer = lockManager.getCustomer(1L, davidId);
        martinCustomer = lockManager.getCustomer(1L, martinId);

        assertNull(martinCustomer);
        assertEquals(davidCustomer.getName(), "Jake Hill");
    }
}
