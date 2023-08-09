package com.example.theexamapi.service.impl;

import com.example.theexamapi.entity.TCourse;
import com.example.theexamapi.entity.TStream;
import com.example.theexamapi.repositories.TCourseRepository;
import com.example.theexamapi.service.TCourseService;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

public class TCourseServiceImpl implements TCourseService {
    private TCourseRepository courseRepository;

    @Inject
    public TCourseServiceImpl(TCourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public TCourse save(TCourse tCourse) {
        return courseRepository.save(tCourse);
    }

    @Override
    public void deleteById(String uid) {
        courseRepository.deleteById(uid);
    }

    @Override
    public Optional<TCourse> findById(String uid) {
        return courseRepository.findById(uid);
    }


    @Override
    public List<TCourse> findAll() {
        return courseRepository.findAll();
    }

    @Override
    public List<TCourse> findByStream(TStream tStream) {
        return courseRepository.findByStream(tStream);
    }

    @Override
    public TCourse update(TCourse tCourse) {
        return courseRepository.update(tCourse);
    }

}
