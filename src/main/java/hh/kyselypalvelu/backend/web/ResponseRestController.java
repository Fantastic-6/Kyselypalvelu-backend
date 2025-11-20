package hh.kyselypalvelu.backend.web;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import hh.kyselypalvelu.backend.domain.ResponseRepository;
import hh.kyselypalvelu.backend.domain.Response;

public class ResponseRestController {
    private final ResponseRepository rRepository;

    public ResponseRestController(ResponseRepository rRepository) {
        this.rRepository = rRepository;
    }

    @GetMapping("/api/responses")
    public @ResponseBody List<Response> ResponseListRest() {
        return (List<Response>) rRepository.findAll();
    }

    //TODO
    @PostMapping("/api/responses")
    public ResponseEntity<Response> AddResponse() {
        return null;
    }
}
