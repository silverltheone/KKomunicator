package com.comunicator.kkomunicatorbackend.repository;

import com.comunicator.kkomunicatorbackend.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    @Override
    List<User> findAll();

    @Override
    Optional<User> findById(Long id);

    @Override
    List<User> findAllById(Iterable<Long> idList);

    @Query("select c from USERS c " +
            "where c.email=:email " +
            "and c.password=:password")
    Optional<User> findByEmailAndPassword(@Param("email") String email, @Param("password") String password);

    @Query("select c from USERS c " +
            "where c.email=:email ")
    Optional<User> findByEmail(@Param("email") String email);

    @Override
    User save(User user);

    @Override
    void deleteById(Long id);

    @Override
    long count();
}
