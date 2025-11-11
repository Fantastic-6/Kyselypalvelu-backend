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
    private final SurveyRepository sRepository;
    private final QuestionRepository qRepository;

    public SurveyController(SurveyRepository sRepository, QuestionRepository qRepository) {
        this.sRepository = sRepository;
        this.qRepository = qRepository;
    }


    @GetMapping("/surveys")
    public String getSurveys(Model model) {
        model.addAttribute("surveys", sRepository.findAll());
        return "surveys";
    }

    @GetMapping("/addsurvey")
    public String getAddSurvey(Model model) {
        Survey survey = new Survey();
        survey.setOpeningTime(LocalTime.now());
        survey.setOpeningDate(LocalDate.now());
        model.addAttribute("survey", survey);
        return "addsurvey";
    }

    @PostMapping("/savesurvey")
    public String saveSurvey(@ModelAttribute Survey survey) {
        sRepository.save(survey);
        return "redirect:/surveys";
    }

    @GetMapping("/deletesurvey/{surveyId}")
    public String getMethodName(@PathVariable("surveyId") Long surveyId) {
        sRepository.deleteById(surveyId);
        return "redirect:/surveys";
    }

    @GetMapping("/editsurvey/{surveyId}")
    public String getMethodName(@PathVariable("surveyId") Long surveyId, Model model) {
        model.addAttribute("survey", sRepository.findById(surveyId));
        model.addAttribute("questions", qRepository.findBySurvey(sRepository.findById(surveyId)));
        return "editsurvey";
    }
    
}
