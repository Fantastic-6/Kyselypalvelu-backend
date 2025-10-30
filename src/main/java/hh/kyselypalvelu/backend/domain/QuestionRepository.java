package hh.kyselypalvelu.backend.domain;

import org.springframework.data.repository.CrudRepository;
import java.util.List;
import java.util.Optional;


public interface QuestionRepository extends CrudRepository<Question,Long> {
    List<Question> findByQuestionnaire(Optional<Questionnaire> questionnaire);
}
