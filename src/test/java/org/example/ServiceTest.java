package org.example;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ServiceTest {
    Service service = new Service();

    @Test
    void addOrder() {
    }

    @Test
    void getAllOrders() {
        List<Order> allOrders = service.getAllOrders();
        Order order = allOrders.get(0);
        assertEquals(new Order(1, "Лимон", 2.0, 2.0), order);
    }

    @Test
    void deleteOrder() {
    }

    @Test
    void getMaxNumberOfOrder() {
    }
}