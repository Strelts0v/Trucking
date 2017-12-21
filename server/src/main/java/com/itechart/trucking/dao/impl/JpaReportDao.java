package com.itechart.trucking.dao.impl;

import com.itechart.trucking.dao.ReportDao;
import com.itechart.trucking.domain.InvoiceResult;
import com.itechart.trucking.domain.InvoiceResult_;
import com.itechart.trucking.domain.Report;
import com.itechart.trucking.domain.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.time.LocalDate;
import java.util.List;

/**
 * @author blink7
 * @version 1.1
 * @since 2017-12-21
 */
@Repository
public class JpaReportDao implements ReportDao {

    private EntityManager em;

    public JpaReportDao(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Report> findResultsByDate(LocalDate startDate, LocalDate endDate) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Report> cq = cb.createQuery(Report.class);
        Root<InvoiceResult> root = cq.from(InvoiceResult.class);

        cq.select(cb.construct(Report.class,
                root.get(InvoiceResult_.completeDate),
                cb.sum(root.get(InvoiceResult_.income)),
                cb.sum(root.get(InvoiceResult_.consumption))));

        cq.where(cb.greaterThanOrEqualTo(root.get(InvoiceResult_.completeDate), startDate),
                cb.lessThanOrEqualTo(root.get(InvoiceResult_.completeDate), endDate));

        cq.groupBy(root.get(InvoiceResult_.completeDate));

        TypedQuery<Report> q = em.createQuery(cq);
        return q.getResultList();
    }

    @Override
    public List<User> findFiveBestDrivers() {
        /*CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> root = cq.from(User.class);

        Join<InvoiceResult, User> user = root.join(InvoiceResult_.driver, JoinType.RIGHT);
        cq.orderBy(cb.desc(cb.diff(root.get(InvoiceResult_.income), root.get(InvoiceResult_.consumption))));

        TypedQuery<User> q = em.createQuery(cq);
        q.setMaxResults(1);
        return q.getResultList();*/

        Query q = em.createNativeQuery("SELECT u.* FROM users u JOIN invoices_result ir " +
                "ON ir.user_id_driver = u.id GROUP BY ir.user_id_driver " +
                "ORDER BY ir.income - ir.consumption DESC", User.class);

        q.setMaxResults(5);
        return q.getResultList();
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }
}
