/*
Crie uma classe java de nome DesserializaJSON para
ler / desserializar os objetos Serializados na Questão 6 e exibi-los
*/

package com.Exercicio5.Q7;

import com.Exercicio5.Q1.Usuarios;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DesserializaJSON{

    public static void main(String[] args){

        StringBuilder sb = new StringBuilder();

        try(BufferedReader br = new BufferedReader(new FileReader("ex.json"))){
            String s = br.readLine();
            while(s != null){
                sb.append(s);
                s = br.readLine();
            }
            //conversão da StringBuilder em uma string (arquivo JSON completo)
            String i = sb.toString();
            //conversão da string JSON em um objeto Usuarios
            Usuarios a = new ObjectMapper().readValue(i, Usuarios.class);

            System.out.println(a);
        }
        catch (IOException e){
            System.err.println(e.getMessage());
        }
    }
}
