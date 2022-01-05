package com.lukas8219.pollbe.helper.stub;

import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class GenericIncrementalRepositoryStub<T, ID> implements CrudRepository<T, ID> {

    protected final List<T> database;

    public GenericIncrementalRepositoryStub() {
        this.database = new ArrayList<>(5000);
    }

    public GenericIncrementalRepositoryStub(ArrayList<T> database) {
        this.database = database;
    }

    @Override
    public <S extends T> S save(S entity) {
        return entity;
    }

    @Override
    public <S extends T> Iterable<S> saveAll(Iterable<S> entities) {
        return entities;
    }

    @Override
    public Optional<T> findById(ID id) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(ID id) {
        return false;
    }

    @Override
    public Iterable<T> findAll() {
        return null;
    }

    @Override
    public Iterable<T> findAllById(Iterable<ID> ids) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(ID id) {

    }

    @Override
    public void delete(T entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends ID> ids) {

    }

    @Override
    public void deleteAll(Iterable<? extends T> entities) {

    }

    @Override
    public void deleteAll() {

    }
}
