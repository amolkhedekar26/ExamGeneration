package com.example.theexamapi.service.impl;

import com.example.theexamapi.entity.TQuestion;
import com.example.theexamapi.repositories.TQuestionRepository;
import com.example.theexamapi.service.TQuestionService;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

public class TQuestionServiceImpl implements TQuestionService {
    private TQuestionRepository questionRepository;

    @Inject
    public TQuestionServiceImpl(TQuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public TQuestion save(TQuestion tStream) {
        return questionRepository.save(tStream);
    }

    @Override
    public void deleteById(String uid) {
        questionRepository.deleteById(uid);
    }

    @Override
    public Optional<TQuestion> findById(String uid) {
        return questionRepository.findById(uid);
    }


    @Override
    public List<TQuestion> findAll() {
        return questionRepository.findAll();
    }

    @Override
    public TQuestion update(TQuestion tStream) {
        return questionRepository.update(tStream);
    }

}
