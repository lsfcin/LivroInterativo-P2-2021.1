import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LeitorDeArquivos {

    public HashMap<String, Personagem> carregarPersonagens(String caminho) {
        HashMap<String, Personagem> personagens = new HashMap<String, Personagem>();

        File arquivo = new File(caminho);
        try {
            Scanner escaneadorDeArquivos = new Scanner(arquivo, "UTF-8");

            System.out.println("Carregando personagens...");
            int i = 0;
            while (escaneadorDeArquivos.hasNextLine()) {
                i++;
                String id = escaneadorDeArquivos.nextLine().toLowerCase(); // ID
                String nome = escaneadorDeArquivos.nextLine(); // Nome
                int energia = Integer.parseInt(escaneadorDeArquivos.nextLine());

                escaneadorDeArquivos.nextLine(); // \n
                System.out.println("Personagem " + i);

                personagens.put(id, new Personagem(nome, energia));
            }
            escaneadorDeArquivos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return personagens;
    }

    public HashMap<String, Capitulo> carregarCapitulos(
            String caminho,
            Map<String, Personagem> personagens,
            Scanner escaneadorDoConsole) {

        HashMap<String, Capitulo> capitulos = new HashMap<String, Capitulo>();

        File arquivo = new File(caminho);
        try {
            Scanner escaneadorDoArquivo = new Scanner(arquivo, "UTF-8");

            System.out.println("Carregando capitulos...");
            String linha = escaneadorDoArquivo.nextLine();
            while (escaneadorDoArquivo.hasNextLine()) {
                if (linha.equals("CAPITULO") ||
                    linha.equals("CAPITULO_COM_IMAGEM")) {
                    
                    escaneadorDoArquivo.nextLine(); // ID
                    String id = escaneadorDoArquivo.nextLine().toLowerCase();

                    if(linha.equals("CAPITULO")) 
                    {
                        capitulos.put(id, new Capitulo(personagens, escaneadorDoConsole, escaneadorDoArquivo));
                    } 
                    else if(linha.equals("CAPITULO_COM_IMAGEM"))
                    {
                        capitulos.put(id, new CapituloImagem(personagens, escaneadorDoConsole, escaneadorDoArquivo));
                    }

                    System.out.println("Capitulo " + id);
                    escaneadorDoArquivo.nextLine(); // \n

                } else if (linha.equals("ESCOLHA")) {
                    lerEscolha(capitulos, escaneadorDoArquivo);
                }
                linha = escaneadorDoArquivo.nextLine();
            }

            escaneadorDoArquivo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return capitulos;
    }

    private void lerEscolha(
            HashMap<String, Capitulo> capitulos,
            Scanner escaneadorDeArquivo) {
        escaneadorDeArquivo.nextLine(); // DE
        String idCapituloDe = escaneadorDeArquivo.nextLine().toLowerCase();
        escaneadorDeArquivo.nextLine(); // PARA
        String idCapituloPara = escaneadorDeArquivo.nextLine().toLowerCase();
        escaneadorDeArquivo.nextLine(); // TEXTO DIGITADO
        String textoDigitado = escaneadorDeArquivo.nextLine();
        escaneadorDeArquivo.nextLine(); // TEXTO MOSTRADO
        String textoMostrado = escaneadorDeArquivo.nextLine();

        capitulos.get(idCapituloDe).adicionarEscolha(
                new Escolha(
                        textoDigitado,
                        textoMostrado,
                        capitulos.get(idCapituloPara)));
    }

    // void salvarPersonagens(...)
}
