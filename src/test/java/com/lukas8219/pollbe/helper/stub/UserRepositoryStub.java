package com.lukas8219.pollbe.helper.stub;

import com.lukas8219.pollbe.data.domain.User;

import java.util.*;
import java.util.stream.Collectors;

public class UserRepositoryStub implements com.lukas8219.pollbe.repository.UserRepository {

    private final Map<Long, User> database;

    public UserRepositoryStub(Map<Long, User> database) {
        this.database = database;
    }

    public UserRepositoryStub() {
        this.database = new HashMap<>();
    }

    @Override
    public Optional<User> findByEmail(String username) {
        return database.values()
                .stream()
                .filter(user -> user.getEmail() != null && user.getEmail().equals(username))
                .findFirst();
    }

    @Override
    public boolean emailAlreadyExists(String email, Long id) {
        return database.values()
                .stream()
                .filter(x -> !Objects.equals(x.getId(), id) && x.getEmail().equals(email))
                .count() > 0;
    }

    @Override
    public <S extends User> S save(S entity) {
        if (entity.getId() == null) {
            entity.setId((long) database.size());
        }
        database.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public <S extends User> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<User> findById(Long aLong) {
        return Optional.ofNullable(database.get(aLong));
    }

    @Override
    public boolean existsById(Long aLong) {
        return database.containsKey(aLong);
    }

    @Override
    public Iterable<User> findAll() {
        return database.values();
    }

    @Override
    public Iterable<User> findAllById(Iterable<Long> longs) {
        return database.values().stream()
                .filter(user -> {
                    var ids = new ArrayList<Long>();
                    longs.forEach(ids::add);
                    return ids.contains(user.getId());
                })
                .collect(Collectors.toList());
    }

    @Override
    public long count() {
        return database.size();
    }

    @Override
    public void deleteById(Long aLong) {
        database.remove(aLong);
    }

    @Override
    public void delete(User entity) {
        database.remove(entity.getId());
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {
        longs.forEach(this::deleteById);
    }

    @Override
    public void deleteAll(Iterable<? extends User> entities) {
        entities.forEach(this::delete);
    }

    @Override
    public void deleteAll() {
        database.clear();
    }
}
