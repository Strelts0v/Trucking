package com.itechart.trucking.dao.impl;

import com.itechart.trucking.dao.UserDao;
import com.itechart.trucking.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
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
    public Optional<User> getUserByEmail(String email) {
        final String jpqlQuery =
                "select u from User u where u.email = :email";
        TypedQuery<User> query = em.createQuery(jpqlQuery, User.class);
        query.setParameter("email", email);

        User user = query.getSingleResult();
        return Optional.ofNullable(user);
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
    public List<User> getAllUsers() {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);
        criteriaQuery.select(root);
        TypedQuery<User> typedQuery = em.createQuery(criteriaQuery);
        return typedQuery.getResultList();
    }

    @Override
    public int getUserCount() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<User> root = cq.from(User.class);
        cq.select(cb.count(root));

        return em.createQuery(cq).getSingleResult().intValue();
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
    public void deleteUser(User user) {
        em.remove(user);
    }

    @Override
    public List<User> getUsersByRole(User.Role role) {
        final String sqlQuery =
                "SELECT * FROM users u RIGHT JOIN users_has_roles ur" +
                " ON u.id = ur.users_id WHERE ur.user_role = 'DR'";

        Query query = em.createNativeQuery(sqlQuery, User.class);
        return query.getResultList();

    }
}