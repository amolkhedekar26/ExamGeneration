package com.example.theexamapi.repo;


import com.example.theexamapi.entity.TClassEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Optional;

public class ClassRepository {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("ExamAPI");
    private EntityManager em;

    public ClassRepository() {
        em = emf.createEntityManager();
    }

    public TClassEntity save(TClassEntity tClass) {
        em.getTransaction().begin();
        tClass.setState((byte) 1);
        em.persist(tClass);
        em.getTransaction().commit();
        return tClass;
    }

    public Optional<TClassEntity> findById(String uid) {
        em.getTransaction().begin();
//        TClassEntity tClass = em.find(TClassEntity.class, uid);
        TClassEntity tClass = em.createQuery("from TClassEntity where uid = :uid and state=1", TClassEntity.class).setParameter("uid",uid).getSingleResult();
        em.getTransaction().commit();
        return tClass != null ? Optional.of(tClass) : Optional.empty();
    }


    @SuppressWarnings("unchecked")
    public List<TClassEntity> findAll() {
        return em.createQuery("from TClassEntity").getResultList();
    }

    public TClassEntity update(TClassEntity tClass) {
        em.getTransaction().begin();
        tClass = em.merge(tClass);
        em.getTransaction().commit();
        return tClass;
    }

    public void deleteById(String uid) {
        em.getTransaction().begin();
        em.remove(em.find(TClassEntity.class, uid));
        em.getTransaction().commit();
    }

    public void close() {
        emf.close();
    }

}
