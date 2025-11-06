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
public class Option {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long optionId;

    @ManyToOne
    @JsonIgnoreProperties("options")
    @JoinColumn(name = "questionId")
    private Question question;

    private String title;

    private LocalDateTime timeAdded;

    // Constructors
    public Option(Question question, String title, LocalDateTime timeAdded) {
        this.question = question;
        this.title = title;
        this.timeAdded = timeAdded;
    }

    public Option() {
    }

    // Getters and setters
    public Long getOptionId() {
        return optionId;
    }

    public void setOptionId(Long optionId) {
        this.optionId = optionId;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getTimeAdded() {
        return timeAdded;
    }

    public void setTimeAdded(LocalDateTime timeAdded) {
        this.timeAdded = timeAdded;
    }

    // toString
    @Override
    public String toString() {
        return "Option [question=" + question + ", title=" + title + ", timeAdded=" + timeAdded + "]";
    }

}
