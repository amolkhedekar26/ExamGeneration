package com.example.theexamapi.repositories;


import com.example.theexamapi.entity.TCourse;
import com.example.theexamapi.entity.TDepartmentClassMapSemesterMapping;
import com.example.theexamapi.entity.TStream;
import com.example.theexamapi.entity.TSubject;
import com.example.theexamapi.utilsResources;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Optional;

public class TSubjectRepository {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("ExamAPI");
    private EntityManager em;

    public TSubjectRepository() {
        em = emf.createEntityManager();
    }

    public TSubject save(TSubject tSubject) {
        em.getTransaction().begin();
        tSubject.setUidSubject(utilsResources.generateUid());
        tSubject.setJstatus(1);
        em.persist(tSubject);
        em.getTransaction().commit();
        return tSubject;
    }

    public Optional<TSubject> findById(String uid) {
        em.getTransaction().begin();
//        TSubject tSubject = em.find(TSubject.class, uid);
        TSubject tSubject = em.createQuery("from TSubject where uidSubject = :uid and jstatus=1", TSubject.class).setParameter("uid", uid).getSingleResult();
        em.getTransaction().commit();
        return tSubject != null ? Optional.of(tSubject) : Optional.empty();
    }


    @SuppressWarnings("unchecked")
    public List<TSubject> findAll() {
        return em.createQuery("from TSubject").getResultList();
    }

    public List<TSubject> findBySemester(TDepartmentClassMapSemesterMapping tDepartmentClassMapSemesterMapping) {
        em.getTransaction().begin();
        List<TSubject> tSubjects = em.createQuery("from TSubject where uidDepartmentClassSemesterMapFK = :tDepartmentClassMapSemesterMapping and jstatus=1", TSubject.class).setParameter("tDepartmentClassMapSemesterMapping",tDepartmentClassMapSemesterMapping).getResultList();
        em.getTransaction().commit();
        return tSubjects;
    }

    public TSubject update(TSubject tSubject) {
        em.getTransaction().begin();
        tSubject = em.merge(tSubject);
        em.getTransaction().commit();
        return tSubject;
    }

    public void deleteById(String uid) {
        em.getTransaction().begin();
        em.remove(em.find(TSubject.class, uid));
        em.getTransaction().commit();
    }

    public void close() {
        emf.close();
    }

}
