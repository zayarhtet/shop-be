package com.zay.shop.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "order_items")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler","order" })
public class OrderItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Integer id;

    @Getter @Setter
    private Integer quantity;

    @Getter @Setter
    private Double total;

    @ManyToOne
    @JoinColumn(name="order_id", nullable=false)
    @Getter @Setter
    private Order order;

    @OneToOne
    @JoinColumn(name = "product_id")
    @Getter @Setter
    private Product product;

    public OrderItems() {
    }

    public OrderItems(Integer quantity, Double total, Order order, Product product) {
        this.quantity = quantity;
        this.total = total;
        this.order = order;
        this.product = product;
    }
}
