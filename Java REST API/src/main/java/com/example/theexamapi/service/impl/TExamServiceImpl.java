package com.example.theexamapi.service.impl;

import com.example.theexamapi.entity.TExam;
import com.example.theexamapi.repositories.TExamRepository;
import com.example.theexamapi.service.TExamService;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

public class TExamServiceImpl implements TExamService {
    private TExamRepository examRepository;

    @Inject
    public TExamServiceImpl(TExamRepository examRepository) {
        this.examRepository = examRepository;
    }

    @Override
    public TExam save(TExam tCourse) {
        return examRepository.save(tCourse);
    }

    @Override
    public void deleteById(String uid) {
        examRepository.deleteById(uid);
    }

    @Override
    public Optional<TExam> findById(String uid) {
        return examRepository.findById(uid);
    }


    @Override
    public List<TExam> findAll() {
        return examRepository.findAll();
    }

    @Override
    public TExam update(TExam tCourse) {
        return examRepository.update(tCourse);
    }

}
