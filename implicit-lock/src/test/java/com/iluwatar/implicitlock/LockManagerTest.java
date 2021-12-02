package com.iluwatar.implicitlock;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * Lock Manager Test
 */
class LockManagerTest {
    private final static Client CLIENT = new Client(100L); // Client

    /**
     * This test is used to confirm a client is locked if it's not in use
     */
    @Test
    void lockSuccess() {
        final var lockManager = new LockManager();
        boolean result = lockManager.lock(CLIENT);
        assertTrue(result, "Client is not locked");
    }

    /**
     * This test is used to confirm a client cannot be locked if it's in use
     */
    @Test
    void lockFail() {
        final var lockManager = new LockManager();
        lockManager.lock(CLIENT);
        // Another user tries to lock the same client
        boolean result = lockManager.lock(CLIENT);
        assertFalse(result, "Client is double locked");
    }

    /**
     * This test is used to confirm a client is release if it's locked
     */
    @Test
    void releaseSuccess() {
        final var lockManager = new LockManager();
        lockManager.lock(CLIENT);
        boolean result = lockManager.release(CLIENT);
        assertTrue(result, "Client is not released");
    }

    /**
     * This test is used to confirm a client cannot be release if it's not locked
     */
    @Test
    void releaseFail() {
        final var lockManager = new LockManager();
        boolean result = lockManager.release(CLIENT);
        assertFalse(result, "Client is released when it's not locked");
    }
}
