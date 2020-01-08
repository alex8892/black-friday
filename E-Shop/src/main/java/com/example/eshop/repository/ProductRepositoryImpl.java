package com.example.eshop.repository;

import com.example.eshop.models.Product;
import com.example.eshop.repository.contracts.ProductRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private final SessionFactory factory;

    @Autowired
    public ProductRepositoryImpl(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public List<Product> getAllWithoutBlackFriday() {
        List<Product> products = new ArrayList<>();
        try (Session session = factory.openSession()) {
            products = session.createQuery("from Product where enabled = 1 and addedToBlackFriday = 0 ", Product.class).list();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return products;
    }

    @Override
    public List<Product> getAllBlackFriday() {
        List<Product> products = new ArrayList<>();
        try (Session session = factory.openSession()) {
            products = session.createQuery("from Product where enabled = 1 and addedToBlackFriday = 1 ", Product.class).list();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return products;
    }


    @Override
    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();
        try (Session session = factory.openSession()) {
            products = session.createQuery("from Product where enabled = 1 ", Product.class).list();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return products;
    }


    @Override
    public Product getProductById(int id) {
        Product product = new Product();
        try (Session session = factory.openSession()) {
            product = session.get(Product.class, id);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return product;
    }

    @Override
    public void createProduct(Product product) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.save(product);
            session.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void updateProduct(Product product) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.update(product);
            session.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void deleteProduct(Product product) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.delete(product);
            session.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void saveDeleteProduct(Product product) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.update(product);
            session.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public boolean checkProductExist(String name) {
        List<Product> products = new ArrayList<>();
        try (Session session = factory.openSession()) {
            Query<Product> query = session.createQuery("from Product where name = :name ", Product.class);
            query.setParameter("name", name);
            products = query.list();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        if (products.isEmpty()) {
            return false;
        }
        return true;
    }
}
