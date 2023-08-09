package com.example.theexamapi.repositories;


import com.example.theexamapi.entity.TSemester;
import com.example.theexamapi.utilsResources;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Optional;

public class TSemesterRepository {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("ExamAPI");
    private EntityManager em;

    public TSemesterRepository() {
        em = emf.createEntityManager();
    }

    public TSemester save(TSemester tSemester) {
        em.getTransaction().begin();
        tSemester.setUidSemester(utilsResources.generateUid());
        tSemester.setJstatus(1);
        em.persist(tSemester);
        em.getTransaction().commit();
        return tSemester;
    }

    public Optional<TSemester> findById(String uid) {
        em.getTransaction().begin();
//        TSemester tSemester = em.find(TSemester.class, uid);
        TSemester tSemester = em.createQuery("from TSemester where uidSemester = :uid and jstatus=1", TSemester.class).setParameter("uid", uid).getSingleResult();
        em.getTransaction().commit();
        return tSemester != null ? Optional.of(tSemester) : Optional.empty();
    }


    @SuppressWarnings("unchecked")
    public List<TSemester> findAll() {
        return em.createQuery("from TSemester").getResultList();
    }

    public TSemester update(TSemester tSemester) {
        em.getTransaction().begin();
        tSemester = em.merge(tSemester);
        em.getTransaction().commit();
        return tSemester;
    }

    public void deleteById(String uid) {
        em.getTransaction().begin();
        em.remove(em.find(TSemester.class, uid));
        em.getTransaction().commit();
    }

    public void close() {
        emf.close();
    }

}
