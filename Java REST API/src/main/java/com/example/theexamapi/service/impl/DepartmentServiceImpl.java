package com.example.theexamapi.service.impl;

import com.example.theexamapi.entity.TDepartmentEntity;
import com.example.theexamapi.repo.DepartmentRepository;
import com.example.theexamapi.service.DepartmentService;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

public class DepartmentServiceImpl implements DepartmentService {
    private DepartmentRepository departmentRepository;

    @Inject
    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public TDepartmentEntity save(TDepartmentEntity tStream) {
        return departmentRepository.save(tStream);
    }

    @Override
    public void deleteById(String uid) {
        departmentRepository.deleteById(uid);
    }

    @Override
    public Optional<TDepartmentEntity> findById(String uid) {
        return departmentRepository.findById(uid);
    }


    @Override
    public List<TDepartmentEntity> findAll() {
        return departmentRepository.findAll();
    }

    @Override
    public TDepartmentEntity update(TDepartmentEntity tStream) {
        return departmentRepository.update(tStream);
    }

}
