package hh.kyselypalvelu.backend.domain;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface SurveyRepository extends CrudRepository<Survey, Long> {
    Survey findBySurveyId(Long surveyId);

    List<Survey> findByOpeningDateBeforeAndDeadlineDateAfter(
            LocalDate openingDate, LocalDate deadlineDate);
}
