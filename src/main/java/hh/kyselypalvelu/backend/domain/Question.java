package hh.kyselypalvelu.backend.domain;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

     private Long questionId;
     @ManyToOne
     @JsonIgnoreProperties ("questions")
     @JoinColumn(name="surveyId")

     private Survey survey;
     private String questionText;
     private String questionType;
     private LocalDateTime timeAdded;
     private Boolean isRequired;
    //  private int priority;

    // Constructors
    
    public Question() {
     }

     public Question(Survey survey, String questionText, String questionType, LocalDateTime timeAdded,
            Boolean isRequired) {
        this.survey = survey;
        this.questionText = questionText;
        this.questionType = questionType;
        this.timeAdded = timeAdded;
        this.isRequired = isRequired;
     }

     //Getters and setters
     
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

     public String getQuestionType() {
         return questionType;
     }

     public void setQuestionType(String questionType) {
         this.questionType = questionType;
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

     // To string 

     @Override
     public String toString() {
        return "Question [questionId=" + questionId + ", survey=" + survey + ", questionText="
                + questionText + ", questionType=" + questionType + ", timeAdded=" + timeAdded + ", isRequired="
                + isRequired + "]";
     }

}
