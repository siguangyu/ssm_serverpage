package com.itheima.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.ssm.domain.Orders;
import com.itheima.ssm.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: sgy
 * @CreateDate: 2018/11/13$ 20:52$
 */
@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @RequestMapping("/findAllOrder")
    public ModelAndView findAllOrder(@RequestParam(name = "page",required = true,defaultValue = "1")Integer page,
                                     @RequestParam(name = "size",required = true,defaultValue = "4")Integer size)
    {
        ModelAndView mv=new ModelAndView();
        List<Orders> orders = orderService.findAllOrder(page,size);
        PageInfo pageInfo=new PageInfo(orders);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("orders-list");
        return mv;
    }
    @RequestMapping("/findById")
    public ModelAndView findById(String id){
        ModelAndView mv=new ModelAndView();
        Orders orders= orderService.findById(id);
        mv.addObject("orders",orders);
        mv.setViewName("orders-show");
        return mv;
    }

}
