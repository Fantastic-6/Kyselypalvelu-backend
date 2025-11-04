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
public class Survey {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long surveyId;
    private String title;
    @DateTimeFormat(pattern = "hh:mm")
    private LocalTime timePublished;
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private LocalDate datePublished;
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private LocalDate deadline;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "survey")
    @JsonIgnoreProperties("survey")
    private List<Question> questions;

    // Constructors

    public Survey() {
    }

    public Survey(String title, LocalTime publishTime, LocalDate publishDate, LocalDate deadline, List<Question> questions) {
        this.title = title;
        this.timePublished = publishTime;
        this.datePublished = publishDate;
        this.deadline = deadline;
        this.questions = questions;
    }

    // Getters and setters

    public Long getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(Long id) {
        this.surveyId = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalTime getTimePublished() {
        return timePublished;
    }

    public void setTimePublished(LocalTime timePublished) {
        this.timePublished = timePublished;
    }

    public LocalDate getDatePublished() {
        return datePublished;
    }

    public void setDatePublished(LocalDate datePublished) {
        this.datePublished = datePublished;
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
        return "Survey [id=" + surveyId + ", title=" + title + ", publishTime=" + timePublished + ", deadline="
                + deadline + "]";
    }

}
