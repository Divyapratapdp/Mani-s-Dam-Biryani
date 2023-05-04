package com.example.Mani.s.Dam.Biryani.service;

import com.example.Mani.s.Dam.Biryani.model.BillResponse;
import com.example.Mani.s.Dam.Biryani.model.OrderIdEntity;
import com.example.Mani.s.Dam.Biryani.model.OrderRequest;
import org.springframework.web.bind.annotation.RequestBody;

public interface OrderService {

    OrderIdEntity orderItem(OrderRequest orderIdEntity);

     BillResponse getBill(Integer orderId);

    public void addItemQuantity(@RequestBody OrderIdEntity orderIdEntity);
}
