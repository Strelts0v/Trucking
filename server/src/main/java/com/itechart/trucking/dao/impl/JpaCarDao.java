package com.itechart.trucking.dao.impl;

import com.itechart.trucking.dao.CarDao;
import com.itechart.trucking.domain.Car;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;


public class JpaCarDao implements CarDao {

    @Autowired
    private EntityManager em;

    @Override
    public List<Car> findAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Car> cq = cb.createQuery(Car.class);
        Root<Car> root = cq.from(Car.class);
        cq.select(root);
        TypedQuery<Car> q = em.createQuery(cq);
        return q.getResultList();
    }

    @Override
    public List<Car> findAllByPage(int offset, int recordPerPage) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Car> cq = cb.createQuery(Car.class);
        Root<Car> root = cq.from(Car.class);
        cq.select(root);
        TypedQuery<Car> q = em.createQuery(cq);
        q.setFirstResult((offset - 1) * recordPerPage);
        q.setMaxResults(recordPerPage);
        return q.getResultList();
    }

    @Override
    public Optional<Car> findOne(Integer id) {

        return Optional.ofNullable(em.find(Car.class, id));


    }
}
