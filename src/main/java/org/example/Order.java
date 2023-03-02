package org.example;

import java.util.Objects;

public class Order {

    private int number;
    private String name;
    private double quality;
    private double price;
    private double amount;
    private boolean deleted;

    public Order() {
    }

    public Order(int number, String name, double quality, double price) {
        this.number = number;
        this.name = name;
        this.quality = quality;
        this.price = price;
        this.amount = quality * price;
        this.deleted = false;
    }


    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getQuality() {
        return quality;
    }

    public void setQuality(double quality) {
        this.quality = quality;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return
                "number: " + number +
                        ", name: " + name +
                        ", quality: " + quality +
                        ", price: " + price +
                        ", amount: " + amount +
                        ", deleted: " + deleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return number == order.number
                && Double.compare(order.quality, quality) == 0
                && Double.compare(order.price, price) == 0
                && Double.compare(order.amount, amount) == 0
                && deleted == order.deleted
                && name.equals(order.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, name, quality, price, amount, deleted);
    }
}
