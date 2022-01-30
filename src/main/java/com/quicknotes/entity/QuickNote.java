package com.quicknotes.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class QuickNote extends Note {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private long id;


}
