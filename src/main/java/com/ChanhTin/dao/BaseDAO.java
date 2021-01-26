package com.ChanhTin.dao;

import com.ChanhTin.model.ThiSinh;

import java.util.List;

public interface BaseDAO<G> {

    List<G> getAll()  throws Exception;

    List<G> getAllPagination(int start, int total) throws Exception;

    boolean save(G object) throws Exception;

    boolean update(int id, G object) throws Exception;

    boolean delete(int id) throws Exception;

    G findById(int id) throws Exception;

    List<ThiSinh> danhSachThiSinh() throws Exception;
}
