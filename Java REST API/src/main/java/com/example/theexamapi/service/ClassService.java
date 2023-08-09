package com.example.theexamapi.service;


import com.example.theexamapi.entity.TClassEntity;

import java.util.List;
import java.util.Optional;

public interface ClassService {
    TClassEntity save(TClassEntity tClass);

    TClassEntity update(TClassEntity tClass);

    void deleteById(String uid);

    Optional<TClassEntity> findById(String uid);


    List<TClassEntity> findAll();
}
