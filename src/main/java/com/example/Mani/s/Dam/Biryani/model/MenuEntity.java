package com.example.Mani.s.Dam.Biryani.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "menu")
@Data
public class MenuEntity {
    @Id
    private String itemName;

    private String category;

    @Column(name = "Price")
    private Integer price;

}
