package com.example.theexamapi.service;


import com.example.theexamapi.entity.TDepartment;
import com.example.theexamapi.entity.TDepartmentClassMapping;

import java.util.List;
import java.util.Optional;

public interface TDepartmentClassMappingService {
    TDepartmentClassMapping save(TDepartmentClassMapping tDepartmentClassMapping);

    TDepartmentClassMapping update(TDepartmentClassMapping tDepartmentClassMapping);

    void deleteById(String uid);

    Optional<TDepartmentClassMapping> findById(String uid);


    List<TDepartmentClassMapping> findAll();

    List<TDepartmentClassMapping> findByDepartment(TDepartment department);
}
