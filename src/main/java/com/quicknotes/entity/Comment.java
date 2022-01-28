package com.quicknotes.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Comment extends Note {

    @Override
    public String getText() {
        return super.getText();


    }
}
