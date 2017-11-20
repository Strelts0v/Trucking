package com.itechart.trucking.dao.impl;


import com.itechart.trucking.dao.UserDao;
import com.itechart.trucking.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;



@Repository
public class JpaUserDao implements UserDao{

    @Autowired
    private EntityManager em;


    @Override
    public Optional<User> getUserById(Integer id) {
        return Optional.ofNullable(em.find(User.class, id));
    }

    @Override
    public List<User> getUsersByPage(int offset, int recordPerPage) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> root = cq.from(User.class);
        cq.select(root);
        TypedQuery<User> q = em.createQuery(cq);
        q.setFirstResult((offset - 1) * recordPerPage);
        q.setMaxResults(recordPerPage);
        return q.getResultList();
    }

    @Override
    public User addUser(User user) {
        em.persist(user);
        return user;
    }

    @Override
    public void updateUser(User user) {
        em.persist(user);
    }

    @Override
    public void deleteClient(User user) {
        em.remove(user);
    }
}
