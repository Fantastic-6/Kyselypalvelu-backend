package hh.kyselypalvelu.backend.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Questionnaire {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long questionnaireId;
    private String title;
    private String description;
    @DateTimeFormat(pattern = "hh:mm")
    private LocalTime openingTime;
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private LocalDate openingDate;
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private LocalDate deadline;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "questionnaire")
    @JsonIgnoreProperties("questionnaire")
    private List<Question> questions;

    // Constructors

    public Questionnaire() {
    }

    public Questionnaire(String title, String description, LocalTime openingTime, LocalDate openingDate, LocalDate deadline, List<Question> questions) {
        this.title = title;
        this.description = description;
        this.openingTime = openingTime;
        this.openingDate = openingDate;
        this.deadline = deadline;
        this.questions = questions;
    }

    // Getters and setters

    public Long getQuestionnaireId() {
        return questionnaireId;
    }

    public void setQuestionnaireId(Long id) {
        this.questionnaireId = id;
    }

    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public LocalTime getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(LocalTime timePublished) {
        this.openingTime = timePublished;
    }

    public LocalDate getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(LocalDate datePublished) {
        this.openingDate = datePublished;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    // To string
    @Override
    public String toString() {
        return "Questionnaire [questionnaireId=" + questionnaireId + ", title=" + title + ", description=" + description
                + ", openingTime=" + openingTime + ", openingDate=" + openingDate + ", deadline=" + deadline + "]";
    }
}
