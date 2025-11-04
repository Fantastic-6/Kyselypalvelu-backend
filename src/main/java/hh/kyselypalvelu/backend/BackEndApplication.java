package hh.kyselypalvelu.backend;


import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;

import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.format.annotation.DateTimeFormat;

import hh.kyselypalvelu.backend.domain.Questionnaire;
import hh.kyselypalvelu.backend.domain.Question;
import hh.kyselypalvelu.backend.domain.QuestionRepository;
import hh.kyselypalvelu.backend.domain.QuestionnaireRepository;

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
    

}
