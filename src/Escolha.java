public class Escolha {
  private String textoMostrado;
  private String textoDigitado;
  private Capitulo proximo;

  public Escolha(String textoDigitado, String textoMostrado, Capitulo proximo) {
    this.textoDigitado = textoDigitado;
    this.textoMostrado = textoMostrado;
    this.proximo = proximo;
  }

  public String getTextoDigitado() {
    return this.textoDigitado;
  }

  public String getTextoMostrado() {
    return this.textoMostrado;
  }

  public Capitulo getProximo() {
    return this.proximo;
  }
}
