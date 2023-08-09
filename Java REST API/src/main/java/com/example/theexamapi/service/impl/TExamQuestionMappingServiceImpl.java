package com.example.theexamapi.service.impl;

import com.example.theexamapi.entity.TExamQuestionMapping;
import com.example.theexamapi.repositories.TExamQuestionMappingRepository;
import com.example.theexamapi.service.TExamQuestionMappingService;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

public class TExamQuestionMappingServiceImpl implements TExamQuestionMappingService {
    private TExamQuestionMappingRepository tExamQuestionMappingRepository;

    @Inject
    public TExamQuestionMappingServiceImpl(TExamQuestionMappingRepository tExamQuestionMappingRepository) {
        this.tExamQuestionMappingRepository = tExamQuestionMappingRepository;
    }

    @Override
    public TExamQuestionMapping save(TExamQuestionMapping tCourse) {
        return tExamQuestionMappingRepository.save(tCourse);
    }

    @Override
    public void deleteById(String uid) {
        tExamQuestionMappingRepository.deleteById(uid);
    }

    @Override
    public Optional<TExamQuestionMapping> findById(String uid) {
        return tExamQuestionMappingRepository.findById(uid);
    }


    @Override
    public List<TExamQuestionMapping> findAll() {
        return tExamQuestionMappingRepository.findAll();
    }

    @Override
    public TExamQuestionMapping update(TExamQuestionMapping tCourse) {
        return tExamQuestionMappingRepository.update(tCourse);
    }

}
