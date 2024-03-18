package com.ufc.exercio7.Dao;

import com.ufc.exercicio7.Entity.Produto;

import java.sql.Date;
import java.util.List;

public interface ProdutoDAO {

    void save(Produto entity);

    void delete(int id);

    Produto find(int id);

    List<Produto> find();

    Produto findByCode(String nome);

    List<Produto> findByDescription(String description);

    List<Produto> findByPrice(Double price);

    List<Produto> findBetweenDates(Date start, Date end);

}
