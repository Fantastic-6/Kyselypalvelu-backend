package hh.kyselypalvelu.backend.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ResponseRepository extends CrudRepository<Response, Long> {
    List<Response> findByQuestionId(Long questionId);
}
