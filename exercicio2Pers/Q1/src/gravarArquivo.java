/*
Escreva uma aplicação Java para ler um arquivo texto ou binário qualquer
e gravá-lo em outro arquivo (arquivo destino).
Os nomes dos arquivos (origem e destino) devem ser definidos via
argumentos de linha de comando (Dica: usar o String args[] do método main).
A leitura e gravação devem ser realizadas byte a byte.
Ao final, deve-se exibir o tempo total da cópia em milisegundos,
caso a cópia tenha sido bem sucedida.
Dica: pode-se usar o método System.currentTimeMillis().
Em caso de qualquer erro, enviar uma mensagem pela saída padrão de erro (System.err).
*/

import java.io.*;
import java.io.IOException;

public class gravarArquivo{
    public static void main(String[] args) {

        //verifica se foi inserido o nome dos dois arquivos
        if(args.length != 2){
            //mensagem de erro
            System.err.println("é necessário inserir apenas o nome do arquivo origem e do arquivo destino");
            //programa encerra
            System.exit(1);
        }

        //objeto que lê o arquivo do primeiro argumento
        try(InputStreamReader arquivo = new FileReader(args[0]);
            //objeto que escreve o arquivo do segundo argumento
            OutputStream arqDestino = new FileOutputStream(args[1])){

            //variável que armazena o tempo inicial em milissegundos
            long tempoInicial = System.currentTimeMillis();

            //armazena os dados lidos do arquivo de origem
            int dados;
            //lê os dados do arquivo de origem de byte em byte até chegar no fim do arquivo
            while ((dados = arquivo.read()) != -1){
                //escreve o que foi lido no arquivo de destino
                arqDestino.write(dados);
            }

            //variável que armazena o tempo final em milissegundos
            long tempoFinal = System.currentTimeMillis();
            //variável que armazena a duração em milissegundos
            long duracao = tempoFinal - tempoInicial;

            System.out.println(duracao + " milissegundos");

        }
        catch (IOException e){
            //mensagem de erro
            System.err.println(e.getMessage());
        }
    }
}
