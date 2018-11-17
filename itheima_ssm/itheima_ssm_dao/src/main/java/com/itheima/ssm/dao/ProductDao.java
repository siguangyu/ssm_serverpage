package com.itheima.ssm.dao;

import com.itheima.ssm.domain.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ProductDao {
    @Select("select * from product")
    public List<Product> findAll();
    @Insert("insert into product(productnum,productname,cityname,DEPARTURETIME,PRODUCTPRICE,PRODUCTDESC,PRODUCTSTATUS) " +
            "values(#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    public void saveProduct(Product product);

    @Select("select * from product where id=#{id}")
    public Product findById(String id);
}
