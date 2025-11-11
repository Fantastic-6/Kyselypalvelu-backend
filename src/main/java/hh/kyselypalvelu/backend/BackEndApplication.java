package hh.kyselypalvelu.backend;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.kyselypalvelu.backend.domain.Question;
import hh.kyselypalvelu.backend.domain.QuestionRepository;
import hh.kyselypalvelu.backend.domain.QuestionType;
import hh.kyselypalvelu.backend.domain.QuestionTypeRepository;
import hh.kyselypalvelu.backend.domain.Survey;
import hh.kyselypalvelu.backend.domain.SurveyRepository;

@SpringBootApplication
public class BackEndApplication {

    private final QuestionTypeRepository questionTypeRepository;

    private static final org.slf4j.Logger Log = LoggerFactory.getLogger(BackEndApplication.class);

    BackEndApplication(QuestionTypeRepository questionTypeRepository) {
        this.questionTypeRepository = questionTypeRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(BackEndApplication.class, args);
    }
    

     @Bean
        public CommandLineRunner seedData(SurveyRepository surveyRepo, QuestionRepository questionRepo, QuestionTypeRepository questionTypeRepo) {
            return(args) -> {

                QuestionType radio = questionTypeRepository.save(new QuestionType("radio"));
                QuestionType checkbox = questionTypeRepository.save(new QuestionType("checkbox"));
                QuestionType range = questionTypeRepository.save(new QuestionType("range"));

                Log.info("save survey 1");
                Survey survey1 = surveyRepo.save(new Survey("Survey 1", "tosi osuva kuvaus :)", LocalTime.now(), LocalDate.now(), LocalDate.of(2025, 11,15), LocalTime.of(23, 59)));
                Log.info("save questions for survey 1");
                Question s1q1 = questionRepo.save(new Question(survey1, "Q 1: Is this a question?", LocalDateTime.now(), true, radio, 1));
                Question s1q2 = questionRepo.save(new Question(survey1, "Q 2: What is love? Select all that apply", LocalDateTime.now(), true, radio, 2));

                Log.info("save survey 2");
                Survey survey2 = surveyRepo.save(new Survey("Survey 2", "vielä osuvampi testikuvaus :D", LocalTime.now(), LocalDate.now(), LocalDate.of(2026, 07,12), LocalTime.of(12, 00)));
                Log.info("save questions for survey 2");
                Question s2q1 = questionRepo.save(new Question(survey2, "Q 1: Onko tämä kymysys?", LocalDateTime.now(), true, checkbox, 3));
                Question s2q2 = questionRepo.save(new Question(survey2, "Q 2: Valitse monta vaihtoehtoa", LocalDateTime.now(), true, range, 4));
            };
        }
}
