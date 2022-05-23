import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

  public static void main(String[] args) {
    launch(args);
  }


  /**
  * O método start sobrepõe o método da classe herdada,
  * dentro deste método estamos carregando um arquivo FXML
  */

  @Override
  public void start(Stage primaryStage) throws Exception {
    URL url = getClass().getResource("arquivoTeste.fxml");
    FXMLLoader loader = new FXMLLoader(url);
    Parent root = loader.load();
    Scene tela = new Scene(root);

    primaryStage.setTitle("Livro Interativo!");
    primaryStage.setScene(tela);
    primaryStage.show();
  }
}