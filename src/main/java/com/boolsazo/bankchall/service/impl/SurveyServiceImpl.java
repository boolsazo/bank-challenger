package com.boolsazo.bankchall.service.impl;

import com.boolsazo.bankchall.domain.Survey;
import com.boolsazo.bankchall.repository.SurveyRepository;
import com.boolsazo.bankchall.service.SurveyService;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SurveyServiceImpl implements SurveyService {

    @Autowired
    private SurveyRepository repository;

    @Override
    public void registerSurvey(Survey vo) {
        repository.save(vo);
    }

    @Override
    public Survey showSurvey(int userId) {
        return repository.findById(userId)
                   .orElseThrow(() -> new NoSuchElementException("Survey Not Found"));
    }
}
