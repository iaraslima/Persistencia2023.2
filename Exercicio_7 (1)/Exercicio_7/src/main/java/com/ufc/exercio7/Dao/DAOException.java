package com.ufc.exercio7.Dao;

//RuntimeException permite que exceções sejam lançadas durante a execução do código
public class DAOException extends RuntimeException{

    public DAOException(){
        super();
    }

    public DAOException(String message){
        super(message);
    }

    public DAOException(String message, Throwable cause){
        super(message, cause);
    }

    public DAOException(Throwable cause){
        super(cause);
    }
}
