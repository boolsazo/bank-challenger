package com.boolsazo.bankchall.service.impl;

import com.boolsazo.bankchall.domain.Survey;
import com.boolsazo.bankchall.repository.SurveyRepository;
import com.boolsazo.bankchall.service.SurveyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SurveyServiceImpl implements SurveyService {

    private SurveyRepository surveyRepository;

    @Override
    public void registerSurvey(Survey vo) {
        surveyRepository.save(vo);
    }
}
