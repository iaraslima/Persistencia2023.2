/*Escreva uma aplicação Java para obter via teclado uma sequência de linhas de texto
 até que uma linha contendo somente a String "FIM" seja digitada.
 Depois disso, solicitar o nome do arquivo via teclado e
 salvar todas as linhas de texto digitadas no arquivo solicitado.
 A linha contendo a String "FIM" não deve ser salva no arquivo.
 */
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class finish {
    public static void main(String[] args) {

        //leitura dos dados inseridos pelo usuário
        Scanner scanner = new Scanner(System.in);
        //lista que armazena as linhas lidas do texto inseridas pelo usuário
        List<String> linhas = new ArrayList<>();

        //armazena cada linha lida
        String linha;
        //uma linha será adicionada à lista linhas até que a linha seja != FIM
        while(!(linha = scanner.nextLine()).equals("FIM")) {
            linhas.add(linha);
        }
        //leitura do nome do arquivo
        String nomeArq = scanner.nextLine();

        //escrita no arquivo dito pelo usuário
        try(FileWriter writer = new FileWriter(nomeArq)) {
            //for (int i = 0; i < linhas.size(); i++){
            //  writer.write(linhas.get(i) + "\n");
            //}
            //
            for (String lines : linhas){
                writer.write(lines + "\n");
            }
        }
        //mensagem de erro
        catch(IOException e){
            System.err.println(e.getMessage());
        }
    }
}