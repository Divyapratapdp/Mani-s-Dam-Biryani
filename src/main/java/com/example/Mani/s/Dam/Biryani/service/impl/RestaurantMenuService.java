package com.example.Mani.s.Dam.Biryani.service.impl;

import com.example.Mani.s.Dam.Biryani.model.MenuResponse;
import com.example.Mani.s.Dam.Biryani.model.MenuEntity;
import com.example.Mani.s.Dam.Biryani.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RestaurantMenuService {
    @Autowired
    MenuRepository menuRepository;

    private Set<String> findUniqueCatagory(List<MenuEntity> menu) { //To find all the unique catagory type
        return menu.stream().map(MenuEntity::getCategory).collect(Collectors.toSet());
    }

    private Map<String, List<MenuResponse>> addItemsToMenu(List<MenuEntity> menu, Set<String> catagory) {//adding item catagorewise
       HashMap<String, List<MenuResponse>> output = new HashMap<>();
      //  return menu.stream().collect(Collectors.groupingBy(MenuResponse::getCategory));

        for (String it : catagory) {
            List<MenuResponse> catagoryItems = new ArrayList<>();
            for (MenuEntity menuEntity : menu) {
                if (it.equals(menuEntity.getCategory())) {
                    MenuResponse item = new MenuResponse();
                    item.setItemName(menuEntity.getItemName());
                    item.setPrice(menuEntity.getPrice());
                    catagoryItems.add(item);
                }
            }
            output.put(it, catagoryItems);
            //item=null;
        }
        return output;
    }

    public Map<String, List<MenuResponse>> showMenu() {
        List<MenuEntity> menu = (List<MenuEntity>) menuRepository.findAll();
       // ArrayList<String> catagorys = findUniqueCatagory(menu);
        Set<String>catagorys=findUniqueCatagory(menu);
        return addItemsToMenu(menu, catagorys);
    }

    public MenuEntity addItems(MenuEntity menuEntity) {
        return menuRepository.save(menuEntity);
    }
}
