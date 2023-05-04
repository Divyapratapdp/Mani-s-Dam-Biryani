package com.example.Mani.s.Dam.Biryani.repository;

import com.example.Mani.s.Dam.Biryani.model.OrderIdEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface OrderRepository extends CrudRepository<OrderIdEntity,Integer> {

    OrderIdEntity findById(int orderId);

}
