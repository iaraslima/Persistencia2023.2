package com.ufc.exercio7.Dao;

import com.ufc.exercicio7.Entity.Produto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

//suprime os avisos específicos do IntelliJ IDEA sobre campos autowired.
//Isso é útil quando você sabe que o aviso não se aplica ao seu caso específico.
@SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
//permite que o Spring forneça serviços de tradução de exceções de tempo de execução.
@Repository
//este repositório deve ter prioridade quando houver múltiplos beans candidatos para autowiring
@Primary
//usado para criar um logger SLF4J
@Slf4j

public class ProdutoSpringJDBCDAO implements ProdutoDAO{

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public void save(Produto entity){
        String insert = "insert into produtos (code, description, price, date) values (:code, :dcpt, :price, :date)";
        String update = "update produtos set code = :code, description = :dcpt, price = :price, date = :date where id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("code", entity.getCode())
                .addValue("dcpt", entity.getDescription())
                .addValue("price", entity.getPrice())
                .addValue("date", entity.getDate());
        if(entity.getId() == null){
            jdbcTemplate.update(insert, params);
        }
        else{
            params.addValue("id", entity.getId());
            jdbcTemplate.update(update, params);
        }
    }

    @Override
    public void delete(int id){

        try{
            String delete = "delete from produtos where id = :id";
            MapSqlParameterSource params = new MapSqlParameterSource()
                    .addValue("id", id);
            jdbcTemplate.update(delete, params);
        }
        catch(EmptyResultDataAccessException e){
            log.debug(e.getMessage());
        }
    }

    @Override
    public Produto find(int id){

        Produto produto = null;

        try{
            String select = "select id, code, description, price, quantity, date from produtos where id = :id";
            MapSqlParameterSource params = new MapSqlParameterSource()
                    .addValue("id", id);
            produto = jdbcTemplate.queryForObject(select, params, (rs, rowNum) -> map(rs));
        }
        catch(EmptyResultDataAccessException e){
            log.debug(e.getMessage());
        }
        return produto;
    }

    @Override
    public List<Produto> find(){

        try{
            String select = "select id, code, description, price, quantity, date from produtos";
            return jdbcTemplate.query(select, (rs, rowNum) -> map(rs));
        }
        catch(EmptyResultDataAccessException e){
            log.debug(e.getMessage());
        }
        return null;
    }

    @Override
    public Produto findByCode(String code){

        Produto produto = null;

        try{
            String select = "select * from produtos where upper(code) like :code";
            MapSqlParameterSource params = new MapSqlParameterSource()
                    .addValue("code", "%" + code.toUpperCase() + "%");
            produto = jdbcTemplate.queryForObject(select, params, (rs, rowNum) -> map(rs));
        }
        catch(EmptyResultDataAccessException e){
            log.debug(e.getMessage());
        }
        return produto;
    }

    @Override
    public List<Produto> findByDescription(String description){

        try {
            String select = "select * from produtos where upper(description) like :description";
            MapSqlParameterSource params = new MapSqlParameterSource()
                    .addValue("description", "%" + description.toUpperCase() + "%");
            return jdbcTemplate.query(select, params, (rs, rowNum) -> map(rs));
        }
        catch(EmptyResultDataAccessException e){
            log.debug(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Produto> findByPrice(Double price){

        try{
            String select = "select * from produtos where price <= :price";
            MapSqlParameterSource params = new MapSqlParameterSource()
                    .addValue("price", price);
            return jdbcTemplate.query(select, params, (rs, rowNum) -> map(rs));
        }
        catch(EmptyResultDataAccessException e){
            log.debug(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Produto> findBetweenDates(Date start, Date end){

        try{
            String select = "select * from produtos where produtos.date between :start and :end";
            MapSqlParameterSource params = new MapSqlParameterSource()
                    .addValue("start", start)
                    .addValue("end", end);
            return jdbcTemplate.query(select, params, (rs, rowNum) -> map(rs));
        }
        catch(EmptyResultDataAccessException e){
            log.debug(e.getMessage());
        }
        return null;
    }

    private Produto map(ResultSet set) throws SQLException{
        Produto produto = new Produto();
        produto.setId(set.getInt("id"));
        produto.setCode(set.getString("code"));
        produto.setDescription(set.getString("description"));
        produto.setPrice(set.getBigDecimal("price").doubleValue());
        produto.setDate(set.getDate("date"));
        return produto;
    }
}
