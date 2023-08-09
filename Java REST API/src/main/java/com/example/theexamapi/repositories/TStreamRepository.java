package com.example.theexamapi.repositories;

import com.example.theexamapi.entity.TStream;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Optional;
import com.example.theexamapi.utilsResources;

public class TStreamRepository {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("ExamAPI");
    private EntityManager em;

    public TStreamRepository() {
        em = emf.createEntityManager();
    }

    public TStream save(TStream tStream) {
        em.getTransaction().begin();
        tStream.setUidStream(utilsResources.generateUid());
        tStream.setJstatus(1);
        em.persist(tStream);
        em.getTransaction().commit();
        return tStream;
    }

    public Optional<TStream> findById(String uid) {
        em.getTransaction().begin();
//        TStream tStream = em.find(TStream.class, uid);
        TStream tStream = em.createQuery("from TStream where uidStream = :uid and jstatus=1", TStream.class).setParameter("uid",uid).getSingleResult();
        em.getTransaction().commit();
        return tStream != null ? Optional.of(tStream) : Optional.empty();
    }


    @SuppressWarnings("unchecked")
    public List<TStream> findAll() {
        return em.createQuery("from TStream").getResultList();
    }

    public TStream update(TStream tStream) {
        em.getTransaction().begin();
        tStream = em.merge(tStream);
        em.getTransaction().commit();
        return tStream;
    }

    public void deleteById(String uid) {
        em.getTransaction().begin();
        em.remove(em.find(TStream.class, uid));
        em.getTransaction().commit();
    }

    public void close() {
        emf.close();
    }



}
