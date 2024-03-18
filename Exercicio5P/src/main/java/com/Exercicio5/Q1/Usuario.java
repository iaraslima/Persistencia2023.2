/*
Crie uma classe Java de entidade.
Escolha uma entidade que você já propôs para seu Trabalho Prático.
Exemplo: classe Filme (id, titulo, sinopse, diretor).
A classe deve implementar a interface java.io.Serializable.
Crie também uma classe que possua uma lista de objetos da entidade escolhida.
Exemplo: classe Filmes, possuindo uma lista de Filme (List<Filme> filmes).
*/

package com.Exercicio5.Q1;

import java.io.Serializable;

public class Usuario implements Serializable{

    public String nome;
    public int idade;

    public Usuario() {
    }

    public Usuario(String nome, int idade){
        this.nome = nome;
        this.idade = idade;
    }

    public String getNome(){
        return nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public int getIdade(){
        return idade;
    }

    public void setIdade(int idade){
        this.idade = idade;
    }

    @Override
    public String toString(){
        return "Usuario{" +
                "nome='" + nome + '\'' +
                ", idade=" + idade +
                '}';
    }
}
