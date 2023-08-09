package com.example.theexamapi.repositories;

import com.example.theexamapi.entity.TMcqOption;
import com.example.theexamapi.entity.TQuestion;
import com.example.theexamapi.utilsResources;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Optional;

public class TMcqOptionRepository {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("ExamAPI");
    private EntityManager em;

    public TMcqOptionRepository() {
        em = emf.createEntityManager();
    }

    public TMcqOption save(TMcqOption tMcqOption) {
        em.getTransaction().begin();
        tMcqOption.setUidOption(utilsResources.generateUid());
        tMcqOption.setJstatus(1);
        em.persist(tMcqOption);
        em.getTransaction().commit();
        return tMcqOption;
    }

    public Optional<TMcqOption> findById(String uid) {
        em.getTransaction().begin();
//        TMcqOption tMcqOption = em.find(TMcqOption.class, uid);
        TMcqOption tMcqOption = em.createQuery("from TMcqOption where uidOption = :uid and jstatus=1", TMcqOption.class).setParameter("uid",uid).getSingleResult();
        em.getTransaction().commit();
        return tMcqOption != null ? Optional.of(tMcqOption) : Optional.empty();
    }


    @SuppressWarnings("unchecked")
    public List<TMcqOption> findAll() {
        return em.createQuery("from TMcqOption").getResultList();
    }

    public List<TMcqOption> findByQuestion(TQuestion tQuestion) {
        em.getTransaction().begin();
        List<TMcqOption> tMcqOptions = em.createQuery("from TMcqOption where uidQuestionFk = :uidQuestion and jstatus=1", TMcqOption.class).setParameter("uidQuestion",tQuestion).getResultList();
        em.getTransaction().commit();
        return tMcqOptions;
    }

    public TMcqOption update(TMcqOption tMcqOption) {
        em.getTransaction().begin();
        tMcqOption = em.merge(tMcqOption);
        em.getTransaction().commit();
        return tMcqOption;
    }

    public void deleteById(String uid) {
        em.getTransaction().begin();
        em.remove(em.find(TMcqOption.class, uid));
        em.getTransaction().commit();
    }

    public void close() {
        emf.close();
    }



}
