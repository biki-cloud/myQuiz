package com.example.myquiz.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Getter
@Setter
@Entity
public class Quiz {
    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    private String htmlString;

    @Column(length = 3000)
    private String question;

    @Column(columnDefinition = "TEXT", length = 300000)
    private String choices;

    @Column(length = 3000)
    private String answer;

    @Column(length = 3000)
    private String userAnswer;

    public Quiz(Long id, String htmlString) {
        this.id = id;
        this.htmlString = htmlString;
    }

    public Quiz() {

    }
}
