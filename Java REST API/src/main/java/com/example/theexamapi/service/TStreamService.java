package com.example.theexamapi.service;



import com.example.theexamapi.entity.TStream;

import java.util.List;
import java.util.Optional;

public interface TStreamService {
    TStream save(TStream tStream);

    TStream update(TStream tStream);

    void deleteById(String uid);

    Optional<TStream> findById(String uid);


    List<TStream> findAll();
}
