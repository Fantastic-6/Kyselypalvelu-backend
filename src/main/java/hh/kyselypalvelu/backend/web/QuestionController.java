package hh.kyselypalvelu.backend.web;

import hh.kyselypalvelu.backend.domain.QuestionRepository;
import hh.kyselypalvelu.backend.domain.Question;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class QuestionController {
    private final QuestionRepository questionRepository;

    public QuestionController(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @RequestMapping("/savequestion")
    public String saveQuestion(Question question) {
        questionRepository.save(question);
        return "redirect:/editsurvey/{id}";
    }

    @RequestMapping(value = "/addquestion")
    public String addQuestion(@ModelAttribute Question question) {
        questionRepository.save(question);
        return "addquestion";
    }

    @RequestMapping(value = "/deletequestion/{id}")
    public String deleteQuestion(@PathVariable("id") Long questionId) {
        questionRepository.deleteById(questionId);
        return "redirect:/editsurvey/{id}";
    }

    @RequestMapping(value = "/editquestion/{id}")
    public String editQuestion(@PathVariable("id") Long questionId, Model model) {
        model.addAttribute("question", questionRepository.findById(questionId));
        return "editquestion";
    }
}
