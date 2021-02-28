package com.darek.giza.userservice.repository;

import com.darek.giza.userservice.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public UserRepositoryImpl(@Lazy MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public User create(User user) {
        return mongoTemplate.save(user);
    }

    @Override
    public Optional<User> findById(String id) {
        return Optional.ofNullable(mongoTemplate.findOne(Query.query(Criteria.where("id").is(id)), User.class));
    }

    @Override
    public void deleteById(String id) {
        mongoTemplate.findAndRemove(Query.query(Criteria.where("id").is(id)), User.class);
    }

    @Override
    public List<User> getAll() {
        return mongoTemplate.findAll(User.class);
    }
}