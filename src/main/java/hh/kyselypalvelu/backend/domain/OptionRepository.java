package hh.kyselypalvelu.backend.domain;


import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface OptionRepository extends CrudRepository<Option, Long> {
	List<Option> findByQuestionQuestionId(Long questionId);
   
}
