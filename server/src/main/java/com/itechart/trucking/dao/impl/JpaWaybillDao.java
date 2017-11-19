package com.itechart.trucking.dao.impl;

import com.itechart.trucking.dao.WaybillDao;
import com.itechart.trucking.domain.Waybill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

/**
 * @author blink7
 * @version 1.0
 * @since 2017-11-18
 */
@Repository
public class JpaWaybillDao implements WaybillDao {

    @Autowired
    private EntityManager em;

    @Override
    public List<Waybill> findAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Waybill> cq = cb.createQuery(Waybill.class);
        Root<Waybill> root = cq.from(Waybill.class);
        cq.select(root);
        TypedQuery<Waybill> q = em.createQuery(cq);
        return q.getResultList();
    }

    @Override
    public List<Waybill> findAllByPage(int pageNumber, int pageSize) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Waybill> cq = cb.createQuery(Waybill.class);
        Root<Waybill> root = cq.from(Waybill.class);
        cq.select(root);
        TypedQuery<Waybill> q = em.createQuery(cq);
        q.setFirstResult((pageNumber - 1) * pageSize);
        q.setMaxResults(pageSize);
        return q.getResultList();
    }

    @Override
    public Optional<Waybill> findOne(Integer id) {
        return Optional.ofNullable(em.find(Waybill.class, id));
    }

    @Override
    public void save(Waybill waybill) {
        em.persist(waybill);
    }

    @Override
    public void delete(Waybill waybill) {
        em.remove(waybill);
    }

    @Override
    public int size() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Waybill> root = cq.from(Waybill.class);
        cq.select(cb.count(root));
        return em.createQuery(cq).getSingleResult().intValue();
    }

    public EntityManager getEntityManager() {
        return em;
    }

    public void setEntityManager(EntityManager em) {
        this.em = em;
    }
}
