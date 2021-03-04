package com.backend.testing.service.database.user;

import com.backend.testing.entity.User;
import com.backend.testing.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Component
public class UserData {
    private UserRepository repository;

    @Autowired
    public UserData(UserRepository repository) {
        this.repository = repository;
    }

    public int getNumberOfRecords() {
        List<User> result = repository.findAll();
        return !result.isEmpty() ? result.size() : 1;
    }

    public long getRandomId() {
        List<User> result = repository.findAll();
        Random random = new Random();
        return result.get(random.nextInt(result.size())).getId();
    }
}
