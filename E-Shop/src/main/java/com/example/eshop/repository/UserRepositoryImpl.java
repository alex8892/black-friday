package com.example.eshop.repository;

import com.example.eshop.models.User;
import com.example.eshop.repository.contracts.UserRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final SessionFactory factory;

    @Autowired
    public UserRepositoryImpl(SessionFactory factory) {
        this.factory = factory;
    }


    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        try (Session session = factory.openSession()) {
            users = session.createQuery("from User", User.class).list();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return users;
    }


    @Override
    public User getUserByUsername(String username) {
        User user = new User();
        try (Session session = factory.openSession()) {
         Query<User> query = session.createQuery("from User where username like :username",User.class);
         query.setParameter("username",username);
            user = query.list().get(0);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return user;
    }

    @Override
    public void updateUser(User user) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.update(user);
            session.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void deleteUser(User user) {
        user.setEnabled(0);
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.delete(user);
            session.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void createUser(User user) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

}
