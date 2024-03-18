package com.ufc.exercio7;

import com.ufc.exercio7.Dao.ProdutoDAO;
import com.ufc.exercicio7.Entity.Produto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import javax.swing.*;
import java.nio.charset.Charset;
import java.sql.Date;
import java.util.List;
import java.util.Objects;

//suprime os avisos específicos do IntelliJ IDEA sobre campos autowired.
//Isso é útil quando você sabe que o aviso não se aplica ao seu caso específico.
@SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
//usado para criar um logger SLF4J isso permite que você registre mensagens
// (por exemplo, informações de depuração ou erros)
//sem ter que criar e configurar manualmente um objeto logger.
@Slf4j
//adiciona @EnableAutoConfiguration que ativa o mecanismo de autoconfiguração do Spring Boot
//adiciona @ComponentScan que ativa a varredura de componentes no pacote onde a aplicação está localizada
//adiciona @Configuration que permite registrar beans extras no contexto ou importar classes de configuração adicionais
@SpringBootApplication
public class Main implements CommandLineRunner {

    //A anotação @Autowired é usada no Spring Framework para realizar a injeção de dependência.
    // Isso significa que o Spring irá automaticamente instanciar e atribuir valores aos campos marcados
    // com @Autowired em suas classes.
    @Autowired
    ProdutoDAO baseProdutos;

    public static void main(String[] args) {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(Main.class);
        builder.headless(false).run(args);
    }

    @Override
    public void run(String... args) {

        String menu = """
                Escolha uma opção:
                1 - insira produto
                2 - atualize por ID
                3 - remova por ID
                4 - exiba por ID
                5 - exiba por código
                6 - exiba por descrição
                7 - exiba por preço
                8 - exiba por intervalo de tempo
                9 - exiba todos os produtos
                10 - sair
                """;
        String option = "0";
        do {
            try {
                Produto produto;
                String code;
                option = JOptionPane.showInputDialog(menu);
                if (option == null) System.exit(0);
                switch (option) {
                    case "1":
                        produto = new Produto();
                        obterProduto(produto);
                        baseProdutos.save(produto);
                        break;
                    case "2":
                        code = JOptionPane.showInputDialog("insira o ID do produto a ser alterado");
                        produto = baseProdutos.find(Integer.parseInt(code));
                        if(produto != null){
                            obterProduto(produto);
                            baseProdutos.save(produto);
                        }
                        else
                            JOptionPane.showMessageDialog(null, "Não foi possível atualizar: o produto não encontrado.");
                        break;
                    case "3":
                        code = JOptionPane.showInputDialog("ID");
                        produto = baseProdutos.find(Integer.parseInt(code));
                        if(produto != null) baseProdutos.delete(produto.getId());
                        else
                            JOptionPane.showMessageDialog(null, "Não foi possível remover: o produto não encontrado.");
                        break;
                    case "4":
                        code = JOptionPane.showInputDialog("ID");
                        produto = baseProdutos.find(Integer.parseInt(code));
                        listaProduto(produto);
                        break;
                    case "5":
                        code = JOptionPane.showInputDialog(new String("Código".getBytes(), Charset.defaultCharset()));
                        produto = baseProdutos.findByCode(code);
                        listaProduto(produto);
                        break;
                    case "6":
                        code = JOptionPane.showInputDialog(new String("Descrição".getBytes(), Charset.defaultCharset()));
                        listaProdutos(baseProdutos.findByDescription(code));
                        break;
                    case "7":
                        double price = Double.parseDouble(JOptionPane.showInputDialog(new String("Preço".getBytes(), Charset.defaultCharset())));
                        listaProdutos(baseProdutos.findByPrice(price));
                        break;
                    case "8":
                        Date dateS = Date.valueOf(JOptionPane.showInputDialog("data inicial"));
                        Date dateE = Date.valueOf(JOptionPane.showInputDialog("data final"));
                        listaProdutos(baseProdutos.findBetweenDates(dateS, dateE));
                        break;
                    case "9":
                        listaProdutos(baseProdutos.find());
                        break;
                    case "10":
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "opção inválida");
                        break;
                }
            } catch (NumberFormatException e) {
                log.error("Erro: {}", e.getMessage(), e);
                JOptionPane.showMessageDialog(null, "entrada inserida é inválida: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                log.error("Erro: {}", e.getMessage(), e);
                JOptionPane.showMessageDialog(null, "o formato de data correta é yyyy-mm-dd: " + e.getMessage());
            } catch (Exception e) {
                log.error("Erro: {}", e.getMessage(), e);
                JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
            }
        } while (!Objects.equals(option, "10"));
    }

    public static void obterProduto(Produto produto) {

        String code = JOptionPane.showInputDialog(new String("Código".getBytes(), Charset.defaultCharset()), produto.getCode());
        String description = JOptionPane.showInputDialog(new String("Descrição".getBytes(), Charset.defaultCharset()), produto.getDescription());
        double price = Double.parseDouble(JOptionPane.showInputDialog(new String("Preço".getBytes(), Charset.defaultCharset()), produto.getPrice()));
        Date date = Date.valueOf(JOptionPane.showInputDialog("Data", produto.getDate()));

        produto.setCode(code);
        produto.setDescription(description);
        produto.setPrice(price);
        produto.setDate(date);

    }

    public static void listaProdutos(List<Produto> produtos) {
        StringBuilder listagem = new StringBuilder();
        for (Produto produto : produtos) {
            listagem.append(produto).append("\n");
        }
        JOptionPane.showMessageDialog(null, listagem.isEmpty() ? "nenhum produto encontrado" : listagem);
    }

    public static void listaProduto(Produto produto) {
        JOptionPane.showMessageDialog(null, produto == null ? "nenhum produto encontrado" : produto);
    }

}


