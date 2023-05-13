package com.boolsazo.bankchall.service;

import com.boolsazo.bankchall.domain.Survey;

public interface SurveyService {

    void registerSurvey(Survey vo);

    Survey showSurvey(int userId);
}
