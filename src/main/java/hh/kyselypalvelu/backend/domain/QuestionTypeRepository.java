package hh.kyselypalvelu.backend.domain;

import org.springframework.data.repository.CrudRepository;
import java.util.List;


public interface QuestionTypeRepository extends CrudRepository<QuestionType,Long> {
    List<QuestionType> findByTypeName(String typeName);
    
}
