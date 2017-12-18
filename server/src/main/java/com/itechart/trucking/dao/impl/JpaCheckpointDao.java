package com.itechart.trucking.dao.impl;

import com.itechart.trucking.dao.CheckpointDao;
import com.itechart.trucking.domain.Checkpoint;
import com.itechart.trucking.domain.Checkpoint_;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

/**
 * @author blink7
 * @version 1.2
 * @since 2017-12-13
 */
@Repository
public class JpaCheckpointDao implements CheckpointDao {

    private EntityManager em;

    public JpaCheckpointDao(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Checkpoint> findAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Checkpoint> cq = cb.createQuery(Checkpoint.class);
        Root<Checkpoint> root = cq.from(Checkpoint.class);
        cq.select(root);
        TypedQuery<Checkpoint> q = em.createQuery(cq);
        return q.getResultList();
    }

    @Override
    public Optional<Checkpoint> findOneByName(String name) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Checkpoint> cq = cb.createQuery(Checkpoint.class);
        Root<Checkpoint> root = cq.from(Checkpoint.class);
        cq.where(cb.equal(root.get(Checkpoint_.name), name));
        TypedQuery<Checkpoint> q = em.createQuery(cq);
        Optional<Checkpoint> result;
        try {
            result = Optional.of(q.getSingleResult());
        } catch (NoResultException | NullPointerException e) {
            result = Optional.empty();
        }
        return result;
    }

    @Override
    public Optional<Checkpoint> findOneByLocation(Double lat, Double lng) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Checkpoint> cq = cb.createQuery(Checkpoint.class);
        Root<Checkpoint> root = cq.from(Checkpoint.class);
        cq.where(cb.equal(root.get(Checkpoint_.lat), lat),
                cb.equal(root.get(Checkpoint_.lng), lng));
        TypedQuery<Checkpoint> q = em.createQuery(cq);
        Optional<Checkpoint> result;
        try {
            result = Optional.of(q.getSingleResult());
        } catch (NoResultException | NullPointerException e) {
            result = Optional.empty();
        }
        return result;
    }

    @Override
    public Checkpoint save(Checkpoint checkpoint) {
        return em.merge(checkpoint);
    }

    @Override
    public void delete(Checkpoint checkpoint) {
        em.remove(checkpoint);
    }

    public EntityManager getEntityManager() {
        return em;
    }

    public void setEntityManager(EntityManager em) {
        this.em = em;
    }
}