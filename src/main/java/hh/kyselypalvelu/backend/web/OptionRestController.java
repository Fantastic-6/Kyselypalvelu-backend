package hh.kyselypalvelu.backend.web;

import org.springframework.web.bind.annotation.RestController;

import hh.kyselypalvelu.backend.domain.Option;
import hh.kyselypalvelu.backend.domain.OptionRepository;
import hh.kyselypalvelu.backend.domain.QuestionRepository;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController
public class OptionRestController {
    private final OptionRepository oRepository;
    private final QuestionRepository qRepository;

    public OptionRestController(OptionRepository oRepository, QuestionRepository qRepository) {
        this.oRepository = oRepository;
        this.qRepository = qRepository;
    }

    // gets all options by question
    @GetMapping("/api/{questionId}/options")
    public @ResponseBody List<Option> optionListRest(@PathVariable("questionId") Long questionId) {
        return (List<Option>) oRepository.findByQuestion(qRepository.findById(questionId).orElse(null));
    }

    // gets one option by id
    @GetMapping("/api/option/{optionId}")
    public @ResponseBody Option optionRest(@PathVariable() Long optionId) {
        return oRepository.findByOptionId(optionId);
    }

}
