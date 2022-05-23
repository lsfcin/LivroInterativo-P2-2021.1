import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

/**
 * Classe que armazena todas as informações relativas à um capítulo da história.
 * As informações armezandas são:
 * <ul>
 * <li> private String texto;
 * <li> protected ArrayList<Escolha> escolhas;
 * <li> private Personagem personagem1;
 * <li> private Personagem personagem2;
 * <li> private int variacaoEnergiaPersonagem1;
 * <li> private int variacaoEnergiaPersonagem2;
 * </ul>
 */
public class Capitulo {
  private String texto;
  protected ArrayList<Escolha> escolhas;
  private Personagem personagem1;
  private Personagem personagem2;
  private int variacaoEnergiaPersonagem1;
  private int variacaoEnergiaPersonagem2;

  protected Capitulo() {
    this.texto = "";
    this.escolhas = new ArrayList<Escolha>();
  }

  public Capitulo(String texto,
      Personagem personagem1,
      Personagem personagem2,
      int variacaoEnergiaPersonagem1,
      int variacaoEnergiaPersonagem2) {
    this.texto = texto;
    this.personagem1 = personagem1;
    this.personagem2 = personagem2;
    this.variacaoEnergiaPersonagem1 = variacaoEnergiaPersonagem1;
    this.variacaoEnergiaPersonagem2 = variacaoEnergiaPersonagem2;
    this.escolhas = new ArrayList<Escolha>();
  }

  public Capitulo(
      Map<String, Personagem> personagens,
      Scanner escaneadorDoArquivo) {
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

  int findMin(int a, int b, int c) {
    int min = Math.min(a, b);
    return Math.min(min, c);
  }

  public ArrayList<Escolha> getEscolhas() {
    return escolhas;
  }

  public void atualizarEnergiaPersonagens() {
    personagem1.ajustarEnergia(variacaoEnergiaPersonagem1);
    personagem2.ajustarEnergia(variacaoEnergiaPersonagem2);
  };
}
