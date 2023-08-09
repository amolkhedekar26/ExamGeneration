package com.example.theexamapi.service;


import com.example.theexamapi.entity.TCourse;
import com.example.theexamapi.entity.TCourseEntity;
import com.example.theexamapi.entity.TStream;

import java.util.List;
import java.util.Optional;

public interface TCourseService {
    TCourse save(TCourse tStream);

    TCourse update(TCourse tStream);

    void deleteById(String uid);

    Optional<TCourse> findById(String uid);


    List<TCourse> findAll();

    List<TCourse> findByStream(TStream tStream);

}
