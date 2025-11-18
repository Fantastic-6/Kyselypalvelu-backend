package hh.kyselypalvelu.backend.web;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import hh.kyselypalvelu.backend.domain.Survey;
import hh.kyselypalvelu.backend.domain.SurveyRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class SurveyRestController {
    private final SurveyRepository sRepository;

    public SurveyRestController(SurveyRepository sRepository) {
        this.sRepository = sRepository;
    }

    @GetMapping("/api/surveys")
    public @ResponseBody List<Survey> surveyListRest() {
        LocalDate nowDate = LocalDate.now();
	return (List<Survey>) sRepository.findByOpeningDateBeforeAndDeadlineDateAfter(nowDate, nowDate);
}

    @GetMapping("/api/survey/{surveyId}")
    public @ResponseBody Survey surveyRest(@PathVariable("surveyId") Long surveyId) {
	return sRepository.findBySurveyId(surveyId);
}    
}
