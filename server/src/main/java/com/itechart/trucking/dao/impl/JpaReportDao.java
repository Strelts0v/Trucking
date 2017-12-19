package com.itechart.trucking.dao.impl;

import com.itechart.trucking.dao.ReportDao;
import com.itechart.trucking.domain.InvoiceResult;
import com.itechart.trucking.domain.InvoiceResult_;
import com.itechart.trucking.domain.Report;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.List;

/**
 * @author blink7
 * @version 1.0
 * @since 2017-12-17
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

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }
}
