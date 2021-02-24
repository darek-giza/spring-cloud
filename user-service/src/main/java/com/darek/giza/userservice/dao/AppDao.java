package com.darek.giza.userservice.dao;

import java.util.List;

public interface AppDao<T> {
    List<T> getAll();
    T create(T t);
    T findById(String id);
    T deleteById(String id);
}
