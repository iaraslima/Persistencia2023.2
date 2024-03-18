/*
Crie uma classe java de nome DesserializaJava para
ler / desserializar os objetos Serializados na Questão 2 e exibi-los.
*/

package com.Exercicio5.Q3;

import com.Exercicio5.Q1.Usuario;
import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class DesserializaJava{

    public static void main(String[] args){

        List<Usuario> user = new LinkedList<>();

        try(FileInputStream arq = new FileInputStream("arquivo.ser")){

            try(ObjectInputStream arquivo = new ObjectInputStream(arq)){

                Usuario i = (Usuario) arquivo.readObject();

                while(i != null){
                    user.add(i);
                    //conversão do objeto retornado em readObject em um objeto Usuario
                    i = (Usuario) arquivo.readObject();
                }

            }
            finally{
                System.out.println(user);
            }

        }
        catch(IOException | ClassNotFoundException e){
            if(e instanceof EOFException){
                System.exit(0);
            }
            else{
                System.err.println(e.getMessage());
            }
        }
    }
}
