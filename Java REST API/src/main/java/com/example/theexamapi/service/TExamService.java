package com.example.theexamapi.service;


import com.example.theexamapi.entity.TExam;

import java.util.List;
import java.util.Optional;

public interface TExamService {
    TExam save(TExam tExam);

    TExam update(TExam tExam);

    void deleteById(String uid);

    Optional<TExam> findById(String uid);


    List<TExam> findAll();

}
