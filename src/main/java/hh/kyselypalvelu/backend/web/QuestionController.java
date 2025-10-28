package hh.kyselypalvelu.backend.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class QuestionController {

    @RequestMapping(value = "/editquestionaire")
    public String getQuestionsList() {
        return "editquestionaire";
    }

    @RequestMapping("/savequestion")
    public String saveQuestion() {
        return "redirect:/editquestionaire";
    }

    @RequestMapping(value = "/addquestion")
    public String addQuestion() {
        return "addquestion";
    }

    @RequestMapping(value = "/deletequestion/{id}")
    public String deleteQuestion() {
        return "redirect:/editquestionaire";
    }

    @RequestMapping(value = "/editquestion/{id}")
    public String editQuestion() {
        return "editquestion";
    }
}
