package com.example.theexamapi.service.impl;


import com.example.theexamapi.entity.TClassEntity;
import com.example.theexamapi.repo.ClassRepository;
import com.example.theexamapi.service.ClassService;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

public class ClassServiceImpl implements ClassService {
    private ClassRepository classRepository;

    @Inject
    public ClassServiceImpl(ClassRepository classRepository) {
        this.classRepository = classRepository;
    }

    @Override
    public TClassEntity save(TClassEntity tClass) {
        return classRepository.save(tClass);
    }

    @Override
    public void deleteById(String uid) {
        classRepository.deleteById(uid);
    }

    @Override
    public Optional<TClassEntity> findById(String uid) {
        return classRepository.findById(uid);
    }


    @Override
    public List<TClassEntity> findAll() {
        return classRepository.findAll();
    }

    @Override
    public TClassEntity update(TClassEntity tClass) {
        return classRepository.update(tClass);
    }

}
