package mytest;

import com.itheima.ssm.dao.OrderDao;
import com.itheima.ssm.dao.ProductDao;
import com.itheima.ssm.domain.Product;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: sgy
 * @CreateDate: 2018/11/11$ 19:46$
 */

public class TestDao {
    /*@Test
    public void testDao(){
        ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext-dao.xml");
        OrderDao dao = (OrderDao)ac.getBean("orderDao");
       dao.findAllOrder();
        for (Product product : products) {
            System.out.println(product);
        }
    }*/
}
