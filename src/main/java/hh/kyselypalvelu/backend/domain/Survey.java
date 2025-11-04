package hh.kyselypalvelu.backend.domain;

import java.time.LocalDate;
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
public class Survey {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long surveyId;

    private String title;

    private String description;

    @DateTimeFormat(pattern = "hh:mm")
    private LocalTime openingTime;

    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private LocalDate openingDate;

    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private LocalDate deadlineDate;

    @DateTimeFormat(pattern = "hh:mm")
    private LocalTime deadlineTime;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "survey")
    @JsonIgnoreProperties("survey")
    private List<Question> questions;

    // Constructors

    public Survey() {
    }

    public Survey(String title, LocalTime openingTime, LocalDate openingDate, LocalDate deadlineDate, LocalTime deadlineTime) {
        this.title = title;
        this.openingTime = openingTime;
        this.openingDate = openingDate;
        this.deadlineDate = deadlineDate;
        this.deadlineTime = deadlineTime;
    }

    // Getters and setters


    public Long getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(Long surveyId) {
        this.surveyId = surveyId;
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

    public void setOpeningTime(LocalTime openingTime) {
        this.openingTime = openingTime;
    }

    public LocalDate getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(LocalDate openingDate) {
        this.openingDate = openingDate;
    }

    public LocalDate getDeadlineDate() {
        return deadlineDate;
    }

    public void setDeadlineDate(LocalDate deadlineDate) {
        this.deadlineDate = deadlineDate;
    }

    public LocalTime getDeadlineTime() {
        return deadlineTime;
    }

    public void setDeadlineTime(LocalTime deadlineTime) {
        this.deadlineTime = deadlineTime;
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
        return "Survey [title=" + title + ", description=" + description + ", openingTime=" + openingTime
                + ", openingDate=" + openingDate + ", deadlineDate=" + deadlineDate + ", deadlineTime=" + deadlineTime
                + "]";
    }

    
}
