package com.iluwater.pessimistic;

import com.iluwatar.pessimistic.Customer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
/**
 * Test case for Customer class
 */
class CustomerTest {

    @Test
    void testCustomer() {
        var customer = new Customer();
        customer.setID(10L);
        customer.setName("George Washington");
        assertEquals(customer.getID(), 10L);
        assertEquals(customer.getName(), "George Washington");
    }
}
