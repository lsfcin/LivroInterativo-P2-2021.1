import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ControladorNomePersonagem {

    @FXML
    private Button botaoIniciar;

    @FXML
    private Label labelNomePersonagem;

    @FXML
    private TextField textFieldNomePersonagem;

    @FXML
    void salvarNomePersonagem(ActionEvent event) {
        String nome = textFieldNomePersonagem.getText();
        labelNomePersonagem.setText(nome);
        textFieldNomePersonagem.setVisible(false);
    }
}
