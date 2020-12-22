package com.zay.shop.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler","customer"})
@Table(name = "`order`")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Integer id;

    @Getter @Setter
    @Column(name = "order_no")
    private Long orderNo;

    @Getter @Setter
    @Column(name = "order_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date orderDate;

    @Getter @Setter
    private String status;

    @ManyToOne
    @JoinColumn(name="customer_id")
    @Getter @Setter
    private Customer customer;

    @OneToMany(mappedBy = "order")
    @Getter @Setter
    @JsonIgnoreProperties({"order"})
    private Set<OrderItems> orderItems=new HashSet<>();

    public Order() {
    }

    public Order(Long orderNo, String status, Customer customer, Set<OrderItems> orderItems) {
        this.orderNo = orderNo;
        this.status = status;
        this.customer = customer;
        this.orderItems = orderItems;
    }

    public Order(Long orderNo, String status, Customer customer) {
        this.orderNo = orderNo;
        this.status = status;
        this.customer = customer;
    }

    public Order(String status) {
        this.status = status;
    }
}
