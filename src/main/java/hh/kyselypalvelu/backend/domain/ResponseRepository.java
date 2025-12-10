package hh.kyselypalvelu.backend.domain;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ResponseRepository extends CrudRepository<Response, Long> {
    List<Response> findByQuestionQuestionId(Long questionId);
    Response findByResponseId(Long responseId);

    // count number of distinct participants (sessions) for a survey. JPA named method didn't work, so using @Query instead
    @Query("""
        SELECT COUNT(DISTINCT r.session)
        FROM Response r
        WHERE r.question.survey.surveyId = :surveyId
    """)
    long countParticipants(@Param("surveyId") Long surveyId);
    List<Response> findByQuestionSurveySurveyId(Long surveyId);
}
