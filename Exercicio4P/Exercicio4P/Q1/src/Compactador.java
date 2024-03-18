/*Crie uma aplicação em Java que recebe via linha de comando
(1) o nome de um arquivo compactado a ser criado e (2) uma pasta.
Compactar todos os arquivos e subpastas em um arquivo compactado com extensão zip.*/

import java.io.*;
import java.util.zip.*;

public class Compactador{
    public static void main(String[] args){
        //o usuáriuo precisará fornecer 2 argumentos
        if(args.length != 2){
            return;
        }

        //o nome da pasta que será compactada  em variáveis para fácil referência
        String nomeZip = args[0];
        File nomePasta = new File(args[1]);

        try{
            //fluxo de saída de arquivo para escrever o arquivo zip
            FileOutputStream arq = new FileOutputStream(nomeZip);
            //fluxo de saída Zip para fazer a compactação dos arquivos
            ZipOutputStream zip = new ZipOutputStream(arq);
            //método que vai compactar a pasta
            ziparPasta(nomePasta, nomePasta.getName(), zip);
            //fechando os fluxos de saída
            zip.close();
            arq.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        System.out.println("ok");
    }

    //método privado para compactar a pasta
    private static void ziparPasta(File folder, String caminho, ZipOutputStream zip) throws IOException{
        //lista de arquivos na pasta atual
        File[] arquivos = folder.listFiles();
        //se a lista de arquivos é nula?
        if(arquivos != null){
            //iteração sobre todos os arquivos na pasta
            for(File file : arquivos){
                //se o arquivo atual é um diretório
                if(file.isDirectory()){
                    //se for um diretório, chamamos o método novamente de forma recursiva
                    ziparPasta(file, caminho + "/" + file.getName(), zip);
                }
                else{
                    //se for um arquivo, o arquivo é lido e adiciona ao zip
                    byte[] buffer = new byte[1024];
                    FileInputStream fis = new FileInputStream(file);
                    zip.putNextEntry(new ZipEntry(caminho + "/" + file.getName()));
                    int length;
                    while((length = fis.read(buffer)) > 0){
                        zip.write(buffer, 0, length);
                    }
                    zip.closeEntry();
                    fis.close();
                }
            }
        }
    }
}