package hh.kyselypalvelu.backend.web;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import hh.kyselypalvelu.backend.domain.ResponseRepository;
import hh.kyselypalvelu.backend.domain.Response;

public class ResponseRestController {
    private final ResponseRepository rRepository;

    public ResponseRestController(ResponseRepository rRepository) {
        this.rRepository = rRepository;
    }

    // get all responses
    @GetMapping("/api/responses")
    public @ResponseBody List<Response> responseListRest() {
        return (List<Response>) rRepository.findAll();
    }

    // get all responses to a question
    @GetMapping("/api/{questionId}/responses")
    public @ResponseBody List<Response> responsesToQuestionListRest(@PathVariable() Long questionId) {
        return (List<Response>) rRepository.findByQuestionId(questionId);
    }

    // TODO
    @PostMapping("/api/responses")
    public ResponseEntity<Response> addResponse() {
        return null;
    }
}
