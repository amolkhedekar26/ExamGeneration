package com.example.theexamapi.repositories;


import com.example.theexamapi.entity.TExamQuestionMapping;
import com.example.theexamapi.utilsResources;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Optional;

public class TExamQuestionMappingRepository {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("ExamAPI");
    private EntityManager em;

    public TExamQuestionMappingRepository() {
        em = emf.createEntityManager();
    }

    public TExamQuestionMapping save(TExamQuestionMapping tExamQuestionMapping) {
        em.getTransaction().begin();
        tExamQuestionMapping.setUidExamQuestionMapping(utilsResources.generateUid());
        tExamQuestionMapping.setJstatus(1);
        em.persist(tExamQuestionMapping);
        em.getTransaction().commit();
        return tExamQuestionMapping;
    }

    public Optional<TExamQuestionMapping> findById(String uid) {
        em.getTransaction().begin();
//        TExamQuestionMapping tExamQuestionMapping = em.find(TExamQuestionMapping.class, uid);
        TExamQuestionMapping tExamQuestionMapping = em.createQuery("from TExamQuestionMapping where uidExamQuestionMapping = :uid and jstatus=1", TExamQuestionMapping.class).setParameter("uid", uid).getSingleResult();
        em.getTransaction().commit();
        return tExamQuestionMapping != null ? Optional.of(tExamQuestionMapping) : Optional.empty();
    }


    @SuppressWarnings("unchecked")
    public List<TExamQuestionMapping> findAll() {
        return em.createQuery("from TExamQuestionMapping").getResultList();
    }


    //    public List<TExamQuestionMapping> findByStream(TExamQuestionMapping tExamQuestionMapping) {
//        em.getTransaction().begin();
//        List<TExamQuestionMapping> tExamQuestionMappings = em.createQuery("from TExamQuestionMapping where uidStreamFk = :tExamQuestionMapping and jstatus=1", TExamQuestionMapping.class).setParameter("tExamQuestionMapping",tExamQuestionMapping).getResultList();
//        em.getTransaction().commit();
//        return tExamQuestionMappings;
//    }
    public TExamQuestionMapping update(TExamQuestionMapping tExamQuestionMapping) {
        em.getTransaction().begin();
        tExamQuestionMapping = em.merge(tExamQuestionMapping);
        em.getTransaction().commit();
        return tExamQuestionMapping;
    }

    public void deleteById(String uid) {
        em.getTransaction().begin();
        em.remove(em.find(TExamQuestionMapping.class, uid));
        em.getTransaction().commit();
    }

    public void close() {
        emf.close();
    }
}
