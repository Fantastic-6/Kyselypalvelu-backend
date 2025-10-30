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
        return "redirect:/editquestionnaire/{questionnaireId}";
    }

    @RequestMapping(value = "/addquestion")
    public String addQuestion(@ModelAttribute Question question) {
        questionRepository.save(question);
        return "addquestion";
    }

    @RequestMapping(value = "/deletequestion/{questionId}")
    public String deleteQuestion(@PathVariable("questionId") Long questionId) {
        questionRepository.deleteById(questionId);
        return "redirect:/editquestionnaire/{questionnaireId}";
    }

    @RequestMapping(value = "/editquestion/{questionId}")
    public String editQuestion(@PathVariable("questionId") Long questionId, Model model) {
        model.addAttribute("question", questionRepository.findById(questionId));
        return "editquestion";
    }
}
