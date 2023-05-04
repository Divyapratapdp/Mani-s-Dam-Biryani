package com.example.Mani.s.Dam.Biryani.repository;

import com.example.Mani.s.Dam.Biryani.model.MenuEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends CrudRepository<MenuEntity, String> {
    MenuEntity findByItemName(String itemName);
}
