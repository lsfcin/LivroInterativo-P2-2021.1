import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.TextArea;

public class ControladorNomePersonagem {

    @FXML
    private ButtonBar botoesEscolha;

    @FXML
    private TextArea imagemAscii;

    @FXML
    private TextArea textoCapitulo;

    public void mostrarTextoCapitulo(String texto) 
    {
        textoCapitulo.setText(texto);
    }

    public void mostrarImagemAscii(String imagem) 
    {
        imagemAscii.setText(imagem);
    }

    public void mostrarEscolhas(ArrayList<Escolha> escolhas)
    {
        botoesEscolha.setPadding(new Insets(10));

        for (int i = 0; i < escolhas.size(); i++) {
            botoesEscolha.getButtons().add(new Button(escolhas.get(i).getTextoMostrado()));
            //System.out.println("- " + escolhas.get(i).getTextoMostrado());
        }
    }
}
