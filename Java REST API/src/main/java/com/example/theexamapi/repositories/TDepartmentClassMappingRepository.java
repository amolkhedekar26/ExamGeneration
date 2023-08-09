package com.example.theexamapi.repositories;

import com.example.theexamapi.entity.TCourse;
import com.example.theexamapi.entity.TDepartment;
import com.example.theexamapi.entity.TDepartmentClassMapping;
import com.example.theexamapi.utilsResources;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Optional;

public class TDepartmentClassMappingRepository {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("ExamAPI");
    private EntityManager em;

    public TDepartmentClassMappingRepository() {
        em = emf.createEntityManager();
    }

    public TDepartmentClassMapping save(TDepartmentClassMapping tDepartmentClassMapping) {
        em.getTransaction().begin();
        tDepartmentClassMapping.setUidDepartmentClassMapping(utilsResources.generateUid());
        tDepartmentClassMapping.setJstatus(1);
        em.persist(tDepartmentClassMapping);
        em.getTransaction().commit();
        return tDepartmentClassMapping;
    }

    public Optional<TDepartmentClassMapping> findById(String uid) {
        em.getTransaction().begin();
//        TDepartmentClassMapping tDepartmentClassMapping = em.find(TDepartmentClassMapping.class, uid);
        TDepartmentClassMapping tDepartmentClassMapping = em.createQuery("from TDepartmentClassMapping where uidDepartmentClassMapping = :uid and jstatus=1", TDepartmentClassMapping.class).setParameter("uid",uid).getSingleResult();
        em.getTransaction().commit();
        return tDepartmentClassMapping != null ? Optional.of(tDepartmentClassMapping) : Optional.empty();
    }


    @SuppressWarnings("unchecked")
    public List<TDepartmentClassMapping> findAll() {
        return em.createQuery("from TDepartmentClassMapping").getResultList();
    }

    public List<TDepartmentClassMapping> findByDepartment(TDepartment tDepartment) {
        em.getTransaction().begin();
        List<TDepartmentClassMapping> tDepartmentClassMappings = em.createQuery("from TDepartmentClassMapping where uidDepartmentFk = :tDepartment and jstatus=1", TDepartmentClassMapping.class).setParameter("tDepartment", tDepartment).getResultList();
        em.getTransaction().commit();
        return tDepartmentClassMappings;
    }

    public TDepartmentClassMapping update(TDepartmentClassMapping tDepartmentClassMapping) {
        em.getTransaction().begin();
        tDepartmentClassMapping = em.merge(tDepartmentClassMapping);
        em.getTransaction().commit();
        return tDepartmentClassMapping;
    }

    public void deleteById(String uid) {
        em.getTransaction().begin();
        em.remove(em.find(TDepartmentClassMapping.class, uid));
        em.getTransaction().commit();
    }

    public void close() {
        emf.close();
    }



}
