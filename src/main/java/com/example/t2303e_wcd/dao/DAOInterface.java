package com.example.t2303e_wcd.dao;

import com.example.t2303e_wcd.entity.Student;

import java.util.List;

public interface DAOInterface<S> {
    List<S> all();
    boolean create(S s);
    boolean update(S s);
    boolean delete(S s);
    <K> S find(K id);
}
