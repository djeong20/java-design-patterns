package com.iluwatar.implicitlock;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Lock Manager Test
 */
class FrameworkTest {
    private final static long CLIENT1_ID = 100L; // Client 1 ID
    private final static long CLIENT2_ID = 100L; // Client 2 ID
    private final static long CLIENT3_ID = 143L; // Client 3 ID
    private final static Client CLIENT1 = new Client(CLIENT1_ID); // Client 1
    private final static Client CLIENT2 = new Client(CLIENT2_ID); // Client 2
    private final static Client CLIENT3 = new Client(CLIENT3_ID); // Client 3

    /**
     * This test is used to confirm a client is added if client id is not occupied
     */
    @Test
    void addClientSuccess() {
        var framework = new Framework();
        boolean result = framework.addClient(CLIENT1);
        assertTrue(result, "Fail to add client to a client list");
    }

    /**
     * This test is used to confirm framework filters new client when id is already in use
     */
    @Test
    void addClientFail() {
        var framework = new Framework();
        framework.addClient(CLIENT1);
        boolean result = framework.addClient(CLIENT2);
        assertFalse(result, "Added client with duplicate id");
    }

    /**
     * This test is used to confirm a client is added if client id is not occupied
     * and filters clients with duplicate ids
     */
    @Test
    void addClientTest() {
        var framework = new Framework();
        framework.addClient(CLIENT1);
        framework.addClient(CLIENT2); // This client will not be added since id 100L is occupied
        framework.addClient(CLIENT3);
        assertEquals(framework.total, 2, "Total number of clients do not match");
    }

    /**
     * This test is used to confirm an unlocked client is can be deleted
     */
    @Test
    void deleteClientSuccess() {
        var framework = new Framework();
        framework.addClient(CLIENT1);
        boolean result = framework.deleteClients(CLIENT1);
        assertTrue(result, "Fail to delete client from a client list");
    }

    /**
     * This test is used to confirm a client cannot be deleted when it's locked
     */
    @Test
    void deleteLockedClientTest() {
        var framework = new Framework();
        framework.addClient(CLIENT1);
        framework.loadClient(CLIENT1.getClientId()); // Automatically locks the client
        boolean result = framework.deleteClients(CLIENT1);
        assertFalse(result, "Deleted client when it's locked");
    }

    /**
     * This test is used to confirm a client is deleted
     * with other client with duplicate id
     */
    @Test
    void deleteClientTest() {
        var framework = new Framework();
        framework.addClient(CLIENT1);
        framework.deleteClients(CLIENT2); // This client has same id with CLIENT1
        assertEquals(framework.total, 1, "Total number of clients do not match");
    }

    /**
     * This test is used to confirm a client cannot be deleted when it's not in list
     */
    @Test
    void deleteNoClientFail() {
        var framework = new Framework();
        framework.addClient(CLIENT1);
        boolean result = framework.deleteClients(CLIENT3);
        assertFalse(result, "Deleted invalid client");
    }

    /**
     * This test is used to confirm a client can be loaded when it's unlocked
     */
    @Test
    void loadClientSuccess() {
        var framework = new Framework();
        framework.addClient(CLIENT1);
        Client client = framework.loadClient(CLIENT1_ID);
        assertEquals(CLIENT1, client, "Fail to load a client");
    }

    /**
     * This test is used to confirm a client cannot be loaded when it's locked
     */
    @Test
    void loadClientFail() {
        var framework = new Framework();
        framework.addClient(CLIENT1);
        framework.loadClient(CLIENT1_ID);
        Client client = framework.loadClient(CLIENT1_ID);
        assertNull(client, "Loaded a locked client");
    }

    /**
     * This test is used to confirm a client is automatically locked when it's loaded
     */
    @Test
    void loadClientLocks() {
        var framework = new Framework();
        framework.addClient(CLIENT1);
        Client client = framework.loadClient(CLIENT1_ID);
        boolean result = framework.deleteClients(client);
        assertFalse(result, "Client is not locked");
    }

    /**
     * This test is used to confirm a locked client is unlocked
     */
    @Test
    void releaseClientSuccess() {
        var framework = new Framework();
        framework.addClient(CLIENT1);
        Client client = framework.loadClient(CLIENT1_ID);
        boolean result = framework.releaseClient(client);
        assertTrue(result, "Fail to release a client");
    }

    /**
     * This test is used to confirm an unlocked client cannot be unlocked
     */
    @Test
    void releaseClientFail() {
        var framework = new Framework();
        framework.addClient(CLIENT1);
        boolean result = framework.releaseClient(CLIENT1);
        assertFalse(result, "Released an unlocked client");
    }

    /**
     * This test is used to confirm an unlocked client cannot be unlocked
     */
    @Test
    void releaseNonExistingClient() {
        var framework = new Framework();
        boolean result = framework.releaseClient(CLIENT1);
        assertFalse(result, "Released a non-existing client");
    }
}
