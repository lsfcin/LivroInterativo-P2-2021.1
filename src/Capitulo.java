import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.Objects;

public class Capitulo {
    private String texto;
    protected ArrayList<Escolha> escolhas;
    private Personagem personagem1;
    private Personagem personagem2;
    private int variacaoEnergiaPersonagem1;
    private int variacaoEnergiaPersonagem2;

    protected Capitulo() 
    {
        this.texto = "";
        this.escolhas = new ArrayList<Escolha>();
    }

    public Capitulo(String texto,
            Personagem personagem1,
            Personagem personagem2,
            int variacaoEnergiaPersonagem1,
            int variacaoEnergiaPersonagem2) 
    {
        this.texto = texto;
        this.personagem1 = personagem1;
        this.personagem2 = personagem2;
        this.variacaoEnergiaPersonagem1 = variacaoEnergiaPersonagem1;
        this.variacaoEnergiaPersonagem2 = variacaoEnergiaPersonagem2;
        this.escolhas = new ArrayList<Escolha>();
    }

    public Capitulo(
            Map<String, Personagem> personagens,
            Scanner escaneadorDoArquivo) 
    {
        this.lerCapitulo(personagens, escaneadorDoArquivo);
        this.escolhas = new ArrayList<Escolha>();
    }

    public String getTexto() {
        return this.texto;
    }

    protected void lerCapitulo(
            Map<String, Personagem> personagens,
            Scanner escaneadorDoArquivo) {

        escaneadorDoArquivo.nextLine(); // PERSONAGEM
        String idPersonagem1 = escaneadorDoArquivo.nextLine().toLowerCase();
        String idPersonagem2 = escaneadorDoArquivo.nextLine().toLowerCase();
        this.personagem1 = personagens.get(idPersonagem1);
        this.personagem2 = personagens.get(idPersonagem2);

        escaneadorDoArquivo.nextLine(); // TEXTO
        String linha = escaneadorDoArquivo.nextLine();
        this.texto = "";
        while (!linha.equals("VARIACOES")) {
            if (linha.toLowerCase().equals(idPersonagem1)) {
                texto = texto + personagem1.getNome();
            } else if (linha.toLowerCase().equals(idPersonagem2)) {
                texto = texto + personagem2.getNome();
            } else {
                texto = texto + linha;
            }
            linha = escaneadorDoArquivo.nextLine();
        }

        // VARIAÇÕES já foi lido
        this.variacaoEnergiaPersonagem1 = Integer.parseInt(escaneadorDoArquivo.nextLine());
        this.variacaoEnergiaPersonagem2 = Integer.parseInt(escaneadorDoArquivo.nextLine());
    }

    public void adicionarEscolha(Escolha escolha) {
        escolhas.add(escolha);
    }

    public void executar() {
        // Mostrando o texto do capítulo e das escolhas
        mostrar();

        // Permitindo que o usuário realize sua escolha, caso existam escolhas possíveis
        if (escolhas.size() > 0) {
            int idCapituloEscolhido = escolher();
            System.out.println();
            System.out.println(". . .");
            System.out.println();
            escolhas.get(idCapituloEscolhido).getProximo().executar();
        } else {
            System.out.println("Sua aventura acabou, reinicie o livro para uma nova experiência!\n");
        }
    }

    protected void mostrar() {
        System.out.println(texto);
        personagem1.ajustarEnergia(variacaoEnergiaPersonagem1);
        personagem2.ajustarEnergia(variacaoEnergiaPersonagem2);

        for (int i = 0; i < escolhas.size(); i++) {
            System.out.println("- " + escolhas.get(i).getTextoMostrado());
        }

        System.out.print(">> ");
    }

    private int escolher() {
        int opcaoEscolhida = 0;
        String escolha;
        boolean escolhaValida = false;

        while (!escolhaValida) {
            //escolha = escaneador.nextLine();
            escolha = "";
            for (int i = 0; i < escolhas.size(); i++) {
                if (escolha.equalsIgnoreCase(escolhas.get(i).getTextoDigitado())) {
                    escolhaValida = true;
                    opcaoEscolhida = i;
                }
            }
            if (!escolhaValida) {
                System.out.println("A escolha digitada não válida, digite novamente");
            }
        }

        return opcaoEscolhida;
    }

    int findMin(int a, int b, int c) {
        int min = Math.min(a, b);
        return Math.min(min, c);
    }

    int calculateLevenshteinDistance(String a, String b) {
        int aLimit = a.length() + 1;
        int bLimit = b.length() + 1;
        int[][] distance = new int[aLimit][];
        for (int i = 0; i < aLimit; ++i) {
            distance[i] = new int[bLimit];
        }
        for (int i = 0; i < aLimit; ++i) {
            distance[i][0] = i;
        }
        for (int j = 0; j < bLimit; ++j) {
            distance[0][j] = j;
        }
        for (int i = 1; i < aLimit; ++i) {
            for (int j = 1; j < bLimit; ++j) {
                char aChar = a.charAt(i - 1);
                char bChar = b.charAt(j - 1);
                distance[i][j] = findMin(
                        distance[i - 1][j] + 1,
                        distance[i][j - 1] + 1,
                        distance[i - 1][j - 1] + (Objects.equals(aChar, bChar) ? 0 : 1) // + substitution cost
                );
            }
        }
        return distance[a.length()][b.length()];
    }

    public ArrayList<Escolha> getEscolhas() {
        return escolhas;
    };
}
