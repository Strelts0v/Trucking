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

/**
 * @author Quontico
 * @version 1.0
 * @since 2017-11-20
 */

@Repository
public class JpaItemDao implements ItemDao{

    @Autowired
    private EntityManager entityManager;

    public JpaItemDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Item> findAllItems() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Item> criteriaQuery = criteriaBuilder.createQuery(Item.class);
        Root<Item> root = criteriaQuery.from(Item.class);
        criteriaQuery.select(root);
        TypedQuery<Item> typedQuery = entityManager.createQuery(criteriaQuery);
        return typedQuery.getResultList();
    }

    @Override
    public List<Item> findAllItemsByPage(int pageNumber, int pageSize) {
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
    public int getItemCount() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        Root<Item> root = criteriaQuery.from(Item.class);
        criteriaQuery.select(criteriaBuilder.count(root));

        return entityManager.createQuery(criteriaQuery).getSingleResult().intValue();
    }

    @Override
    public Item addItem(Item item) {
        entityManager.persist(item);
        return item;
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
}
