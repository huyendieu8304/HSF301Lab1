package com.dieu.lab1.repository;

public interface ICrudRepository<T, K> {
    boolean save (T entity);
    T findById(K id);
    void update(T entity);
    void delete(T entity);
}
