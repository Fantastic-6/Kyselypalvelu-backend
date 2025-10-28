package hh.kyselypalvelu.backend.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class QuestionnaireController {
    private final QuestionnaireRepository repository;

    public QuestionnaireController(QuestionnaireRepository repository) {
        this.repository = repository;
    }


    @GetMapping("/questionnaires")
    public String getQuestionnaires() {
        return "questionnaires";
    }

    @GetMapping("/newquestionnaire")
    public String getNewQuestionnaire() {
        return "newquestionnaire";
    }

    @PostMapping("/savequestionnaire")
    public String saveQuestionnaire(@ModelAttribute Questionnaire q) {
        repository.save(q);
        return "redirect:/questionnaires";
    }

    @GetMapping("/deletequestionnaire/{questionnaireId}")
    public String getMethodName(@PathVariable("questionnaireId") Long id) {
        repository.deleteById(id);
        return "redirect:/questionnaires";
    }
    
    @GetMapping("/editquestionnaire/{questionnaireId}")
    public String getMethodName(@PathVariable("questionnaireId") Long id, Model model) {
        model.addAttribute("q", repository.findById("id"));
        return "editquestionnaire";
    }
    
}
