package com.example.theexamapi.repositories;


import com.example.theexamapi.entity.TDepartment;
import com.example.theexamapi.entity.TDepartmentClassMapSemesterMapping;
import com.example.theexamapi.entity.TDepartmentClassMapping;
import com.example.theexamapi.utilsResources;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Optional;

public class TDepartmentClassMapSemesterMappingRepository {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("ExamAPI");
    private EntityManager em;

    public TDepartmentClassMapSemesterMappingRepository() {
        em = emf.createEntityManager();
    }

    public TDepartmentClassMapSemesterMapping save(TDepartmentClassMapSemesterMapping tDepartmentClassMapSemesterMapping) {
        em.getTransaction().begin();
        tDepartmentClassMapSemesterMapping.setUidDepartmentClassMapSemesterMapping(utilsResources.generateUid());
        tDepartmentClassMapSemesterMapping.setJstatus(1);
        em.persist(tDepartmentClassMapSemesterMapping);
        em.getTransaction().commit();
        return tDepartmentClassMapSemesterMapping;
    }

    public Optional<TDepartmentClassMapSemesterMapping> findById(String uid) {
        em.getTransaction().begin();
//        TDepartmentClassMapSemesterMapping tClass = em.find(TDepartmentClassMapSemesterMapping.class, uid);
        TDepartmentClassMapSemesterMapping tClass = em.createQuery("from TDepartmentClassMapSemesterMapping where uidDepartmentClassMapSemesterMapping = :uid and jstatus=1", TDepartmentClassMapSemesterMapping.class).setParameter("uid", uid).getSingleResult();
        em.getTransaction().commit();
        return tClass != null ? Optional.of(tClass) : Optional.empty();
    }


    @SuppressWarnings("unchecked")
    public List<TDepartmentClassMapSemesterMapping> findAll() {
        return em.createQuery("from TDepartmentClassMapSemesterMapping").getResultList();
    }

    public List<TDepartmentClassMapSemesterMapping> findByClass(TDepartmentClassMapping tDepartmentClassMapping) {
        em.getTransaction().begin();
        List<TDepartmentClassMapSemesterMapping> tDepartmentClassMapSemesterMappings = em.createQuery("from TDepartmentClassMapSemesterMapping where uidDepartmentClassMappingFk = :tDepartmentClassMapping and jstatus=1", TDepartmentClassMapSemesterMapping.class).setParameter("tDepartmentClassMapping", tDepartmentClassMapping).getResultList();
        em.getTransaction().commit();
        return tDepartmentClassMapSemesterMappings;
    }

    public TDepartmentClassMapSemesterMapping update(TDepartmentClassMapSemesterMapping tDepartmentClassMapSemesterMapping) {
        em.getTransaction().begin();
        tDepartmentClassMapSemesterMapping = em.merge(tDepartmentClassMapSemesterMapping);
        em.getTransaction().commit();
        return tDepartmentClassMapSemesterMapping;
    }

    public void deleteById(String uid) {
        em.getTransaction().begin();
        em.remove(em.find(TDepartmentClassMapSemesterMapping.class, uid));
        em.getTransaction().commit();
    }

    public void close() {
        emf.close();
    }

}