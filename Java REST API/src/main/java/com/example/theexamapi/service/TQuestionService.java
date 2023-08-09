package com.example.theexamapi.service;



import com.example.theexamapi.entity.TQuestion;

import java.util.List;
import java.util.Optional;

public interface TQuestionService {
    TQuestion save(TQuestion tQuestion);

    TQuestion update(TQuestion tQuestion);

    void deleteById(String uid);

    Optional<TQuestion> findById(String uid);


    List<TQuestion> findAll();
}
