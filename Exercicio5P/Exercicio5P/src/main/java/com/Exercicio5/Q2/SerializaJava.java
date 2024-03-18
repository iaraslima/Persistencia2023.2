/*
Crie uma classe Java de nome SerializaJava para instanciar objetos
da classe definida na Questão 1 e adicionar esses objetos em uma Lista.
Depois percorrer a lista e Serializar os objetos em disco/ssd.
Serialize usando a Serialização de objetos da própria API Java
*/

package com.Exercicio5.Q2;

import com.Exercicio5.Q1.Usuario;
import com.Exercicio5.Q1.Usuarios;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.List;

public class SerializaJava {

    public static void main(String[] args){

        Usuario user = new Usuario("Luiz", 19);
        Usuario user1 = new Usuario("Maria", 18);

        List<Usuario> listaUser = new LinkedList<>();
        listaUser.add(user);
        listaUser.add(user1);

        Usuarios usuarios = new Usuarios(listaUser);

        //escrita de dados no arquivo
        try(FileOutputStream arq = new FileOutputStream("arquivo.ser")){

            //criação de objeto para escrever objetos no fluxo de saída
            try(ObjectOutputStream criaArq = new ObjectOutputStream(arq)){
                for(Usuario u : usuarios.getUsuarios()){
                    criaArq.writeObject(u);
                    System.out.println("Serializado: " + u);
                }
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
