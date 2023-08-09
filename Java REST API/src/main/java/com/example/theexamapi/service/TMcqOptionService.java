package com.example.theexamapi.service;



import com.example.theexamapi.entity.TMcqOption;
import com.example.theexamapi.entity.TQuestion;

import java.util.List;
import java.util.Optional;

public interface TMcqOptionService {
    TMcqOption save(TMcqOption tMcqOption);

    TMcqOption update(TMcqOption tMcqOption);

    void deleteById(String uid);

    Optional<TMcqOption> findById(String uid);

    List<TMcqOption> findAll();

    List<TMcqOption> findByQuestion(TQuestion tQuestion);
}
