package com.example.theexamapi.repositories;


import com.example.theexamapi.entity.TCourse;
import com.example.theexamapi.entity.TStream;
import com.example.theexamapi.utilsResources;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Optional;

public class TCourseRepository {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("ExamAPI");
    private EntityManager em;

    public TCourseRepository() {
        em = emf.createEntityManager();
    }

    public TCourse save(TCourse tCourse) {
        em.getTransaction().begin();
        tCourse.setUidCourse(utilsResources.generateUid());
        tCourse.setJstatus(1);
        em.persist(tCourse);
        em.getTransaction().commit();
        return tCourse;
    }

    public Optional<TCourse> findById(String uid) {
        em.getTransaction().begin();
//        TCourse tCourse = em.find(TCourse.class, uid);
        TCourse tCourse = em.createQuery("from TCourse where uidCourse = :uid and jstatus=1", TCourse.class).setParameter("uid",uid).getSingleResult();
        em.getTransaction().commit();
        return tCourse != null ? Optional.of(tCourse) : Optional.empty();
    }


    @SuppressWarnings("unchecked")
    public List<TCourse> findAll() {
        return em.createQuery("from TCourse").getResultList();
    }


    public List<TCourse> findByStream(TStream tStream) {
        em.getTransaction().begin();
        List<TCourse> tCourses = em.createQuery("from TCourse where uidStreamFk = :tStream and jstatus=1", TCourse.class).setParameter("tStream",tStream).getResultList();
        em.getTransaction().commit();
        return tCourses;
    }
    public TCourse update(TCourse tCourse) {
        em.getTransaction().begin();
        tCourse = em.merge(tCourse);
        em.getTransaction().commit();
        return tCourse;
    }

    public void deleteById(String uid) {
        em.getTransaction().begin();
        em.remove(em.find(TCourse.class, uid));
        em.getTransaction().commit();
    }

    public void close() {
        emf.close();
    }
}
