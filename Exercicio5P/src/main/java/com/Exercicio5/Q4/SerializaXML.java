/*
Crie uma classe Java de nome SerializaXML para instanciar objetos da classe definida
na Questão 1 e adicionar esses objetos em uma Lista.
Depois percorrer a lista e Serializar os objetos em disco/ssd.
Serialize usando a Serialização de objetos da biblioteca Jackson.
*/

package com.Exercicio5.Q4;

import com.Exercicio5.Q1.Usuario;
import com.Exercicio5.Q1.Usuarios;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class SerializaXML {

    public static void main(String[] args){

        Usuario user = new Usuario("Luiz", 19);
        Usuario user1 = new Usuario("Maria", 18);

        List<Usuario> listaUser = new LinkedList<>();
        listaUser.add(user);
        listaUser.add(user1);

        Usuarios usuarios = new Usuarios(listaUser);

        File arqXML = new File("ex.xml");

        try{
            //conversão do Java pro XML
            XmlMapper mapa = new XmlMapper();
            mapa.enable(SerializationFeature.INDENT_OUTPUT);
            mapa.writeValue(arqXML, usuarios);
        }
        catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

}
