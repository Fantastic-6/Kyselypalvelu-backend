package hh.kyselypalvelu.backend.web;

import hh.kyselypalvelu.backend.domain.QuestionRepository;
import hh.kyselypalvelu.backend.domain.Question;
import hh.kyselypalvelu.backend.domain.Survey;
import hh.kyselypalvelu.backend.domain.SurveyRepository;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class QuestionController {
    private final QuestionRepository questionRepository;
    private final SurveyRepository surveyRepository;

    public QuestionController(QuestionRepository questionRepository, SurveyRepository surveyRepository) {
        this.questionRepository = questionRepository;
        this.surveyRepository = surveyRepository;
    }
    @GetMapping("/{surveyId}/questions")
    public String questions(@PathVariable("surveyId") Long surveyId, Model model) {
        model.addAttribute("q", surveyRepository.findById(surveyId));
        model.addAttribute("questions", questionRepository.findBySurvey(surveyRepository.findById(surveyId)));
        return "questions";
    }

    @PostMapping("/{surveyId}/savequestion")
    public String saveQuestion(@PathVariable("surveyId") Long surveyId, Question question) {
        questionRepository.save(question);
        return "redirect:/{surveyId}/questions";
    }

    @GetMapping("/{surveyId}/addquestion")
    public String addQuestion(@PathVariable("surveyId") Long surveyId, Model model) {
        Question question = new Question();
        Survey survey = surveyRepository.findById(surveyId).orElse(null);
        question.setSurvey(survey);
        model.addAttribute("question", question);
        return "addquestion";
    }

    @GetMapping("/{surveyId}/deletequestion/{questionId}")
    public String deleteQuestion(@PathVariable("surveyId") Long surveyId, @PathVariable("questionId") Long questionId) {
        questionRepository.deleteById(questionId);
        return "redirect:/{surveyId}/questions";
    }

    @GetMapping("/{surveyId}/editquestion/{questionId}")
    public String editQuestion(@PathVariable("surveyId") Long surveyId, @PathVariable("questionId") Long questionId, Model model) {
        model.addAttribute("question", questionRepository.findById(questionId));
        return "editquestion";
    }
}
