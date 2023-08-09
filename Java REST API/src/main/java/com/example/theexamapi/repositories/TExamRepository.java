package com.example.theexamapi.repositories;


import com.example.theexamapi.entity.TExam;
import com.example.theexamapi.utilsResources;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Optional;

public class TExamRepository {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("ExamAPI");
    private EntityManager em;

    public TExamRepository() {
        em = emf.createEntityManager();
    }

    public TExam save(TExam tExam) {
        em.getTransaction().begin();
        tExam.setUidExam(utilsResources.generateUid());
        tExam.setJstatus(1);
        em.persist(tExam);
        em.getTransaction().commit();
        return tExam;
    }

    public Optional<TExam> findById(String uid) {
        em.getTransaction().begin();
//        TExam tExam = em.find(TExam.class, uid);
        TExam tExam = em.createQuery("from TExam where uidExam = :uid and jstatus=1", TExam.class).setParameter("uid", uid).getSingleResult();
        em.getTransaction().commit();
        return tExam != null ? Optional.of(tExam) : Optional.empty();
    }


    @SuppressWarnings("unchecked")
    public List<TExam> findAll() {
        return em.createQuery("from TExam").getResultList();
    }


    //    public List<TExam> findByStream(TExam tExam) {
//        em.getTransaction().begin();
//        List<TExam> tExams = em.createQuery("from TExam where uidStreamFk = :tExam and jstatus=1", TExam.class).setParameter("tExam",tExam).getResultList();
//        em.getTransaction().commit();
//        return tExams;
//    }
    public TExam update(TExam tExam) {
        em.getTransaction().begin();
        tExam = em.merge(tExam);
        em.getTransaction().commit();
        return tExam;
    }

    public void deleteById(String uid) {
        em.getTransaction().begin();
        em.remove(em.find(TExam.class, uid));
        em.getTransaction().commit();
    }

    public void close() {
        emf.close();
    }
}
