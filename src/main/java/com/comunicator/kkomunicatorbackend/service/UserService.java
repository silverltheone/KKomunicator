package com.comunicator.kkomunicatorbackend.service;

import com.comunicator.kkomunicatorbackend.controller.UserNotFoundException;
import com.comunicator.kkomunicatorbackend.domain.User;
import com.comunicator.kkomunicatorbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<User> getAll() {
        return repository.findAll();
    }

    public User getOne(Long id) throws UserNotFoundException {
        return repository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    public User getByEmailAndPassword(String email, String password) throws UserNotFoundException {
        return repository.findByEmailAndPassword(email, password).orElseThrow((UserNotFoundException::new));
    }

    public User create(User user) {
        return repository.save(user);
    }

    public User update(User user) {
        Optional<User> userOptional = repository.findById(user.getId());
        if(userOptional.isPresent()) {
            User updatedUser = userOptional.get();
            updatedUser.setId(user.getId());
            updatedUser.setFirstName(user.getFirstName());
            updatedUser.setLastName(user.getLastName());
            updatedUser.setEmail(user.getEmail());
            updatedUser.setPassword(user.getPassword());
            updatedUser.setFriends(user.getFriends());
            updatedUser.setFriendOf(user.getFriendOf());
            updatedUser.setSentMessages(user.getSentMessages());
            updatedUser.setReceivedMessages(user.getReceivedMessages());
            repository.save(updatedUser);
            return updatedUser;
        } else {
            throw new RuntimeException("Cannot find message with id: " + user.getId());
        }
    }

    public boolean delete(Long id) throws UserNotFoundException{
        User user = repository.findById(id).orElseThrow(UserNotFoundException::new);
        repository.delete(user);
        return !repository.existsById(id);
    }
}
