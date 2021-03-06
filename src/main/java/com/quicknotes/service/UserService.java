package com.quicknotes.service;

import com.quicknotes.entity.User;
import com.quicknotes.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }


    public Page<User> getUsers(final int pageNumber, final int size) {

        return repository.findAll(PageRequest.of(pageNumber, size));
    }

    public Optional<User> getUser(final UUID id) {
        return repository.findById(id);
    }


    public User save(final User user) {
        return repository.save(user);
    }

    public void delete(final UUID id) {
        repository.deleteById(id);
    }


}
