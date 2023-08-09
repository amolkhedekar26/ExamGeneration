package com.example.theexamapi.service;


import com.example.theexamapi.entity.TClass;

import java.util.List;
import java.util.Optional;

public interface TClassService {
    TClass save(TClass tClass);

    TClass update(TClass tClass);

    void deleteById(String uid);

    Optional<TClass> findById(String uid);


    List<TClass> findAll();
}
