package com.example.theexamapi.repo;


import com.example.theexamapi.entity.TCourseEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Optional;

public class CourseRepository {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("ExamAPI");
    private EntityManager em;

    public CourseRepository() {
        em = emf.createEntityManager();
    }

    public TCourseEntity save(TCourseEntity tCourse) {
        em.getTransaction().begin();
        tCourse.setState((byte) 1);
        em.persist(tCourse);
        em.getTransaction().commit();
        return tCourse;
    }

    public Optional<TCourseEntity> findById(String uid) {
        em.getTransaction().begin();
//        TCourseEntity tCourse = em.find(TCourseEntity.class, uid);
        TCourseEntity tCourse = em.createQuery("from TCourseEntity where uid = :uid and state=1", TCourseEntity.class).setParameter("uid",uid).getSingleResult();
        em.getTransaction().commit();
        return tCourse != null ? Optional.of(tCourse) : Optional.empty();
    }


    @SuppressWarnings("unchecked")
    public List<TCourseEntity> findAll() {
        return em.createQuery("from TCourseEntity").getResultList();
    }

    public TCourseEntity update(TCourseEntity tCourse) {
        em.getTransaction().begin();
        tCourse = em.merge(tCourse);
        em.getTransaction().commit();
        return tCourse;
    }

    public void deleteById(String uid) {
        em.getTransaction().begin();
        em.remove(em.find(TCourseEntity.class, uid));
        em.getTransaction().commit();
    }

    public void close() {
        emf.close();
    }
}
