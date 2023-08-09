package com.example.theexamapi.repositories;


import com.example.theexamapi.entity.TClass;
import com.example.theexamapi.utilsResources;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Optional;

public class TClassRepository {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("ExamAPI");
    private EntityManager em;

    public TClassRepository() {
        em = emf.createEntityManager();
    }

    public TClass save(TClass tClass) {
        em.getTransaction().begin();
        tClass.setUidClass(utilsResources.generateUid());
        tClass.setJstatus(1);
        em.persist(tClass);
        em.getTransaction().commit();
        return tClass;
    }

    public Optional<TClass> findById(String uid) {
        em.getTransaction().begin();
//        TClass tClass = em.find(TClass.class, uid);
        TClass tClass = em.createQuery("from TClass where uidClass = :uid and jstatus=1", TClass.class).setParameter("uid", uid).getSingleResult();
        em.getTransaction().commit();
        return tClass != null ? Optional.of(tClass) : Optional.empty();
    }


    @SuppressWarnings("unchecked")
    public List<TClass> findAll() {
        return em.createQuery("from TClass").getResultList();
    }

    public TClass update(TClass tClass) {
        em.getTransaction().begin();
        tClass = em.merge(tClass);
        em.getTransaction().commit();
        return tClass;
    }

    public void deleteById(String uid) {
        em.getTransaction().begin();
        em.remove(em.find(TClass.class, uid));
        em.getTransaction().commit();
    }

    public void close() {
        emf.close();
    }

}
