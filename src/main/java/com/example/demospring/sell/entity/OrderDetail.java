package com.example.demospring.sell.entity;

import com.example.demospring.manytomany.entity.Grade;
import com.example.demospring.manytomany.entity.StudentGradeId;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "orders_details")
public class OrderDetail {
    @EmbeddedId
    private ProductOrderId id;

//    private int quantity;
//    private double unitPrice;

    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private Product product;


}
