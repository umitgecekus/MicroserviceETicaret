package com.umit.repository;

import com.umit.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User,String> {
    Page<User> findAllByUserNameContaining(String userName, Pageable pageable);
}
