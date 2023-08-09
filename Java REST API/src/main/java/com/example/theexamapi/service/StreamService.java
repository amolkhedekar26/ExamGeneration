package com.example.theexamapi.service;


import com.example.theexamapi.entity.TStreamEntity;

import java.util.List;
import java.util.Optional;

public interface StreamService {
    TStreamEntity save(TStreamEntity tStream);

    TStreamEntity update(TStreamEntity tStream);

    void deleteById(String uid);

    Optional<TStreamEntity> findById(String uid);


    List<TStreamEntity> findAll();
}
