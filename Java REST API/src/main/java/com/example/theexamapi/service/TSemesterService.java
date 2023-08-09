package com.example.theexamapi.service;


import com.example.theexamapi.entity.TSemester;

import java.util.List;
import java.util.Optional;

public interface TSemesterService {
    TSemester save(TSemester tSemester);

    TSemester update(TSemester tSemester);

    void deleteById(String uid);

    Optional<TSemester> findById(String uid);


    List<TSemester> findAll();
}
