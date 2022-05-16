import java.util.Map;
import java.util.Scanner;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Test extends Application
{

    public static void main(String[] args) 
    {
        Scanner escaneadorDoConsole = new Scanner(System.in, "CP850");
        LeitorDeArquivos leitor = new LeitorDeArquivos();

        Map<String, Personagem> personagens = leitor.carregarPersonagens("rsc/Personagens.txt");
        Map<String, Capitulo> capitulos = leitor.carregarCapitulos("rsc/Capitulos.txt", personagens, escaneadorDoConsole);

        System.out.println("Carregamento finalizado\n\n...\n\n");

        Capitulo raiz = capitulos.get("raiz");
        //raiz.executar();

        escaneadorDoConsole.close();

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("arquivoTeste.fxml"));
        Parent root = loader.load();
        Scene tela = new Scene(root);
        
        primaryStage.setTitle("Livro Interativo!");
        primaryStage.setScene(tela);
        primaryStage.show();
    }
}