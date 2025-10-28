package hh.kyselypalvelu.backend.domain;

import java.time.LocalDateTime;
import java.util.List;

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
    private LocalDateTime timePublished;
    private LocalDateTime deadline;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "questionnaire")
    @JsonIgnoreProperties("questionnaire")
    private List<Question> questions;

    // Constructors

    public Questionnaire() {
    }

    public Questionnaire(String title, LocalDateTime publishTime, LocalDateTime deadline, List<Question> questions) {
        this.title = title;
        this.timePublished = publishTime;
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

    public LocalDateTime getTimePublished() {
        return timePublished;
    }

    public void setTimePublished(LocalDateTime publishTime) {
        this.timePublished = publishTime;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
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
        return "Questionnaire [id=" + questionnaireId + ", title=" + title + ", publishTime=" + timePublished + ", deadline="
                + deadline + "]";
    }

}
