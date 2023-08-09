package com.example.theexamapi.service;


import com.example.theexamapi.entity.TExamQuestionMapping;

import java.util.List;
import java.util.Optional;

public interface TExamQuestionMappingService {
    TExamQuestionMapping save(TExamQuestionMapping tExamQuestionMapping);

    TExamQuestionMapping update(TExamQuestionMapping tExamQuestionMapping);

    void deleteById(String uid);

    Optional<TExamQuestionMapping> findById(String uid);


    List<TExamQuestionMapping> findAll();

}
