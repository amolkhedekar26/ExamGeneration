package com.example.theexamapi.repositories;


import com.example.theexamapi.entity.TCourse;
import com.example.theexamapi.entity.TDepartment;
import com.example.theexamapi.utilsResources;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Optional;

public class TDepartmentRepository {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("ExamAPI");
    private EntityManager em;

    public TDepartmentRepository() {
        em = emf.createEntityManager();
    }

    public TDepartment save(TDepartment tDepartment) {
        em.getTransaction().begin();
        tDepartment.setJstatus(1);
        tDepartment.setUidDepartment(utilsResources.generateUid());
        em.persist(tDepartment);
        em.getTransaction().commit();
        return tDepartment;
    }

    public Optional<TDepartment> findById(String uid) {
        em.getTransaction().begin();
//        TDepartment tDepartment = em.find(TDepartment.class, uid);
        TDepartment tDepartment = em.createQuery("from TDepartment where uidDepartment = :uid and jstatus=1", TDepartment.class).setParameter("uid", uid).getSingleResult();
        em.getTransaction().commit();
        return tDepartment != null ? Optional.of(tDepartment) : Optional.empty();
    }


    @SuppressWarnings("unchecked")
    public List<TDepartment> findAll() {
        return em.createQuery("from TDepartment").getResultList();
    }

    public List<TDepartment> findByCourse(TCourse tCourse) {
        em.getTransaction().begin();
        List<TDepartment> tDepartmentList = em.createQuery("from TDepartment where uidCourseFk = :tCourse and jstatus=1", TDepartment.class).setParameter("tCourse", tCourse).getResultList();
        em.getTransaction().commit();
        return tDepartmentList;
    }

    public TDepartment update(TDepartment tDepartment) {
        em.getTransaction().begin();
        tDepartment = em.merge(tDepartment);
        em.getTransaction().commit();
        return tDepartment;
    }

    public void deleteById(String uid) {
        em.getTransaction().begin();
        em.remove(em.find(TDepartment.class, uid));
        em.getTransaction().commit();
    }

    public void close() {
        emf.close();
    }

}
