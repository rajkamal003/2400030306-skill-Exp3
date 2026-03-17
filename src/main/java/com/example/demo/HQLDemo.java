
package com.example.demo;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.example.entity.Product;
import com.example.util.HibernateUtil;

public class HQLDemo {

    public static void main(String[] args){

        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();

        sortProductsByPriceAscending(session);
        countTotalProducts(session);

        session.close();
        factory.close();
    }

    public static void sortProductsByPriceAscending(Session session){

        String hql = "FROM Product p ORDER BY p.price ASC";

        Query<Product> query = session.createQuery(hql, Product.class);
        List<Product> products = query.list();

        System.out.println("Products Sorted By Price:");

        for(Product p : products){
            System.out.println(p.getName()+" - "+p.getPrice());
        }
    }

    public static void countTotalProducts(Session session){

        String hql = "SELECT COUNT(p) FROM Product p";
        Query<Long> query = session.createQuery(hql, Long.class);

        Long count = query.uniqueResult();

        System.out.println("Total Products: "+count);
    }
}
