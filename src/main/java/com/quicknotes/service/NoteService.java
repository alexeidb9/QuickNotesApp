package com.quicknotes.service;

import com.quicknotes.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoteService {

    private final NoteRepository noteRepository;

    @Autowired
    public NoteService(final NoteRepository repository){
        this.noteRepository = repository;
    }


}
