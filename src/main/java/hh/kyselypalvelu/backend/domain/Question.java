package hh.kyselypalvelu.backend.domain;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long questionId;

    @ManyToOne
    @JsonIgnoreProperties("questions")
    @JoinColumn(name = "surveyId")

    private Survey survey;

    private String questionText;

    private LocalDateTime timeAdded;

    private Boolean isRequired;

    public enum QuestionType {
        TEXT, RADIO, CHECKBOX, SCALE
    };
    private QuestionType questionType;

    private int orderNumber;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "question")
    @JsonIgnoreProperties("question")
    private List<Option> options;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "question")
    @JsonIgnoreProperties("question")
    private List<Response> responses;

    // Constructors
    public Question() {
    }

    public Question(Survey survey, String questionText, LocalDateTime timeAdded,
            Boolean isRequired, QuestionType questionType, int orderNumber) {
        this.survey = survey;
        this.questionText = questionText;
        this.timeAdded = timeAdded;
        this.isRequired = isRequired;
        this.questionType = questionType;
        this.orderNumber = orderNumber;
    }

    // Getters and setters
    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Survey getSurvey() {
        return survey;
    }

    public void setSurvey(Survey survey) {
        this.survey = survey;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public LocalDateTime getTimeAdded() {
        return timeAdded;
    }

    public void setTimeAdded(LocalDateTime timeAdded) {
        this.timeAdded = timeAdded;
    }

    public Boolean getIsRequired() {
        return isRequired;
    }

    public void setIsRequired(Boolean isRequired) {
        this.isRequired = isRequired;
    }

    public QuestionType getQuestionType() {
        return questionType;
    }

    public void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }
    // To string

    @Override
    public String toString() {
        return "Question [questionId=" + questionId + ", survey=" + survey + ", questionText=" + questionText
                + ", timeAdded=" + timeAdded + ", isRequired=" + isRequired + ", questionType=" + questionType
                + ", orderNumber=" + orderNumber + "]";
    }

}
