package com.example.eshop.repository;

import com.example.eshop.models.Order;
import com.example.eshop.repository.contracts.OrderRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class OrderRepositoryImpl implements OrderRepository {

    private final SessionFactory factory;

    @Autowired
    public OrderRepositoryImpl(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public List<Order> getAll() {
        List<Order> orders = new ArrayList<>();
        try (Session session = factory.openSession()) {
            orders = session.createQuery("from Order", Order.class).list();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return orders;
    }

    @Override
    public Double getAllOrdersSum() {
        Double sum = 0.0;
        try (Session session = factory.openSession()) {
            sum = session.createQuery("select sum(price) from Order", Double.class).getSingleResult();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return sum;
    }

    @Override
    public List<Order> getFilterOrders(Date dateFrom, Date dateTo) {
        List<Order> orders = new ArrayList<>();
        try (Session session = factory.openSession()) {
            Query<Order> query = session.createQuery("from Order where date >= :dateFrom and date <= :dateTo");
            query.setParameter("dateFrom", dateFrom);
            query.setParameter("dateTo", dateTo);
            orders = query.list();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return orders;
    }

    @Override
    public Double getFilteredOrdersSum(Date dateFrom, Date dateTo) {
        Double sum = 0.0;
        try (Session session = factory.openSession()) {
            Query<Double> query = session.createQuery("select sum(price) from Order where date >= :dateFrom and date <= :dateTo");
            query.setParameter("dateFrom", dateFrom);
            query.setParameter("dateTo", dateTo);
            sum = query.getSingleResult();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return sum;
    }


    @Override
    public Order getOrderById(int id) {
        Order order = new Order();
        try (Session session = factory.openSession()) {
            order = session.get(Order.class, id);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return order;
    }

    @Override
    public void createOrder(Order order) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.save(order);
            session.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void updateOrder(Order order) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.update(order);
            session.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void deleteOrder(Order order) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.delete(order);
            session.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
