package com.ufc.exercicio7.Entity;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Produto {

    private Integer id;
    private String code;
    private String description;
    private Double price;
    private Date date;

}
