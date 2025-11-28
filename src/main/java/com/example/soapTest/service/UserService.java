package com.example.soapTest.service;



import com.example.soapTest.dto.User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class UserService {
    private final List<User> users = new ArrayList<>();
    private final AtomicLong counter = new AtomicLong(1);

    public UserService() {
        // Тестовые данные
        users.add(new User(counter.getAndIncrement(), "John", "Doe", "john@example.com", "+123456789", LocalDate.of(1990, 5, 15)));
        users.add(new User(counter.getAndIncrement(), "Jane", "Smith", "jane@example.com", "+987654321", LocalDate.of(1985, 8, 22)));
    }

    public User getUserById(Long id) {
        return users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    public User createUser(User user) {
        user.setId(counter.getAndIncrement());
        users.add(user);
        return user;
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(users);
    }
}
