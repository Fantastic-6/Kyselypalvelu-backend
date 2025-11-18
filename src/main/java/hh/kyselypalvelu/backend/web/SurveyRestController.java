package hh.kyselypalvelu.backend.web;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import hh.kyselypalvelu.backend.domain.Survey;
import hh.kyselypalvelu.backend.domain.SurveyRepository;

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
    public @ResponseBody List<Survey> studentListRest() {
	return (List<Survey>) sRepository.findAll();
}

    @GetMapping("/api/survey/{surveyId}")
    public @ResponseBody Survey studentRest(@PathVariable("surveyId") Long surveyId) {
	return sRepository.findBySurveyId(surveyId);
}    
}
