package com.itheima.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.itheima.ssm.dao.OrderDao;
import com.itheima.ssm.domain.Orders;
import com.itheima.ssm.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: sgy
 * @CreateDate: 2018/11/13$ 20:54$
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;
    public List<Orders> findAllOrder(int page,int size) {
        //开启分页
        PageHelper.startPage(page,size);
        return orderDao.findAllOrder();
    }

    public Orders findById(String id) {

        return orderDao.findById(id);
    }
}
