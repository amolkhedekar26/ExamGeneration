package com.example.theexamapi.service.impl;

import com.example.theexamapi.entity.TCourseEntity;
import com.example.theexamapi.entity.TStream;
import com.example.theexamapi.repo.CourseRepository;
import com.example.theexamapi.service.CourseService;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

public class CourseServiceImpl implements CourseService {
    private CourseRepository courseRepository;

    @Inject
    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public TCourseEntity save(TCourseEntity tCourse) {
        return courseRepository.save(tCourse);
    }

    @Override
    public void deleteById(String uid) {
        courseRepository.deleteById(uid);
    }

    @Override
    public Optional<TCourseEntity> findById(String uid) {
        return courseRepository.findById(uid);
    }


    @Override
    public List<TCourseEntity> findAll() {
        return courseRepository.findAll();
    }



    @Override
    public TCourseEntity update(TCourseEntity tCourse) {
        return courseRepository.update(tCourse);
    }

}
