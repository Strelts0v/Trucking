package com.itechart.trucking.dao.impl;

import com.itechart.trucking.dao.WarehouseDao;
import com.itechart.trucking.domain.Warehouse;
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
public class JpaWarehouseDao implements WarehouseDao {

    @Autowired
    private EntityManager em;

    @Override
    public Optional<Warehouse> getWarehouseById(Integer id) {
        return Optional.ofNullable(em.find(Warehouse.class, id));
    }

    @Override
    public List<Warehouse> findWarehousesByName(String namePattern) {
        final String jpqlQuery =
                "select w from Warehouse w where lower(w.name) like lower(concat('%', :namePattern, '%'))";
        TypedQuery<Warehouse> query = em.createQuery(jpqlQuery, Warehouse.class);
        query.setParameter("namePattern", namePattern);

        return query.getResultList();
    }

    @Override
    public List<Warehouse> getWarehousesByPage(int pageNumber, int pageSize) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Warehouse> cq = cb.createQuery(Warehouse.class);
        Root<Warehouse> root = cq.from(Warehouse.class);
        cq.select(root);

        TypedQuery<Warehouse> q = em.createQuery(cq);
        q.setFirstResult((pageNumber - 1) * pageSize);
        q.setMaxResults(pageSize);

        return q.getResultList();
    }

    @Override
    public int getWarehouseCount() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Warehouse> root = cq.from(Warehouse.class);
        cq.select(cb.count(root));

        return em.createQuery(cq).getSingleResult().intValue();
    }

    @Override
    public Warehouse addWarehouse(Warehouse warehouse) {
        em.persist(warehouse);
        return warehouse;
    }

    @Override
    public void updateWarehouse(Warehouse warehouse) {
        em.persist(warehouse);
    }

    @Override
    public void deleteWarehouse(Warehouse warehouse) {
        em.remove(warehouse);
    }
}
