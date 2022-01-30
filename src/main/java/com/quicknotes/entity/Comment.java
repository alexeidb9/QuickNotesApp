package com.quicknotes.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "comments")
public class Comment extends Note {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private double likes;

    @Override
    public String getText() {
        return super.getText();

    }
}
