package com.example.theexamapi.service;


import com.example.theexamapi.entity.TDepartmentClassMapSemesterMapping;
import com.example.theexamapi.entity.TSubject;

import java.util.List;
import java.util.Optional;

public interface TSubjectService {
    TSubject save(TSubject tSubject);

    TSubject update(TSubject tSubject);

    void deleteById(String uid);

    Optional<TSubject> findById(String uid);


    List<TSubject> findAll();

    List<TSubject> findBySemester(TDepartmentClassMapSemesterMapping tDepartmentClassMapSemesterMapping);
}
