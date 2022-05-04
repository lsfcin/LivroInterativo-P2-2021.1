public class Personagem {
    private String nome;
    private int energia;

    public Personagem(String nome, int energia) {
        this.nome = nome;
        this.energia = energia;
    }

    public Personagem(String nome) {
        this.nome = nome;
        this.energia = 100;
    }

    public void ajustarEnergia(int variacao) {
        if (variacao != 0) {
            setEnergia(this.energia + variacao);
            System.out.println(
                    "[Devido aos acontecimentos recentes, a energia de " + this.nome + " foi a " + this.energia + "]");
        }
    }

    private void setEnergia(int energia) {
        this.energia = energia;
        if (this.energia < 0) {
            this.energia = 0;
        }
    }

    public int getEnergia() {
        return this.energia;
    }

    public String getNome() {
        return this.nome;
    }
}
