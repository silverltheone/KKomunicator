package com.comunicator.kkomunicatorbackend.repository;

import com.comunicator.kkomunicatorbackend.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    @Override
    List<User> findAll();

    @Override
    Optional<User> findById(Long id);

    @Query("select c from USERS c " +
            "where c.email like :email " +
            "and c.password like :password")
    Optional<User> findByEmailAndPassword(@PathVariable String email, @PathVariable String password);

    @Override
    User save(User user);

    @Override
    void deleteById(Long id);

    @Override
    long count();
}
