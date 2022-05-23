import javafx.scene.control.Button;

/**
 * A classe BotaoEscolha estende a classe Button,
 * com o objetivo de armazenar uma escolha e assim
 * facilitar o acesso ao capítulo que esta representa.
 * Desta forma é possível, ao apertar o botão, 
 * acionar o capítulo associado àquela escolha.
 */
public class BotaoEscolha extends Button {
  private Escolha escolha;

  /**
   * Único construtor disponível para a classe, 
   * o qual necessita de uma escolha como parâmetro.
   * @param escolha
   */
  public BotaoEscolha(Escolha escolha) {
    super(escolha.getTextoMostrado());
    this.escolha = escolha;
  }

  /**
   * Método getEscolha() para dar acesso à escolha usada no construtor.
   * @return o objeto do tipo Escolha associado ao botão
   */
  public Escolha getEscolha() {
    return this.escolha;
  }
}
