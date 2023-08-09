package com.example.theexamapi.repo;

import com.example.theexamapi.entity.TStreamEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Optional;

public class StreamRepository {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("ExamAPI");
    private EntityManager em;

    public StreamRepository() {
        em = emf.createEntityManager();
    }

    public TStreamEntity save(TStreamEntity tStream) {
        em.getTransaction().begin();
        tStream.setState((byte) 1);
        em.persist(tStream);
        em.getTransaction().commit();
        return tStream;
    }

    public Optional<TStreamEntity> findById(String uid) {
        em.getTransaction().begin();
//        TStreamEntity tStream = em.find(TStreamEntity.class, uid);
        TStreamEntity tStream = em.createQuery("from TStreamEntity where uid = :uid and state=1", TStreamEntity.class).setParameter("uid",uid).getSingleResult();
        em.getTransaction().commit();
        return tStream != null ? Optional.of(tStream) : Optional.empty();
    }


    @SuppressWarnings("unchecked")
    public List<TStreamEntity> findAll() {
        return em.createQuery("from TStreamEntity").getResultList();
    }

    public TStreamEntity update(TStreamEntity tStream) {
        em.getTransaction().begin();
        tStream = em.merge(tStream);
        em.getTransaction().commit();
        return tStream;
    }

    public void deleteById(String uid) {
        em.getTransaction().begin();
        em.remove(em.find(TStreamEntity.class, uid));
        em.getTransaction().commit();
    }

    public void close() {
        emf.close();
    }

}
