package com.example.theexamapi.service.impl;

import com.example.theexamapi.entity.TMcqOption;
import com.example.theexamapi.entity.TQuestion;
import com.example.theexamapi.repositories.TMcqOptionRepository;
import com.example.theexamapi.service.TMcqOptionService;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

public class TMcqOptionServiceImpl implements TMcqOptionService {
    private TMcqOptionRepository mcqOptionRepository;

    @Inject
    public TMcqOptionServiceImpl(TMcqOptionRepository mcqOptionRepository) {
        this.mcqOptionRepository = mcqOptionRepository;
    }

    @Override
    public TMcqOption save(TMcqOption tMcqOption) {
        return mcqOptionRepository.save(tMcqOption);
    }

    @Override
    public void deleteById(String uid) {
        mcqOptionRepository.deleteById(uid);
    }

    @Override
    public Optional<TMcqOption> findById(String uid) {
        return mcqOptionRepository.findById(uid);
    }


    @Override
    public List<TMcqOption> findAll() {
        return mcqOptionRepository.findAll();
    }

    @Override
    public List<TMcqOption> findByQuestion(TQuestion tQuestion) {
        return mcqOptionRepository.findByQuestion(tQuestion);
    }

    @Override
    public TMcqOption update(TMcqOption tMcqOption) {
        return mcqOptionRepository.update(tMcqOption);
    }

}
