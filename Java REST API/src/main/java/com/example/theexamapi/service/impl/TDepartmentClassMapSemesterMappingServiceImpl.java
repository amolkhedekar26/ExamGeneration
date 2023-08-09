package com.example.theexamapi.service.impl;


import com.example.theexamapi.entity.TDepartmentClassMapSemesterMapping;
import com.example.theexamapi.entity.TDepartmentClassMapping;
import com.example.theexamapi.repositories.TDepartmentClassMapSemesterMappingRepository;
import com.example.theexamapi.service.TDepartmentClassMapSemesterMappingService;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

public class TDepartmentClassMapSemesterMappingServiceImpl implements TDepartmentClassMapSemesterMappingService {
    private TDepartmentClassMapSemesterMappingRepository tDepartmentClassMapSemesterMappingRepository;

    @Inject
    public TDepartmentClassMapSemesterMappingServiceImpl(TDepartmentClassMapSemesterMappingRepository tDepartmentClassMapSemesterMappingRepository) {
        this.tDepartmentClassMapSemesterMappingRepository = tDepartmentClassMapSemesterMappingRepository;
    }

    @Override
    public TDepartmentClassMapSemesterMapping save(TDepartmentClassMapSemesterMapping tDepartmentClassMapSemesterMapping) {
        return tDepartmentClassMapSemesterMappingRepository.save(tDepartmentClassMapSemesterMapping);
    }

    @Override
    public void deleteById(String uid) {
        tDepartmentClassMapSemesterMappingRepository.deleteById(uid);
    }

    @Override
    public Optional<TDepartmentClassMapSemesterMapping> findById(String uid) {
        return tDepartmentClassMapSemesterMappingRepository.findById(uid);
    }


    @Override
    public List<TDepartmentClassMapSemesterMapping> findAll() {
        return tDepartmentClassMapSemesterMappingRepository.findAll();
    }

    @Override
    public List<TDepartmentClassMapSemesterMapping> findByClass(TDepartmentClassMapping tDepartmentClassMapping) {
        return tDepartmentClassMapSemesterMappingRepository.findByClass(tDepartmentClassMapping);
    }

    @Override
    public TDepartmentClassMapSemesterMapping update(TDepartmentClassMapSemesterMapping tDepartmentClassMapSemesterMapping) {
        return tDepartmentClassMapSemesterMappingRepository.update(tDepartmentClassMapSemesterMapping);
    }

}
