package com.itechart.trucking.dao.impl;

import com.itechart.trucking.dao.ItemConsignmentDao;
import com.itechart.trucking.domain.ItemConsignment;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class JpaItemConsignmentDao implements ItemConsignmentDao {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<ItemConsignment> findAll() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<ItemConsignment> criteriaQuery = criteriaBuilder.createQuery(ItemConsignment.class);
        Root<ItemConsignment> root = criteriaQuery.from(ItemConsignment.class);
        criteriaQuery.select(root);
        TypedQuery<ItemConsignment> typedQuery = entityManager.createQuery(criteriaQuery);
        return typedQuery.getResultList();
    }

    @Override
    public List<ItemConsignment> findAllByPage(int pageNumber, int pageSize) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<ItemConsignment> criteriaQuery = criteriaBuilder.createQuery(ItemConsignment.class);
        Root<ItemConsignment> root = criteriaQuery.from(ItemConsignment.class);
        criteriaQuery.select(root);
        TypedQuery<ItemConsignment> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setFirstResult((pageNumber - 1) * pageSize);
        typedQuery.setMaxResults(pageSize);
        return typedQuery.getResultList();
    }

    @Override
    public Optional<ItemConsignment> findItemConsignmentById(UUID id) {
        return Optional.ofNullable(entityManager.find(ItemConsignment.class, id));
    }

    @Override
    public void editItemConsignment(ItemConsignment itemConsignment) {
        entityManager.merge(itemConsignment);
    }

    @Override
    public void saveItemConsignment(ItemConsignment itemConsignment) {
        entityManager.persist(itemConsignment);
    }

    @Override
    public void deleteItemConsignment(ItemConsignment itemConsignment) {
        entityManager.remove(entityManager.merge(itemConsignment));
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
}
