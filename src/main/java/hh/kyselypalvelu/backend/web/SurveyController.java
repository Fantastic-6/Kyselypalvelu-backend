package hh.kyselypalvelu.backend.web;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import hh.kyselypalvelu.backend.domain.QuestionRepository;
import hh.kyselypalvelu.backend.domain.Response;
import hh.kyselypalvelu.backend.domain.ResponseRepository;
import hh.kyselypalvelu.backend.domain.Survey;
import hh.kyselypalvelu.backend.domain.SurveyRepository;

@Controller
public class SurveyController {
    private final SurveyRepository sRepository;
    private final QuestionRepository qRepository;
    private final ResponseRepository rRepository;

    public SurveyController(SurveyRepository sRepository, QuestionRepository qRepository,
            ResponseRepository rRepository) {
        this.sRepository = sRepository;
        this.qRepository = qRepository;
        this.rRepository = rRepository;
    }

    @GetMapping({ "/", "/surveys" })
    public String getSurveys(Model model) {
    List<Survey> surveys = (List<Survey>) sRepository.findAll();

    // count participants for each survey
    Map<Long, Long> participantCounts = new HashMap<>();
    for (Survey survey : surveys) {
        long count = rRepository.countParticipants(survey.getSurveyId());
        participantCounts.put(survey.getSurveyId(), count);
    }

    model.addAttribute("surveys", surveys);
    model.addAttribute("participantCounts", participantCounts);

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
        var survey = sRepository.findById(surveyId).orElse(null);
        model.addAttribute("survey", survey);
        model.addAttribute("questions", qRepository.findBySurvey(survey));
        return "editsurvey";
    }

}
