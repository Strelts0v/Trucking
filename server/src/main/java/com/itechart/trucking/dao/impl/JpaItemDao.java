package com.itechart.trucking.dao.impl;

import com.itechart.trucking.dao.ItemDao;
import com.itechart.trucking.domain.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class JpaItemDao implements ItemDao{

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Item> findAll() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Item> criteriaQuery = criteriaBuilder.createQuery(Item.class);
        Root<Item> root = criteriaQuery.from(Item.class);
        criteriaQuery.select(root);
        TypedQuery<Item> typedQuery = entityManager.createQuery(criteriaQuery);
        return typedQuery.getResultList();
    }

    @Override
    public List<Item> findAllByPage(int pageNumber, int pageSize) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Item> criteriaQuery = criteriaBuilder.createQuery(Item.class);
        Root<Item> root = criteriaQuery.from(Item.class);
        criteriaQuery.select(root);
        TypedQuery<Item> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setFirstResult((pageNumber - 1) * pageSize);
        typedQuery.setMaxResults(pageSize);
        return typedQuery.getResultList();
    }

    @Override
    public Optional<Item> findItemById(Integer id) {
        return Optional.ofNullable(entityManager.find(Item.class, id));
    }

    @Override
    public Optional<Item> findItemByName(String name) {
        return Optional.ofNullable(entityManager.find(Item.class, name));
    }

    @Override
    public void editItem(Item item) {
        entityManager.merge(item);
    }

    @Override
    public void saveItem(Item item) {
        entityManager.persist(item);
    }

    @Override
    public void deleteItem(Item item) {
        entityManager.remove(entityManager.merge(item));
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
}
