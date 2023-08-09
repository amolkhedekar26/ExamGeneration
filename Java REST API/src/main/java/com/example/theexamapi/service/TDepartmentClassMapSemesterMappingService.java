package com.example.theexamapi.service;


import com.example.theexamapi.entity.TClass;
import com.example.theexamapi.entity.TDepartmentClassMapSemesterMapping;
import com.example.theexamapi.entity.TDepartmentClassMapping;

import java.util.List;
import java.util.Optional;

public interface TDepartmentClassMapSemesterMappingService {
    TDepartmentClassMapSemesterMapping save(TDepartmentClassMapSemesterMapping tDepartmentClassMapSemesterMapping);

    TDepartmentClassMapSemesterMapping update(TDepartmentClassMapSemesterMapping tDepartmentClassMapSemesterMapping);

    void deleteById(String uid);

    Optional<TDepartmentClassMapSemesterMapping> findById(String uid);


    List<TDepartmentClassMapSemesterMapping> findAll();

    List<TDepartmentClassMapSemesterMapping> findByClass(TDepartmentClassMapping tDepartmentClassMapping);
}
