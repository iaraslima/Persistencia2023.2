/*
Crie uma classe Java de nome SerializaJSON para instanciar objetos
da classe definida na Questão 1 e adicionar esses objetos em uma Lista.
Depois percorrer a lista e Serializar os objetos em disco/ssd.
Serialize usando a Serialização de objetos da biblioteca Jackson
*/

package com.Exercicio5.Q6;

import com.Exercicio5.Q1.Usuario;
import com.Exercicio5.Q1.Usuarios;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.util.LinkedList;
import java.util.List;

public class SerializaJSON{

    public static void main(String[] args){

        Usuario user = new Usuario("Luiz", 19);
        Usuario user1 = new Usuario("Maria", 18);

        List<Usuario> listaUser = new LinkedList<>();
        listaUser.add(user);
        listaUser.add(user1);

        Usuarios usuarios = new Usuarios(listaUser);

        try (FileOutputStream arq = new FileOutputStream("ex.json")){

            //conversão de usuarios para uma string JSON
            String usuarioJson = new ObjectMapper().writeValueAsString(usuarios);
            //conversão da string JSON em um array de bytes
            byte[] usuarioJsonBytes = usuarioJson.getBytes();

            //fluxo pra ler dados
            ByteArrayInputStream arqBytes = new ByteArrayInputStream(usuarioJsonBytes);
            //guarda temporariamente os dados lidos
            byte[] buffer = new byte[8192];
            int bytesLidos;
            //leitura até que não haja mais dados para serem lidos
            while((bytesLidos = arqBytes.read(buffer)) != -1){
                //a escrita começa do índice 0 (buffer) até o nº de bytes lidos
                arq.write(buffer, 0, bytesLidos);
            }
            arqBytes.close();

            System.out.println("ok");
        }
        catch(Exception e){
            System.err.println(e.getMessage());
        }
    }
}
