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
public class Response {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long responseId;

    @ManyToOne
    @JsonIgnoreProperties("responses")
    @JoinColumn(name = "questionId")
    private Question question;

    private String responseText;

    private LocalDateTime timeSubmitted;

    private String session;

    // Constructors
    public Response() {
    }

    public Response(Question question, String responseText, LocalDateTime timeSubmitted, String session) {
        this.question = question;
        this.responseText = responseText;
        this.timeSubmitted = timeSubmitted;
        this.session = session;
    }

    // Constructor without Question

    public Response(String responseText, LocalDateTime timeSubmitted, String session) {
        this.responseText = responseText;
        this.timeSubmitted = timeSubmitted;
        this.session = session;
    }

    // Setters and getters

    public Long getResponseId() {
        return responseId;
    }

    public void setResponseId(Long responseId) {
        this.responseId = responseId;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public String getResponseText() {
        return responseText;
    }

    public void setResponseText(String responseText) {
        this.responseText = responseText;
    }

    public LocalDateTime getTimeSubmitted() {
        return timeSubmitted;
    }

    public void setTimeSubmitted(LocalDateTime timeSubmitted) {
        this.timeSubmitted = timeSubmitted;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    // toString
    @Override
    public String toString() {
        return "Response [responseText=" + responseText + ", timeSubmitted=" + timeSubmitted + ", session=" + session
                + "]";
    }

}
