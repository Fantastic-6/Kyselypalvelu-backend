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
import hh.kyselypalvelu.backend.domain.Survey;
import hh.kyselypalvelu.backend.domain.SurveyRepository;

@SpringBootApplication
public class BackEndApplication {

    private static final org.slf4j.Logger Log = LoggerFactory.getLogger(BackEndApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(BackEndApplication.class, args);
    }
    
        @Bean
        public CommandLineRunner saveData(QuestionnaireRepository questionnaireRepo, QuestionRepository questionRepo) {
            return(args) -> {
                Log.info("save some questionnaries");

                questionRepo.save(new Question(null, null, null, null, null));
                questionnaireRepo.save(new Questionnaire("test1", LocalTime.now(), LocalDate.now(), LocalDate.of(2025, 11,15), null);
            }
        }
    

     @Bean
        public CommandLineRunner seedData(SurveyRepository surveyRepo, QuestionRepository questionRepo) {
            return(args) -> {
                
                Log.info("save survey 1");
                Survey survey1 = surveyRepo.save(new Survey("Survey 1", LocalTime.now(), LocalDate.now(), LocalDate.of(2025, 11,15), null));
                Log.info("save questions for survey 1");
                Question s1q1 = questionRepo.save(new Question(survey1, "Q 1: Is this a question?", "Radio Button", LocalDateTime.now(), true));
                Question s1q2 = questionRepo.save(new Question(survey1, "Q 2: What is love?", "Checkbox", LocalDateTime.now(), true));

                Log.info("save survey 2");
                Survey survey2 = surveyRepo.save(new Survey("Survey 2", LocalTime.now(), LocalDate.now(), LocalDate.of(2026, 07,12), null));
                Log.info("save questions for survey 2");
                Question s2q1 = questionRepo.save(new Question(survey2, "Q 1: Is this a question?", "radio", LocalDateTime.now(), true));
                Question s2q2 = questionRepo.save(new Question(survey2, "Q 2: What is love?", "checkbox", LocalDateTime.now(), true));
            };
        }
}
