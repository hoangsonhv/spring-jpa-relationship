package com.example.demospring.sell.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ProductOrderId implements Serializable {
    @Column(name = "product_id")
    private Integer productId;
    @Column(name = "order_id")
    private int orderId;

}
