package com.example.theexamapi.service.impl;


import com.example.theexamapi.entity.TDepartment;
import com.example.theexamapi.entity.TDepartmentClassMapping;
import com.example.theexamapi.repositories.TDepartmentClassMappingRepository;
import com.example.theexamapi.service.TDepartmentClassMappingService;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

public class TDepartmentClassMappingServiceImpl implements TDepartmentClassMappingService {
    private TDepartmentClassMappingRepository tDepartmentClassMappingRepository;

    @Inject
    public TDepartmentClassMappingServiceImpl(TDepartmentClassMappingRepository tDepartmentClassMappingRepository) {
        this.tDepartmentClassMappingRepository = tDepartmentClassMappingRepository;
    }

    @Override
    public TDepartmentClassMapping save(TDepartmentClassMapping tDepartmentClassMapping) {
        return tDepartmentClassMappingRepository.save(tDepartmentClassMapping);
    }

    @Override
    public void deleteById(String uid) {
        tDepartmentClassMappingRepository.deleteById(uid);
    }

    @Override
    public Optional<TDepartmentClassMapping> findById(String uid) {
        return tDepartmentClassMappingRepository.findById(uid);
    }


    @Override
    public List<TDepartmentClassMapping> findAll() {
        return tDepartmentClassMappingRepository.findAll();
    }

    @Override
    public List<TDepartmentClassMapping> findByDepartment(TDepartment department) {
        return tDepartmentClassMappingRepository.findByDepartment(department);
    }

    @Override
    public TDepartmentClassMapping update(TDepartmentClassMapping tDepartmentClassMapping) {
        return tDepartmentClassMappingRepository.update(tDepartmentClassMapping);
    }

}
