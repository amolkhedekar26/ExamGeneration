package com.example.theexamapi.service;


import com.example.theexamapi.entity.TCourse;
import com.example.theexamapi.entity.TDepartment;
import com.example.theexamapi.entity.TStream;

import java.util.List;
import java.util.Optional;

public interface TDepartmentService {
    TDepartment save(TDepartment tDepartment);

    TDepartment update(TDepartment tDepartment);

    void deleteById(String uid);

    Optional<TDepartment> findById(String uid);


    List<TDepartment> findAll();

    List<TDepartment> findByCourse(TCourse tCourse);
}
