package hh.kyselypalvelu.backend.web;

import org.springframework.web.bind.annotation.RestController;

import hh.kyselypalvelu.backend.domain.Question;
import hh.kyselypalvelu.backend.domain.QuestionRepository;
import hh.kyselypalvelu.backend.domain.SurveyRepository;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController
public class QuestionRestController {
    private final QuestionRepository qRepository;
    private final SurveyRepository sRepository;

    public QuestionRestController(QuestionRepository qRepository, SurveyRepository sRepository) {
        this.qRepository = qRepository;
        this.sRepository = sRepository;
    }

    // gets all questions by survey
    @GetMapping("/api/{surveyId}/questions")
    public @ResponseBody List<Question> surveyListRest(@PathVariable("surveyId") Long surveyId) {
        return (List<Question>) qRepository.findBySurvey(sRepository.findById(surveyId).orElse(null));
    }

}
