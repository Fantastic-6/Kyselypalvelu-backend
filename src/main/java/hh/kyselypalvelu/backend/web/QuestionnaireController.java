package hh.kyselypalvelu.backend.web;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import hh.kyselypalvelu.backend.domain.QuestionRepository;
import hh.kyselypalvelu.backend.domain.Questionnaire;
import hh.kyselypalvelu.backend.domain.QuestionnaireRepository;

@Controller
public class QuestionnaireController {
    private final QuestionnaireRepository repository;
    private final QuestionRepository qRepository;

    public QuestionnaireController(QuestionnaireRepository repository, QuestionRepository qRepository) {
        this.repository = repository;
        this.qRepository = qRepository;
    }


    @GetMapping("/questionnaires")
    public String getQuestionnaires(Model model) {
        model.addAttribute("qs", repository.findAll());
        return "questionnaires";
    }

    @GetMapping("/newquestionnaire")
    public String getNewQuestionnaire(Model model) {
        Questionnaire que = new Questionnaire();
        que.setTimePublished(LocalTime.now());
        que.setDatePublished(LocalDate.now());
        model.addAttribute("q", que);
        return "newquestionnaire";
    }

    @PostMapping("/savequestionnaire")
    public String saveQuestionnaire(@ModelAttribute Questionnaire q) {
        repository.save(q);
        return "redirect:/questionnaires";
    }

    @GetMapping("/deletequestionnaire/{questionnaireId}")
    public String getMethodName(@PathVariable() Long questionnaireId) {
        repository.deleteById(questionnaireId);
        return "redirect:/questionnaires";
    }
    
    @GetMapping("/editquestionnaire/{questionnaireId}")
    public String getMethodName(@PathVariable() Long questionnaireId, Model model) {
        model.addAttribute("q", repository.findById(questionnaireId));
        model.addAttribute("questions", qRepository.findByQuestionnaire(repository.findById(questionnaireId)));
        return "editquestionnaire";
    }
    
}
