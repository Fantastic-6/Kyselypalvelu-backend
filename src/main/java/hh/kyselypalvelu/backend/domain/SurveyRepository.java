package hh.kyselypalvelu.backend.domain;

import org.springframework.data.repository.CrudRepository;



public interface SurveyRepository extends CrudRepository<Survey, Long> {
    Survey findBySurveyId(Long surveyId);
}
