package com.example.theexamapi.service.impl;


import com.example.theexamapi.entity.TSemester;
import com.example.theexamapi.repositories.TSemesterRepository;
import com.example.theexamapi.service.TSemesterService;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

public class TSemesterServiceImpl implements TSemesterService {
    private TSemesterRepository tSemesterRepository;

    @Inject
    public TSemesterServiceImpl(TSemesterRepository tSemesterRepository) {
        this.tSemesterRepository = tSemesterRepository;
    }

    @Override
    public TSemester save(TSemester tClass) {
        return tSemesterRepository.save(tClass);
    }

    @Override
    public void deleteById(String uid) {
        tSemesterRepository.deleteById(uid);
    }

    @Override
    public Optional<TSemester> findById(String uid) {
        return tSemesterRepository.findById(uid);
    }


    @Override
    public List<TSemester> findAll() {
        return tSemesterRepository.findAll();
    }

    @Override
    public TSemester update(TSemester tClass) {
        return tSemesterRepository.update(tClass);
    }

}
