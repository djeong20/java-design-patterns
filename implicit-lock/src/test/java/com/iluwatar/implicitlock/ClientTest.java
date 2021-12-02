package com.iluwatar.implicitlock;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Client test
 */
public class ClientTest {
    /**
     * This test is used to confirm that client ID is set correctly when ID is not initialized
     */
    @Test
    void testClientId1() {
        final var client = new Client();
        client.setClientId(1203L);
        assertEquals(client.getClientId(), 1203L, "Client ID does not match");
    }

    /**
     * This test is used to confirm that client ID is set correctly when ID is initialized
     */
    @Test
    void testClientId2() {
        final var client = new Client(1000L);
        client.setClientId(1203L);
        assertEquals(client.getClientId(), 1203L, "Client ID does not match");
    }
}
