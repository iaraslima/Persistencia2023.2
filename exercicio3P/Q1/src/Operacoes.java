/* Crie uma aplicação em Java que recebe via linha de comando:
(1) o nome de um arquivo CSV; (2) o delimitador usado para separar os campos do arquivo;
(3) uma lista de nomes das colunas do arquivo CSV que serão processados.
Considere que o arquivo CSV (1) deva ter um cabeçalho com os nomes das colunas em sua primeira linha
e que não tenha internamente colunas com Strings contendo o mesmo caractere usado como delimitador (2).
A aplicação deve exibir a soma e a média das colunas selecionadas em (3), caso tenham dados numéricos.
Se não tiverem dados numéricos, somente exibir que aquela coluna não é numérica.
Não usar bibliotecas externas para resolver esta questão (usar Java puro).
Sugere-se navegar apenas uma única vez em cada linha do arquivo CSV.
Fazer a aplicação de modo que ela possa processar arquivos CSV extremamente grandes,
mesmo que não caibam na memória RAM.
Dica: usar o método split da classe String para separar os valores das colunas em cada linha do arquivo CSV.
*/

import java.util.*;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.stream.IntStream;
import java.util.stream.Collectors;

public class Operacoes{
    public static void main(String[] args){

        //primeiro elemento é o caminho para o arquivo
        final String arqCSV = args[0];
        //segundo elemento é o delimitador usado no arquivo
        final String delimitador = args[1];

        ArrayList<String> colunas = argsSplit(args);

        //leitor vai ser fechado assim que o bloco for encerrado
        try(BufferedReader leitor = new BufferedReader(new FileReader(arqCSV))) {
            //lista de strings que retorna o valor retornado ao chamar o método asList
            List<String> todasColunas = Arrays.asList(leitor.readLine().split(delimitador));
            //lista que retorna o valor retornado ao chamar linhas
            //depois map é chamado no stream e é aplicado uma lambda em cada elemento
            //que vão receber uma string como argumento, retornando uma lista de strings
            //a lambda divide cada linha num array usando a vírgula como delimitador
            //converte-o em um stream e coleta-o numa lista
            //depois, o método collect vai ser chamado e vai ser coletado uma arraylist
            //com uma referência ao construtor da classe arraylist como argumento
            ArrayList<List<String>> linhas = leitor.lines().map(line->Arrays.stream(line.split(",")).toList()).collect(Collectors.toCollection(ArrayList::new));

            //iteração em todos os elementos de colunas
            for(String valor : colunas){
                try{
                    //indice da primeira ocorrencia de valor na coluna
                    int indice = todasColunas.indexOf(valor);
                    //contador de números nulos
                    int qtdNumNulos = 0;
                    double soma = 0;

                    //iteração em todos os elementos de linhas
                    for(var linha : linhas){
                        //retorna o elemento na ordem correta
                        String x = linha.get(indice);
                        //se x tiver esse valor, incrementa no contador de numeros nulos
                        if(x.equals("\"\"")){
                            qtdNumNulos++;
                            continue;
                        }
                        //conversao de string em double
                        //parseDouble é um método estático
                        soma = soma + Double.parseDouble(x);
                    }

                    System.out.printf("%s: \tSoma: %f\n\t\tMédia: %f\n", valor, soma, soma / (linhas.size() - qtdNumNulos));
                }
                //exceções do formato dos números
                catch(NumberFormatException e){
                    System.out.println("\n" + valor + ":\tnúmero inválido\n");
                }
            }
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    //recebimento de um array de strings como argumento e retorna um arraylist de strings
    static ArrayList<String> argsSplit(String[] args) {

        ArrayList<String> argsList = new ArrayList<>();

        //criação de um stream de inteiros usando range
        //stream de inteiros que começa com 2 e vai até args-1☺
        IntStream.range(2, args.length).forEach(value->argsList.add(args[value]));
        return argsList;

    }
}