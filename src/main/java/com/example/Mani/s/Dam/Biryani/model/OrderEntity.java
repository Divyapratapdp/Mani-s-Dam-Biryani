package com.example.Mani.s.Dam.Biryani.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Data
@Table(name="OrderDetails")
public class OrderEntity {
    @Id
    private String itemName;
    @NotNull
    private Integer Quantity;



}
