package com.example.Mani.s.Dam.Biryani.service.impl;

import com.example.Mani.s.Dam.Biryani.model.BillItem;
import com.example.Mani.s.Dam.Biryani.model.BillResponse;
import com.example.Mani.s.Dam.Biryani.model.ItemRequest;
import com.example.Mani.s.Dam.Biryani.model.MenuEntity;
import com.example.Mani.s.Dam.Biryani.model.OrderEntity;
import com.example.Mani.s.Dam.Biryani.model.OrderIdEntity;
import com.example.Mani.s.Dam.Biryani.model.OrderRequest;
import com.example.Mani.s.Dam.Biryani.repository.MenuRepository;
import com.example.Mani.s.Dam.Biryani.repository.OrderRepository;
import com.example.Mani.s.Dam.Biryani.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final MenuRepository menuRepository;

    public OrderServiceImpl(OrderRepository orderRepository, MenuRepository menuRepository) {
        this.orderRepository = orderRepository;
        this.menuRepository = menuRepository;
    }

    private int findTotal(List<BillItem> billItems) {
       Integer total = 0;
        for(BillItem bi : billItems){
            total+=bi.getPrice();
        }
        return total;
    }

    @Override
    public OrderIdEntity orderItem(OrderRequest orderRequest) {

        List<ItemRequest> orderItem=orderRequest.getItems();
        for(int i=0;i<orderItem.size();i++){
            try{
                boolean check=ckeckAvailablity(orderItem.get(i));
                if(!check){
                    throw new Exception() ;
                }

            } catch (Exception e) {
                System.out.println("Item not Available");
                orderItem.remove(orderItem.get(i));
            }
        }
        OrderIdEntity orderIdEntity=new OrderIdEntity();
        orderIdEntity.setOrderEntity(changeToOrderEntity(orderItem));
        return orderRepository.save(orderIdEntity);
    }

    private List<OrderEntity> changeToOrderEntity(List<ItemRequest> orderItem) {
        List<OrderEntity> orderEntities=new ArrayList<>();
        for(ItemRequest iR:orderItem){
            OrderEntity orderEntity=new OrderEntity();
            orderEntity.setItemName(iR.getItemName());
            orderEntity.setQuantity(iR.getQuantity());
            orderEntities.add(orderEntity);
        }
        return orderEntities;
    }

    private boolean ckeckAvailablity(ItemRequest item) {
        List<MenuEntity> menu= (List<MenuEntity>) menuRepository.findAll();
        boolean check=false;
        for (MenuEntity menuitem:menu){
            if (menuitem.getItemName().equals(item.getItemName())){
                check=true;
            }
        }
        return check;
    }

    @Override
    public BillResponse getBill(Integer orderId) {
    BillResponse billResponse=new BillResponse();
    billResponse.setBillItem(getBillitems(orderId));
    Integer total=findTotal(billResponse.getBillItem());
    billResponse.setTotal(total);
    return billResponse;
    }

    private List<BillItem> getBillitems(int orderId) {
        List<BillItem> output=new ArrayList<>();
        List<OrderEntity> orderitems=orderRepository.findById(orderId).getOrderEntity();
        for(OrderEntity orderitem:orderitems){
            BillItem billItem=new BillItem();
            billItem.setItem(orderitem.getItemName());
            billItem.setPrice(findItemPrice(orderitem.getItemName(),orderitem.getQuantity()));
            billItem.setQuantity(orderitem.getQuantity());
            output.add(billItem);
        }
        return output;
    }

    private int findItemPrice(String itemName, Integer quantity) {
        return quantity*menuRepository.findByItemName(itemName).getPrice();
    }
    @Override
    public void addItemQuantity(OrderIdEntity addnewItem) {
        Optional<OrderIdEntity> oldOrder=orderRepository.findById(addnewItem.getOrderId());
    try{
        if(oldOrder.equals(null)){
            throw new Exception();
        }
    } catch (Exception e) {
        System.out.println("order is not Present");
    }
    for(OrderEntity newitem:addnewItem.getOrderEntity()){
        boolean check=false;
        for(OrderEntity olditem:oldOrder.get().getOrderEntity()){
            if(newitem.getItemName().equals(olditem.getItemName())){
                check=true;
                olditem.setQuantity(olditem.getQuantity()+newitem.getQuantity());
            }
        }
        if(check==false){
            oldOrder.get().getOrderEntity().add(newitem);
        }
    }
    orderRepository.save(oldOrder.get());

    }
}
