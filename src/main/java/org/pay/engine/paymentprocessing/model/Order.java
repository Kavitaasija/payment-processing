package org.pay.engine.paymentprocessing.model;

import java.util.List;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Order {
    private String id;
    private String description;
    private List<OrderItem> items;
    
    public Order() {
    }
    
    public Order(String id, String description, List<OrderItem> items) {
        this.id = id;
        this.description = description;
        this.items = items;
    }

    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) &&
                Objects.equals(description, order.description) &&
                Objects.equals(items, order.items);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id, description, items);
    }
    
    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", description='" + description + '\'' +
                ", items=" + items +
                '}';
    }
    
    /**
     * Represents an item in an order
     */
    public static class OrderItem {
        private String id;
        private String name;
        private int quantity;
        private double unitPrice;
        
        public OrderItem() {
        }
        
        public OrderItem(String id, String name, int quantity, double unitPrice) {
            this.id = id;
            this.name = name;
            this.quantity = quantity;
            this.unitPrice = unitPrice;
        }
        
        public String getId() {
            return id;
        }
        
        public void setId(String id) {
            this.id = id;
        }
        
        public String getName() {
            return name;
        }
        
        public void setName(String name) {
            this.name = name;
        }
        
        public int getQuantity() {
            return quantity;
        }
        
        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
        
        public double getUnitPrice() {
            return unitPrice;
        }
        
        public void setUnitPrice(double unitPrice) {
            this.unitPrice = unitPrice;
        }
        
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            OrderItem orderItem = (OrderItem) o;
            return quantity == orderItem.quantity &&
                    Double.compare(orderItem.unitPrice, unitPrice) == 0 &&
                    Objects.equals(id, orderItem.id) &&
                    Objects.equals(name, orderItem.name);
        }
        
        @Override
        public int hashCode() {
            return Objects.hash(id, name, quantity, unitPrice);
        }
        
        @Override
        public String toString() {
            return "OrderItem{" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    ", quantity=" + quantity +
                    ", unitPrice=" + unitPrice +
                    '}';
        }
    }
}