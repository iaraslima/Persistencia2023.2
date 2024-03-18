/*
Crie uma classe java de nome DesserializaXML para ler / desserializar
os objetos Serializados na Questão 4 e exibi-los.
*/

package com.Exercicio5.Q5;

import com.Exercicio5.Q1.Usuarios;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;
import java.io.IOException;

public class DesserializaXML {
    public static void main(String[] args){

        try{
            File arq = new File("ex.xml");
            XmlMapper mapa = new XmlMapper();
            Usuarios user = mapa.readValue(arq, Usuarios.class);
            System.out.println(user);
        }
        catch(IOException e){
            System.err.println(e.getMessage());
        }
    }
}
