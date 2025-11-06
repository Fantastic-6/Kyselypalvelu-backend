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
import hh.kyselypalvelu.backend.domain.Survey;
import hh.kyselypalvelu.backend.domain.SurveyRepository;

@Controller
public class SurveyController {
    private final SurveyRepository repository;
    private final QuestionRepository qRepository;

    public SurveyController(SurveyRepository repository, QuestionRepository qRepository) {
        this.repository = repository;
        this.qRepository = qRepository;
    }


    @GetMapping("/surveys")
    public String getSurveys(Model model) {
        model.addAttribute("qs", repository.findAll());
        return "surveys";
    }

    @GetMapping("/addsurvey")
    public String getAddSurvey(Model model) {
        Survey que = new Survey();
        que.setOpeningTime(LocalTime.now());
        que.setOpeningDate(LocalDate.now());
        model.addAttribute("q", que);
        return "addsurvey";
    }

    @PostMapping("/savesurvey")
    public String saveSurvey(@ModelAttribute Survey q) {
        repository.save(q);
        return "redirect:/surveys";
    }

    @GetMapping("/deletesurvey/{surveyId}")
    public String getMethodName(@PathVariable() Long surveyId) {
        repository.deleteById(surveyId);
        return "redirect:/surveys";
    }

    @GetMapping("/editsurvey/{surveyId}")
    public String getMethodName(@PathVariable() Long surveyId, Model model) {
        model.addAttribute("q", repository.findById(surveyId));
        model.addAttribute("questions", qRepository.findBySurvey(repository.findById(surveyId)));
        return "editsurvey";
    }
    
}
