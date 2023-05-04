package com.example.Mani.s.Dam.Biryani.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Data
@Table(name="OrderId")
public class OrderIdEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer OrderId;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "Order Id")
    private List<OrderEntity> orderEntity=new ArrayList<>();

}
