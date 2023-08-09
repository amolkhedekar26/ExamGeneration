package com.example.theexamapi.service;


import com.example.theexamapi.entity.TDepartmentEntity;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {
    TDepartmentEntity save(TDepartmentEntity tStream);

    TDepartmentEntity update(TDepartmentEntity tStream);

    void deleteById(String uid);

    Optional<TDepartmentEntity> findById(String uid);


    List<TDepartmentEntity> findAll();
}
