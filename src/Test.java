import java.util.Map;
import java.util.Scanner;

public class Test 
{    
    public static void main(String[] args) 
    {
        Scanner escaneadorDoConsole = new Scanner(System.in, "CP850");
        LeitorDeArquivos leitor = new LeitorDeArquivos();

        Map<String, Personagem> personagens = leitor.carregarPersonagens("rsc/Personagens.txt");
        Map<String, Capitulo> capitulos = leitor.carregarCapitulos("rsc/Capitulos.txt", personagens, escaneadorDoConsole);

        System.out.println("Carregamento finalizado\n\n...\n\n");

        Capitulo raiz = capitulos.get("raiz");
        raiz.executar();

        escaneadorDoConsole.close();
    }
}