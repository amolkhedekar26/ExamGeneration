package com.example.theexamapi.repo;


import com.example.theexamapi.entity.TDepartmentEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Optional;

public class DepartmentRepository {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("ExamAPI");
    private EntityManager em;

    public DepartmentRepository() {
        em = emf.createEntityManager();
    }

    public TDepartmentEntity save(TDepartmentEntity tDepartment) {
        em.getTransaction().begin();
        tDepartment.setState((byte) 1);
        em.persist(tDepartment);
        em.getTransaction().commit();
        return tDepartment;
    }

    public Optional<TDepartmentEntity> findById(String uid) {
        em.getTransaction().begin();
//        TDepartmentEntity tDepartment = em.find(TDepartmentEntity.class, uid);
        TDepartmentEntity tDepartment = em.createQuery("from TDepartmentEntity where uid = :uid and state=1", TDepartmentEntity.class).setParameter("uid",uid).getSingleResult();
        em.getTransaction().commit();
        return tDepartment != null ? Optional.of(tDepartment) : Optional.empty();
    }


    @SuppressWarnings("unchecked")
    public List<TDepartmentEntity> findAll() {
        return em.createQuery("from TDepartmentEntity").getResultList();
    }

    public TDepartmentEntity update(TDepartmentEntity tDepartment) {
        em.getTransaction().begin();
        tDepartment = em.merge(tDepartment);
        em.getTransaction().commit();
        return tDepartment;
    }

    public void deleteById(String uid) {
        em.getTransaction().begin();
        em.remove(em.find(TDepartmentEntity.class, uid));
        em.getTransaction().commit();
    }

    public void close() {
        emf.close();
    }

}
