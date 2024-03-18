/* Crie uma aplicação em Java que recebe via linha de comando
(1) o nome de um arquivo a ser decriptado e
(2) o nome do arquivo resultante da decriptação e
(3) a chave de decriptação.*/

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.io.*;
import java.nio.file.Files;

public class Desencriptador {
    public static void main(String[] args){
        //verifica a quantidade de argumentos
        if (args.length != 3) {
            System.out.println("São necessários 3 argumentos");
            return;
        }

        //guardando os argumentos em variáveis
        File entrada = new File(args[0]);
        File saida = new File(args[1]);
        String senha = args[2];

        try {
            //criação de especificações para criptografar e descriptografar
            DESKeySpec desKeySpec = new DESKeySpec(senha.getBytes());
            //aqui poderão ser criadas várias especificações pra conversão em uma SecretKey
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey key = keyFactory.generateSecret(desKeySpec);

            //cifra pra desencriptar a partir da chave
            Cipher cifra = Cipher.getInstance("DES");
            cifra.init(Cipher.DECRYPT_MODE, key);

            //leitura de todos os bytes o arquivo a ser encriptado
            byte[] bytesInicio = Files.readAllBytes(entrada.toPath());
            //encriptação
            byte[] bytesFinal = cifra.doFinal(bytesInicio);
            //escrita dos bytes encriptados no arquivo final
            try(FileOutputStream escrita = new FileOutputStream(saida)){
                escrita.write(bytesFinal);
            }
            System.out.println("ok");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
