package com.example.Mani.s.Dam.Biryani.model;

import lombok.Data;

import java.util.List;

@Data
public class BillResponse {

    List<BillItem> billItem;
    private int Total;

}
