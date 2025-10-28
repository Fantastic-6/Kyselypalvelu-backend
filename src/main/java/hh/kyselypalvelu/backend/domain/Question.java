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
     @JoinColumn(name="questionnaireId")

     private Questionnaire questionnaire;
     private String questionText;
     private String questionType;
     private LocalDateTime timeAdded;
     private Boolean isRequired;
    //  private int priority;

    // Constructors
    
    public Question() {
     }

     public Question(Questionnaire questionnaire, String questionText, String questionType, LocalDateTime timeAdded,
            Boolean isRequired) {
        this.questionnaire = questionnaire;
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

     public Questionnaire getQuestionnaire() {
         return questionnaire;
     }

     public void setQuestionnaire(Questionnaire questionnaire) {
         this.questionnaire = questionnaire;
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
        return "Question [questionId=" + questionId + ", questionnaire=" + questionnaire + ", questionText="
                + questionText + ", questionType=" + questionType + ", timeAdded=" + timeAdded + ", isRequired="
                + isRequired + "]";
     }

}
