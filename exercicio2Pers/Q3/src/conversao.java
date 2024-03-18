/*Escreva uma aplicação Java para ler um arquivo texto com codificação ISO-8859-1
e convertê-lo para UTF-8.
Os nomes dos arquivos (origem e destino) devem ser definidos via argumentos de linha de comando
(Dica: usar o String args[] do método main).
 */
import java.io.*;
import java.nio.charset.StandardCharsets;

public class conversao{
    public static void main(String[] args) {

        //verifica se foi inserido o nome dos dois arquivos
        if(args.length != 2){
            //mensagem de erro
            System.err.println("é necessário inserir apenas o nome do arquivo origem e do arquivo destino");
            System.exit(1);
        }
        //primeiro argumento é o nome do arquivo de origem
        String arquivo = args[0];
        //segundo argumento é o nome do arquivo de destino
        String arqDest = args[1];

        //leitura (arquivo com ISO-8859-1) e escrita (arquivo com UTF-8) do arquivo de origem para o de destino
        try(BufferedReader leitor = new BufferedReader(new InputStreamReader(new FileInputStream(arquivo), StandardCharsets.ISO_8859_1));
             BufferedWriter escritor = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(arqDest), StandardCharsets.UTF_8))){

            String linha;
            //leitura de cada linha do arquivo de origem até que não tenha mais linhas para serem lidas
            while((linha = leitor.readLine()) != null){
                //escrita de cada linha lida
                escritor.write(linha);
                //inserção de uma quebra de linha no arq de destino
                escritor.newLine();
            }
        }
        catch (IOException e){
            //mensagem de erro
            System.err.println(e.getMessage());
        }
    }
}
