package com.itechart.trucking.dao.impl;

import com.itechart.trucking.dao.WaybillDao;
import com.itechart.trucking.domain.*;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author blink7
 * @version 1.3
 * @since 2017-12-18
 */
@Repository
public class JpaWaybillDao implements WaybillDao {

    private EntityManager em;

    public JpaWaybillDao(EntityManager em) {
        this.em = em;
    }

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

        CriteriaBuilder.Case<Integer> orderCase = cb.selectCase();
        orderCase
                .when(cb.equal(root.get(Waybill_.status), Waybill.Status.STARTED), 1)
                .when(cb.equal(root.get(Waybill_.status), Waybill.Status.COMPLETED), 2)
                .otherwise(3);
        cq.orderBy(cb.asc(orderCase));

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

    @Override
    public List<Waybill> searchWaybills(String from, String to, String invoiceNumber, LocalDate issueDate,
                                        int pageNumber, int pageSize) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Waybill> cq = cb.createQuery(Waybill.class);
        Root<Waybill> root = cq.from(Waybill.class);

        buildSearchPredicate(cb, cq, root, from, to, invoiceNumber, issueDate);

        TypedQuery<Waybill> q = em.createQuery(cq);
        q.setFirstResult((pageNumber - 1) * pageSize);
        q.setMaxResults(pageSize);
        return q.getResultList();
    }

    @Override
    public int searchSize(String from, String to, String invoiceNumber, LocalDate issueDate) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Waybill> root = cq.from(Waybill.class);

        buildSearchPredicate(cb, cq, root, from, to, invoiceNumber, issueDate);

        cq.select(cb.count(root));
        return em.createQuery(cq).getSingleResult().intValue();
    }

    private <T> void buildSearchPredicate(CriteriaBuilder cb, CriteriaQuery<T> cq, Root<Waybill> root,
                                          String from, String to, String invoiceNumber, LocalDate issueDate) {

        List<Predicate> predicates = new ArrayList<>();

        if (!from.isEmpty()) {
            Join<Waybill, Warehouse> warehouse = root.join(Waybill_.from);
            predicates.add(cb.like(warehouse.get(Warehouse_.name), from + "%"));
        }

        if (!to.isEmpty()) {
            Join<Waybill, Warehouse> warehouse = root.join(Waybill_.to);
            predicates.add(cb.like(warehouse.get(Warehouse_.name), to + "%"));
        }

        if (!invoiceNumber.isEmpty()) {
            Join<Waybill, Invoice> invoice = root.join(Waybill_.invoice);
            predicates.add(cb.like(invoice.get(Invoice_.number), invoiceNumber + "%"));
        }

        if (issueDate != null) {
            predicates.add(cb.equal(root.get(Waybill_.issueDate), issueDate));
        }

        Predicate[] p = new Predicate[predicates.size()];
        cq.where(predicates.toArray(p));
    }

    public EntityManager getEntityManager() {
        return em;
    }

    public void setEntityManager(EntityManager em) {
        this.em = em;
    }
}
