package com.example.theexamapi.service.impl;


import com.example.theexamapi.entity.TDepartmentClassMapSemesterMapping;
import com.example.theexamapi.entity.TSubject;
import com.example.theexamapi.repositories.TSubjectRepository;
import com.example.theexamapi.service.TSubjectService;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

public class TSubjectServiceImpl implements TSubjectService {
    private TSubjectRepository subjectRepository;

    @Inject
    public TSubjectServiceImpl(TSubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @Override
    public TSubject save(TSubject tClass) {
        return subjectRepository.save(tClass);
    }

    @Override
    public void deleteById(String uid) {
        subjectRepository.deleteById(uid);
    }

    @Override
    public Optional<TSubject> findById(String uid) {
        return subjectRepository.findById(uid);
    }


    @Override
    public List<TSubject> findAll() {
        return subjectRepository.findAll();
    }

    @Override
    public List<TSubject> findBySemester(TDepartmentClassMapSemesterMapping tDepartmentClassMapSemesterMapping) {
        return subjectRepository.findBySemester(tDepartmentClassMapSemesterMapping);
    }

    @Override
    public TSubject update(TSubject tClass) {
        return subjectRepository.update(tClass);
    }

}
