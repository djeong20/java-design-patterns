package com.iluwater.pessimistic;

import com.iluwatar.pessimistic.Customer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
/**
 * Test case for Customer class
 */
class CustomerTest {

    /**
     * This test is used to confirm that customer ID is correctly set
     */
    @Test
    void testCustomerID() {
        final var customer = new Customer();
        customer.setID(10L);
        assertEquals(customer.getID(), 10L, "Customer ID does not match");
    }

    /**
     * This test is used to confirm that customer name is correctly set
     */
    @Test
    void testCustomerName() {
        final var customer = new Customer();
        customer.setName("George Washington");
        assertEquals(customer.getName(), "George Washington", "Customer name does not match");
    }
}
