package test;

import com.itheima.ssm.dao.ProductDao;
import com.itheima.ssm.domain.Product;
import com.itheima.ssm.service.ProductService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: sgy
 * @CreateDate: 2018/11/11$ 20:12$
 */

public class TestService {
   /* @Test
    public void testService(){
        ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext-service.xml");
        ProductService service = (ProductService) ac.getBean("productService");
        List<Product> products = service.findAll();
        for (Product product : products) {
            System.out.println(product);
        }
    }*/
}
