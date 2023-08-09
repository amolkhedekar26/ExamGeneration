package com.example.theexamapi.service.impl;

import com.example.theexamapi.entity.TStream;
import com.example.theexamapi.repositories.TStreamRepository;
import com.example.theexamapi.service.TStreamService;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

public class TStreamServiceImpl implements TStreamService {
    private TStreamRepository streamRepository;

    @Inject
    public TStreamServiceImpl(TStreamRepository streamRepository) {
        this.streamRepository = streamRepository;
    }

    @Override
    public TStream save(TStream tStream) {
        return streamRepository.save(tStream);
    }

    @Override
    public void deleteById(String uid) {
        streamRepository.deleteById(uid);
    }

    @Override
    public Optional<TStream> findById(String uid) {
        return streamRepository.findById(uid);
    }


    @Override
    public List<TStream> findAll() {
        return streamRepository.findAll();
    }

    @Override
    public TStream update(TStream tStream) {
        return streamRepository.update(tStream);
    }

}
