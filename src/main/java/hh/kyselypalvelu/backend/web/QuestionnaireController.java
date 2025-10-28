package hh.kyselypalvelu.backend.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import hh.kyselypalvelu.backend.domain.Questionnaire;
import hh.kyselypalvelu.backend.domain.QuestionnaireRepository;

@Controller
public class QuestionnaireController {
    private final QuestionnaireRepository repository;

    public QuestionnaireController(QuestionnaireRepository repository) {
        this.repository = repository;
    }


    @GetMapping("/questionnaires")
    public String getQuestionnaires(Model model) {
        model.addAttribute("qs", repository.findAll());
        return "questionnaires";
    }

    @GetMapping("/newquestionnaire")
    public String getNewQuestionnaire(Model model) {
        model.addAttribute("q", new Questionnaire());
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
        model.addAttribute("q", repository.findById(id));
        return "editquestionnaire";
    }
    
}
