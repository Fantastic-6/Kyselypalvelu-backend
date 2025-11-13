package hh.kyselypalvelu.backend;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.kyselypalvelu.backend.domain.Option;
import hh.kyselypalvelu.backend.domain.OptionRepository;
import hh.kyselypalvelu.backend.domain.Question;
import hh.kyselypalvelu.backend.domain.QuestionRepository;
import hh.kyselypalvelu.backend.domain.Question.QuestionType;
import hh.kyselypalvelu.backend.domain.Survey;
import hh.kyselypalvelu.backend.domain.SurveyRepository;

@SpringBootApplication
public class BackEndApplication {
    private static final org.slf4j.Logger Log = LoggerFactory.getLogger(BackEndApplication.class);

    BackEndApplication() {
    }

    public static void main(String[] args) {
        SpringApplication.run(BackEndApplication.class, args);
    }
    
     @Bean
        public CommandLineRunner seedData(SurveyRepository surveyRepo, QuestionRepository questionRepo, OptionRepository optionRepo) {
            return(args) -> {
                Log.info("save survey 1");
                Survey survey1 = surveyRepo.save(new Survey("Survey 1", "tosi osuva kuvaus :)", LocalTime.now(), LocalDate.now(), LocalDate.of(2025, 11,15), LocalTime.of(23, 59)));
                Log.info("save questions for survey 1");
                Question s1q1 = questionRepo.save(new Question(survey1, "Q 1: Is this a question?", LocalDateTime.now(), true, QuestionType.TEXT, 1));
                Question s1q2 = questionRepo.save(new Question(survey1, "Q 2: What is love? Select one", LocalDateTime.now(), true, QuestionType.RADIO, 2));
                Log.info("save options for survey1 question 2");
                optionRepo.save(new Option(s1q2, "Baby don't hurt me", LocalDateTime.now()));
                optionRepo.save(new Option(s1q2, "Don't hurt me", LocalDateTime.now()));
                optionRepo.save(new Option(s1q2, "No more", LocalDateTime.now()));

                Log.info("save survey 2");
                Survey survey2 = surveyRepo.save(new Survey("Survey 2", "vielä osuvampi testikuvaus :D", LocalTime.now(), LocalDate.now(), LocalDate.of(2026, 07,12), LocalTime.of(12, 00)));
                Log.info("save questions for survey 2");
                Question s2q1 = questionRepo.save(new Question(survey2, "Q 1: Choose one or more", LocalDateTime.now(), true, QuestionType.CHECKBOX, 3));
                Log.info("save options for survey2 question 1");
                optionRepo.save(new Option(s2q1, "Option 1", LocalDateTime.now()));
                optionRepo.save(new Option(s2q1, "Option 2", LocalDateTime.now()));
                optionRepo.save(new Option(s2q1, "Option 3", LocalDateTime.now()));
                Question s2q2 = questionRepo.save(new Question(survey2, "Q 2: How's your current mood?", LocalDateTime.now(), true, QuestionType.SCALE, 4));
            };
        }
}
