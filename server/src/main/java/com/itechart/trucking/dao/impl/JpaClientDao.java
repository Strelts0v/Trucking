package com.itechart.trucking.dao.impl;

import com.itechart.trucking.dao.ClientDao;
import com.itechart.trucking.domain.Client;
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
public class JpaClientDao implements ClientDao {

    @Autowired
    private EntityManager em;

    @Override
    public Optional<Client> getClientById(Integer id) {
        return Optional.ofNullable(em.find(Client.class, id));
    }

    @Override
    public List<Client> findClientsByName(String namePattern) {
        final String jpqlQuery =
                "select c from Client c where lower(c.name) like lower(concat('%', :namePattern, '%'))";
        TypedQuery<Client> query = em.createQuery(jpqlQuery, Client.class);
        query.setParameter("namePattern", namePattern);

        return query.getResultList();
    }

    @Override
    public List<Client> getClientsByPage(int pageNumber, int pageSize) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Client> cq = cb.createQuery(Client.class);
        Root<Client> root = cq.from(Client.class);
        cq.select(root);

        TypedQuery<Client> q = em.createQuery(cq);
        q.setFirstResult((pageNumber - 1) * pageSize);
        q.setMaxResults(pageSize);

        return q.getResultList();
    }

    @Override
    public int getClientCount() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Client> root = cq.from(Client.class);
        cq.select(cb.count(root));

        return em.createQuery(cq).getSingleResult().intValue();
    }

    @Override
    public Client addClient(Client client) {
        em.persist(client);
        return client;
    }

    @Override
    public void updateClient(Client client) {
        em.persist(client);
    }

    @Override
    public void deleteClient(Client client) {
        em.remove(client);
    }
}
