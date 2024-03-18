/*
Escreva uma aplicação Java para fazer exatamente o que foi pedido na Questão 1,
mas com uma única diferença:
A leitura e gravação devem ser realizadas
em blocos de bytes (buffer) e não byte a byte.
Dica: usar os métodos read(byte[] b) e write(byte[] b)
de FileInputStream e FileOutputStream, respectivamente.
Testar a cópia com arquivos grandes. Usar blocos de 8192 bytes. Exemplo:
        ...
        byte[] buffer = new byte[8192];
        fis.read(buffer);
        ...
Comparar o tempo de cópia de arquivos grandes usando a Questão 1 e a Questão 2.
*/

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class gravarArqBuffer {
    public static void main(String[] args) {

        //verifica se foi inserido o nome dos dois arquivos
        if (args.length != 2){
            //mensagem de erro
            System.err.println("é necessário inserir apenas o nome do arquivo origem e do arquivo destino");
            //programa encerra
            System.exit(1);
        }

        //primeiro argumento é oo nome do arquivo de origem
        String arq = args[0];
        //segundo argumento é o nome do arquivo de destino
        String arqDest = args[1];

        FileInputStream arquivo = null;
        FileOutputStream arqDestino = null;

        try{
            //leitura de dados do arquivo de origem
            arquivo = new FileInputStream(arq);
            //escrita de dados do arquivo de destino
            arqDestino = new FileOutputStream(arqDest);

            //array de bytes para armazenar os dados lidos do arquivo de origem
            byte[] buffer = new byte[8192];
            //armazena o número de bytes lidos do arq de origem
            int bytesLidos;

            //tempo inicial em milissegundos
            long tempoInicial = System.currentTimeMillis();

            //leitura de dados do arquivo origem até não haver mais nada para ser lido
            while ((bytesLidos = arquivo.read(buffer)) != -1){
                //escrita dos dados lidos do arquivo origem para o arquivo destino
                arqDestino.write(buffer, 0, bytesLidos);
            }

            //tempo final em milissegundos
            long tempoFinal = System.currentTimeMillis();
            //duração total em milissegundos
            long duracao = tempoFinal - tempoInicial;

            System.out.println(duracao + " milissegundos");

        }
        //mensagem de erro
        catch(IOException e){
            System.err.println(e.getMessage());
        }
        //executa mesmo se uma exceção for lançada
        finally{
            try{
                //se o arquivo origem não for nulo, o fluxo é fechado
                if (arquivo != null){
                    arquivo.close();
                }
                //se o arquivo de destino não for nulo, o fluxo é fechado
                if(arqDestino != null){
                    arqDestino.close();
                }
            }
            //mensagem de erro
            catch(IOException e){
                System.err.println(e.getMessage());
            }
        }
    }
}
