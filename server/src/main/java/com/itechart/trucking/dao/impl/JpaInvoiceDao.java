package com.itechart.trucking.dao.impl;

import com.itechart.trucking.dao.InvoiceDao;
import com.itechart.trucking.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 * @version 1.4
 * @since 2017-12-17
 */
@Repository
public class JpaInvoiceDao implements InvoiceDao {

    private final Logger log = LoggerFactory.getLogger(JpaInvoiceDao.class);

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

    @Override
    public List<Invoice> searchInvoices(LocalDate issueDate, LocalDate checkDate, Invoice.Status status,
                                        String inspector, int pageNumber, int pageSize) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Invoice> cq = cb.createQuery(Invoice.class);
        Root<Invoice> root = cq.from(Invoice.class);

        buildSearchPredicate(cb, cq, root, issueDate, checkDate, status, inspector);

        TypedQuery<Invoice> q = em.createQuery(cq);
        q.setFirstResult((pageNumber - 1) * pageSize);
        q.setMaxResults(pageSize);
        return q.getResultList();
    }

    @Override
    public int searchSize(LocalDate issueDate, LocalDate checkDate, Invoice.Status status, String inspector) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Invoice> root = cq.from(Invoice.class);

        buildSearchPredicate(cb, cq, root, issueDate, checkDate, status, inspector);

        cq.select(cb.count(root));
        return em.createQuery(cq).getSingleResult().intValue();
    }

    private <T> void buildSearchPredicate(CriteriaBuilder cb, CriteriaQuery<T> cq, Root<Invoice> root,
                                          LocalDate issueDate, LocalDate checkDate, Invoice.Status status,
                                          String inspector) {

        List<Predicate> predicates = new ArrayList<>();

        if (issueDate != null) {
            predicates.add(cb.equal(root.get(Invoice_.issueDate), issueDate));
        }

        if (checkDate != null) {
            predicates.add(cb.equal(root.get(Invoice_.checkDate), checkDate));
        }

        if (status != null) {
            predicates.add(cb.equal(root.get(Invoice_.status), status));
        }

        if (!inspector.isEmpty()) {
            String[] nameParts = inspector.split(" ");
            Join<Invoice, User> user = root.join(Invoice_.inspector);

            predicates.add(cb.like(user.get(User_.firstName), nameParts[0] + "%"));

            if (nameParts.length > 1) {
                predicates.add(cb.like(user.get(User_.lastName), nameParts[1] + "%"));
            }

            if (nameParts.length > 2) {
                predicates.add(cb.like(user.get(User_.middleName), nameParts[2] + "%"));
            }
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
