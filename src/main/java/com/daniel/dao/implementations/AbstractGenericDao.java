package com.daniel.dao.implementations;

import com.daniel.dao.interfaces.GenericDao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;

public class AbstractGenericDao<T> implements GenericDao<T> {
    private final Class<T> entityClass;

    @PersistenceContext
    EntityManager entityManager;

    public AbstractGenericDao() {
        this.entityClass
                = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Override
    public T add(T t) {
        if (t != null) {

            try {

                entityManager.persist(t);
                return t;
            } catch (Exception e) {

                e.printStackTrace();
            } finally {
                if (entityManager != null) {
                    entityManager.close();
                }
            }


        }
        return t;
    }

    @Override
    public T modify(T t) {
        if (t != null) {

            entityManager.merge(t);
            return t;
        }
        return t;
    }

    @Override
    public void delete(Long id) {
        if (id != null) {


            T t = entityManager.find(entityClass, id);
            entityManager.remove(t);

        }
    }

    @Override
    public Optional<T> getById(Long id) {
        Optional<T> optional = Optional.ofNullable(entityManager.find(entityClass, id));


        return optional;
    }

    @Override
    public List<T> getAll() {


        return entityManager.createQuery( "from " + entityClass.getName() )
                .getResultList();
    }
}
