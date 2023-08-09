package com.example.theexamapi.repositories;

import com.example.theexamapi.entity.TQuestion;
import com.example.theexamapi.utilsResources;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Optional;

public class TQuestionRepository {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("ExamAPI");
    private EntityManager em;

    public TQuestionRepository() {
        em = emf.createEntityManager();
    }

    public TQuestion save(TQuestion tQuestion) {
        em.getTransaction().begin();
        tQuestion.setUidQuestion(utilsResources.generateUid());
        tQuestion.setJstatus(1);
        em.persist(tQuestion);
        em.getTransaction().commit();
        return tQuestion;
    }

    public Optional<TQuestion> findById(String uid) {
        em.getTransaction().begin();
//        TQuestion tQuestion = em.find(TQuestion.class, uid);
        TQuestion tQuestion = em.createQuery("from TQuestion where uidQuestion = :uid and jstatus=1", TQuestion.class).setParameter("uid",uid).getSingleResult();
        em.getTransaction().commit();
        return tQuestion != null ? Optional.of(tQuestion) : Optional.empty();
    }


    @SuppressWarnings("unchecked")
    public List<TQuestion> findAll() {
        return em.createQuery("from TQuestion").getResultList();
    }

    public TQuestion update(TQuestion tQuestion) {
        em.getTransaction().begin();
        tQuestion = em.merge(tQuestion);
        em.getTransaction().commit();
        return tQuestion;
    }

    public void deleteById(String uid) {
        em.getTransaction().begin();
        em.remove(em.find(TQuestion.class, uid));
        em.getTransaction().commit();
    }

    public void close() {
        emf.close();
    }



}
