package com.supinbank.services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: oli
 * Date: 2/25/12
 * Time: 5:48 PM
 * Generic class to handle all Create, Read, Update and Delete operations within the webapp.
 */
@Stateless
public class GenericCrudService
{
    @PersistenceContext
    EntityManager em;

    public <T> void create(T entity)
    {
        em.persist(entity);
    }

    public <T> T read(Class<T> entity, Object id)
    {
        return em.find(entity, id);
    }

    public <T> List<T> readAll(Class<T> entity)
    {
        String readAllQuery = "SELECT t FROM " + entity.getSimpleName() + " t";
        Query query = em.createQuery(readAllQuery);
        List<T> results = query.getResultList();
        return results;
    }

    public <T> void update(T entity)
    {
        em.merge(entity);
    }

    public <T> void delete(T entity)
    {
        em.remove(entity);
    }
}
