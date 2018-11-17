package com.itheima.ssm.service;

import com.itheima.ssm.domain.Orders;


import java.util.List;

public interface OrderService {
    List<Orders> findAllOrder(int page,int size);

    Orders findById(String id);
}
