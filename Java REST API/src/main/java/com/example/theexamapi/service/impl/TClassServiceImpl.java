package com.example.theexamapi.service.impl;


import com.example.theexamapi.entity.TClass;
import com.example.theexamapi.repositories.TClassRepository;
import com.example.theexamapi.service.TClassService;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

public class TClassServiceImpl implements TClassService {
    private TClassRepository classRepository;

    @Inject
    public TClassServiceImpl(TClassRepository classRepository) {
        this.classRepository = classRepository;
    }

    @Override
    public TClass save(TClass tClass) {
        return classRepository.save(tClass);
    }

    @Override
    public void deleteById(String uid) {
        classRepository.deleteById(uid);
    }

    @Override
    public Optional<TClass> findById(String uid) {
        return classRepository.findById(uid);
    }


    @Override
    public List<TClass> findAll() {
        return classRepository.findAll();
    }

    @Override
    public TClass update(TClass tClass) {
        return classRepository.update(tClass);
    }

}
