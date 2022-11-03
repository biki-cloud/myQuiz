package com.example.myquiz.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.util.List;

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

    private String question;

    private String choices;

    private String answer;

    public Quiz(Long id, String htmlString) {
        this.id = id;
        this.htmlString = htmlString;
    }

    public Quiz() {

    }
}
