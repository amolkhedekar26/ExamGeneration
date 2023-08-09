package com.example.theexamapi.service.impl;


import com.example.theexamapi.entity.TCourse;
import com.example.theexamapi.entity.TDepartment;
import com.example.theexamapi.repositories.TDepartmentRepository;
import com.example.theexamapi.service.TDepartmentService;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

public class TDepartmentServiceImpl implements TDepartmentService {
    private TDepartmentRepository departmentRepository;

    @Inject
    public TDepartmentServiceImpl(TDepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public TDepartment save(TDepartment tDepartment) {
        return departmentRepository.save(tDepartment);
    }

    @Override
    public void deleteById(String uid) {
        departmentRepository.deleteById(uid);
    }

    @Override
    public Optional<TDepartment> findById(String uid) {
        return departmentRepository.findById(uid);
    }


    @Override
    public List<TDepartment> findAll() {
        return departmentRepository.findAll();
    }

    @Override
    public List<TDepartment> findByCourse(TCourse tCourse) {
        return departmentRepository.findByCourse(tCourse);
    }

    @Override
    public TDepartment update(TDepartment tDepartment) {
        return departmentRepository.update(tDepartment);
    }

}
