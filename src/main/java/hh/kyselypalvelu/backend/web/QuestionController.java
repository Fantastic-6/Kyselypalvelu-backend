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
    Survey survey = surveyRepository.findById(surveyId).orElse(null);
    model.addAttribute("survey", survey);
    model.addAttribute("questions", questionRepository.findBySurvey(survey));
        return "questions";
    }

    // SAVE QUESTION
    @PostMapping("/{surveyId}/savequestion")
    public String saveQuestion(@PathVariable("surveyId") Long surveyId, Question question) {
        Survey survey = surveyRepository.findById(surveyId).orElse(null);
        question.setSurvey(survey);
        // save question first to get an id
        Question saved = questionRepository.save(question);
        // then persist options referencing the saved question
        if (question.getOptions() != null) {
            for (Option o : question.getOptions()) {
                o.setQuestion(saved);
                optionRepository.save(o);
            }
        }
        return "redirect:/{surveyId}/questions";
    }
    
    @PostMapping("/{surveyId}/editquestion/savequestion")
    public String saveEditedQuestion(@PathVariable("surveyId") Long surveyId, Question question) {
        // find question to edit question
        if (question.getQuestionId() == null) {
            // nothing to edit, treat as create
            return saveQuestion(surveyId, question);
        }
        Question editedQuestion = questionRepository.findById(question.getQuestionId()).orElse(null);
        if (editedQuestion == null) return "redirect:/{surveyId}/questions";
        // update fields
        editedQuestion.setQuestionText(question.getQuestionText());
        editedQuestion.setQuestionType(question.getQuestionType());
        editedQuestion.setIsRequired(question.getIsRequired());
        editedQuestion.setOrderNumber(question.getOrderNumber());
        editedQuestion.setSurvey(surveyRepository.findById(surveyId).orElse(null));
        questionRepository.save(editedQuestion);
        // delete previous options
        java.util.List<Option> options = optionRepository.findByQuestionQuestionId(editedQuestion.getQuestionId());
        if (options != null) {
            for (Option opt : options) optionRepository.deleteById(opt.getOptionId());
        }
        // save incoming options
        if (question.getOptions() != null) {
            for (Option o : question.getOptions()) {
                // skip empty or null titles (could be leftover empty inputs)
                if (o == null) continue;
                String t = o.getTitle();
                if (t == "") continue;
                if (t.trim().isEmpty()) continue;
                // treat incoming as new records (we deleted previous ones), avoid merging deleted ids
                o.setOptionId(null);
                o.setQuestion(editedQuestion);
                optionRepository.save(o);
            }
        }
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
    Question q = questionRepository.findById(questionId).orElse(null);
    if (q != null) {
        // fetch options explicitly so template can render them
        java.util.List<Option> opts = optionRepository.findByQuestionQuestionId(q.getQuestionId());
        q.setOptions(opts);
    }
    model.addAttribute("question", q);
        return "editquestion";
    }

    // DELETE OPTION
    @GetMapping("/{surveyId}/editquestion/{questionId}/deleteoption/{optionId}")
    public String deleteOption(@PathVariable("surveyId") Long surveyId, @PathVariable("questionId") Long questionId, @PathVariable("optionId") Long optionId) {
        if (optionId != null) {
                optionRepository.deleteById(optionId);
        }
        return "redirect:/{surveyId}/editquestion/{questionId}".replace("{surveyId}", String.valueOf(surveyId)).replace("{questionId}", String.valueOf(questionId));
    }
}
