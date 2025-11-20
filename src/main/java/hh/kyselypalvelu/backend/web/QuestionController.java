package hh.kyselypalvelu.backend.web;

import hh.kyselypalvelu.backend.domain.QuestionRepository;
import hh.kyselypalvelu.backend.domain.Option;
import hh.kyselypalvelu.backend.domain.OptionRepository;
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
    private final OptionRepository optionRepository;

    public QuestionController(QuestionRepository questionRepository, SurveyRepository surveyRepository, OptionRepository optionRepository) {
        this.questionRepository = questionRepository;
        this.surveyRepository = surveyRepository;
        this.optionRepository = optionRepository;
    }

    // --------- QUESTION CONTROLLERS ---------

    // QUESTIONS 
    @GetMapping("/{surveyId}/questions")
    public String questions(@PathVariable("surveyId") Long surveyId, Model model) {
        model.addAttribute("survey", surveyRepository.findById(surveyId));
        model.addAttribute("questions", questionRepository.findBySurvey(surveyRepository.findById(surveyId)));
        return "questions";
    }

    // SAVE QUESTION
    @PostMapping("/{surveyId}/savequestion")
    public String saveQuestion(@PathVariable("surveyId") Long surveyId, Question question) {
        questionRepository.save(question);
        return "redirect:/{surveyId}/questions";
    }
    
    @PostMapping("/{surveyId}/editquestion/savequestion")
    public String saveEditedQuestion(@PathVariable("surveyId") Long surveyId, Question question) {
        questionRepository.save(question);
        return "redirect:/{surveyId}/questions";
    }
    
    // ADD QUESTION

    @GetMapping("/{surveyId}/addquestion")
    public String addQuestion(@PathVariable("surveyId") Long surveyId, Model model) {
        Question question = new Question();
        Survey survey = surveyRepository.findById(surveyId).orElse(null);
        question.setSurvey(survey);
        model.addAttribute("question", question);
        return "addquestion";
    }

    // DELETE QUESTION
    @GetMapping("/{surveyId}/deletequestion/{questionId}")
    public String deleteQuestion(@PathVariable("surveyId") Long surveyId, @PathVariable("questionId") Long questionId) {
        questionRepository.deleteById(questionId);
        return "redirect:/{surveyId}/questions";
    }

    // EDIT QUESTION
    @GetMapping("/{surveyId}/editquestion/{questionId}")
    public String editQuestion(@PathVariable("surveyId") Long surveyId, @PathVariable("questionId") Long questionId, Model model) {
        model.addAttribute("question", questionRepository.findById(questionId));
        return "editquestion";
    }

    // --------- OPTION CONTROLLERS ---------

    // ADD OPTION (in addquestion.html)
    @GetMapping("/{surveyId}/addquestion/{questionId}/addoption")
    public String addOptionInEdit(@PathVariable("surveyId") Long surveyId, @PathVariable("questionId") Long questionId, Model model) {
        model.addAttribute("question", questionRepository.findById(questionId));
        return "redirect:/{surveyId}/addquestion";
    }

    // ADD OPTION (in editquestion.html)
      @GetMapping("/{surveyId}/editquestion/{questionId}/addoption")
    public String addOptionInAdd(@PathVariable("surveyId") Long surveyId, @PathVariable("questionId") Long questionId, Model model) {
        model.addAttribute("question", questionRepository.findById(questionId));
        return "redirect:/{surveyId}/editquestion";
    }

     // SAVE OPTION (in addquestion.html)
    @PostMapping("/{surveyId}/addquestion/saveoption")
    public String saveOptionInAdd(@PathVariable("surveyId") Long surveyId, Option option) {
        optionRepository.save(option);
        return "redirect:/{surveyId}/addquestion";
    }

    // SAVE OPTION (in editquestion.html)
    @PostMapping("/{surveyId}/editquestion/saveoption")
    public String saveOptionInEdit(@PathVariable("surveyId") Long surveyId, Option option) {
        optionRepository.save(option);
        return "redirect:/{surveyId}/editquestion";
    }

      // DELETE OPTION (in addquestion.html)
    @GetMapping("/{surveyId}/addquestion/deleteoption/{optionId}")
    public String deleteOptionInAdd(@PathVariable("surveyId") Long surveyId, @PathVariable("optionId") Long optionId) {
        optionRepository.deleteById(optionId);
        return "redirect:/{surveyId}/addquestion";
    }

    // DELETE OPTION (in editquestion.html)
    @GetMapping("/{surveyId}/editquestion/deleteoption/{optionId}")
    public String deleteOptionInEdit(@PathVariable("surveyId") Long surveyId, @PathVariable("optionId") Long optionId) {
        optionRepository.deleteById(optionId);
        return "redirect:/{surveyId}/editquestion";
    }



}
