package com.example.Mani.s.Dam.Biryani.controller;

import com.example.Mani.s.Dam.Biryani.model.MenuResponse;
import com.example.Mani.s.Dam.Biryani.model.MenuEntity;
import com.example.Mani.s.Dam.Biryani.service.impl.RestaurantMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Menu")
public class MenuController {
    @Autowired
    RestaurantMenuService restaurantMenuService;

    @GetMapping("/show_menu")
    public Map<String, List<MenuResponse>> showMenu() {
        return restaurantMenuService.showMenu();
    }

    @PostMapping("/add_items")
    public MenuEntity addItems(@RequestBody MenuEntity menuEntity) {
        return restaurantMenuService.addItems(menuEntity);
    }
}
