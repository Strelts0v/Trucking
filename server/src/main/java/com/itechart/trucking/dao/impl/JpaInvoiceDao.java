package com.itechart.trucking.dao.impl;

import com.itechart.trucking.dao.InvoiceDao;
import com.itechart.trucking.domain.Invoice;
import com.itechart.trucking.domain.InvoiceResult;
import com.itechart.trucking.domain.Invoice_;
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
 * @version 1.3
 * @since 2017-12-16
 */
@Repository
public class JpaInvoiceDao implements InvoiceDao {

    private EntityManager em;

    public JpaInvoiceDao(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Invoice> findAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Invoice> cq = cb.createQuery(Invoice.class);
        Root<Invoice> root = cq.from(Invoice.class);
        cq.select(root);
        TypedQuery<Invoice> q = em.createQuery(cq);
        return q.getResultList();
    }

    @Override
    public List<Invoice> findAllByPage(int pageNumber, int pageSize) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Invoice> cq = cb.createQuery(Invoice.class);
        Root<Invoice> root = cq.from(Invoice.class);
        cq.select(root);

        CriteriaBuilder.Case<Integer> orderCase = cb.selectCase();
        orderCase
                .when(cb.equal(root.get(Invoice_.status), Invoice.Status.ISSUED), 1)
                .when(cb.equal(root.get(Invoice_.status), Invoice.Status.CHECKED), 2)
                .otherwise(3);
        cq.orderBy(cb.asc(orderCase));

        TypedQuery<Invoice> q = em.createQuery(cq);
        q.setFirstResult((pageNumber - 1) * pageSize);
        q.setMaxResults(pageSize);
        return q.getResultList();
    }

    @Override
    public Optional<Invoice> findOne(Integer id) {
        return Optional.ofNullable(em.find(Invoice.class, id));
    }

    @Override
    public void save(Invoice invoice) {
        em.persist(invoice);
    }

    @Override
    public void delete(Invoice invoice) {
        em.remove(invoice);
    }

    @Override
    public int size() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Invoice> root = cq.from(Invoice.class);
        cq.select(cb.count(root));
        return em.createQuery(cq).getSingleResult().intValue();
    }

    @Override
    public InvoiceResult saveResult(InvoiceResult result) {
        em.persist(result);
        return result;
    }

    public EntityManager getEntityManager() {
        return em;
    }

    public void setEntityManager(EntityManager em) {
        this.em = em;
    }
}
