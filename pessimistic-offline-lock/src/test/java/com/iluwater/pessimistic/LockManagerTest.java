package com.iluwater.pessimistic;

import com.iluwatar.pessimistic.Customer;
import com.iluwatar.pessimistic.LockManager;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Lock Manager Test
 */
class LockManagerTest {
    private final static String TIMOTHY_COLE = "Timothy Cole"; // Customer name
    private final static String JAKE_HILL = "Jake Hill"; // Customer name
    private final static String BEN_WEBSTER = "Ben Webster"; // Customer name
    private final static long MARTIN_ID = 100L; // Admin ID
    private final static long DAVID_ID = 102L; // Admin ID

    /**
     * This test is used to confirm that customer information is inserted.
     */
    @Test
    void testInsert() {
        final var lockManager = new LockManager();
        lockManager.insert(new Customer(TIMOTHY_COLE));
        lockManager.insert(new Customer(JAKE_HILL));
        lockManager.insert(new Customer(BEN_WEBSTER));
        assertEquals(3, lockManager.total, "Number of customers does not match");
    }

    /**
     * This test is used to confirm that customer information is deleted.
     */
    @Test
    void testDeleteSuccess() {
        final var lockManager = new LockManager();
        lockManager.insert(new Customer(TIMOTHY_COLE));
        lockManager.insert(new Customer(JAKE_HILL));
        lockManager.insert(new Customer(BEN_WEBSTER));

        lockManager.getCustomer(1L, MARTIN_ID);
        lockManager.delete(1L, MARTIN_ID);

        lockManager.getCustomer(0L, DAVID_ID);
        lockManager.delete(0L, DAVID_ID);

        assertEquals(1, lockManager.total, "Number of customers does not match");
    }

    /**
     * This test is used to confirm that customer information is not deleted.
     */
    @Test
    void testDeleteFail() {
        final var lockManager = new LockManager();
        lockManager.insert(new Customer(TIMOTHY_COLE));
        lockManager.insert(new Customer(JAKE_HILL));
        lockManager.insert(new Customer(BEN_WEBSTER));

        lockManager.getCustomer(1L, MARTIN_ID);
        lockManager.delete(1L, DAVID_ID);

        lockManager.getCustomer(0L, DAVID_ID);
        lockManager.delete(0L, MARTIN_ID);

        assertEquals(3, lockManager.total, "Number of customers does not match");
    }

    /**
     * This test is used to confirm that customer information is locked.
     */
    @Test
    void testLockSuccess() {
        final var lockManager = new LockManager();
        lockManager.insert(new Customer(TIMOTHY_COLE));
        lockManager.insert(new Customer(JAKE_HILL));
        lockManager.insert(new Customer(BEN_WEBSTER));

        lockManager.getCustomer(1L, MARTIN_ID);
        assertNull(lockManager.getCustomer(1L, DAVID_ID), "Customer information is accessible");
    }

    /**
     * This test is used to confirm that customer information is released.
     */
    @Test
    void testReleaseSuccess() {
        final var lockManager = new LockManager();
        lockManager.insert(new Customer(TIMOTHY_COLE));
        lockManager.insert(new Customer(JAKE_HILL));
        lockManager.insert(new Customer(BEN_WEBSTER));

        final Customer martinCustomer = lockManager.getCustomer(1L, MARTIN_ID);

        lockManager.release(martinCustomer, MARTIN_ID);

        final Customer davidCustomer = lockManager.getCustomer(1L, DAVID_ID);

        assertEquals(martinCustomer, davidCustomer, "Customer's information does not match");
    }

    /**
     * This test is used to confirm that customer information is not released.
     */
    @Test
    void testReleaseFail() {
        final var lockManager = new LockManager();
        lockManager.insert(new Customer(TIMOTHY_COLE));
        lockManager.insert(new Customer(JAKE_HILL));
        lockManager.insert(new Customer(BEN_WEBSTER));

        final Customer martinCustomer = lockManager.getCustomer(1L, MARTIN_ID);

        lockManager.release(martinCustomer, DAVID_ID);

        final Customer davidCustomer = lockManager.getCustomer(1L, DAVID_ID);

        assertNotEquals(martinCustomer, davidCustomer, "Customer's information does match");
    }
}
