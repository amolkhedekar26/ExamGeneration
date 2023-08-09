package com.example.theexamapi.service.impl;


import com.example.theexamapi.entity.TStreamEntity;
import com.example.theexamapi.repo.StreamRepository;
import com.example.theexamapi.service.StreamService;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

public class StreamServiceImpl implements StreamService {
    private StreamRepository streamRepository;

    @Inject
    public StreamServiceImpl(StreamRepository streamRepository) {
        this.streamRepository = streamRepository;
    }

    @Override
    public TStreamEntity save(TStreamEntity tStream) {
        return streamRepository.save(tStream);
    }

    @Override
    public void deleteById(String uid) {
        streamRepository.deleteById(uid);
    }

    @Override
    public Optional<TStreamEntity> findById(String uid) {
        return streamRepository.findById(uid);
    }


    @Override
    public List<TStreamEntity> findAll() {
        return streamRepository.findAll();
    }

    @Override
    public TStreamEntity update(TStreamEntity tStream) {
        return streamRepository.update(tStream);
    }

}
