import java.util.ArrayList;
import java.util.Map;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.TextArea;

public class Controlador {

    private Capitulo raiz;

    @FXML
    private Button botaoIniciar;

    @FXML
    private ButtonBar botoesEscolha;

    @FXML
    private TextArea imagemAscii;

    @FXML
    private TextArea textoCapitulo;

    @FXML
    void iniciarHistoria(ActionEvent event) 
    {
        LeitorDeArquivos leitor = new LeitorDeArquivos();

        Map<String, Personagem> personagens = leitor.carregarPersonagens("rsc/Personagens.txt");
        Map<String, Capitulo> capitulos = leitor.carregarCapitulos("rsc/Capitulos.txt", personagens);
        System.out.println("Carregamento finalizado\n\n...\n\n");

        raiz = capitulos.get("raiz");
        mostrarCapitulo(raiz);
        
        botaoIniciar.setVisible(false);
        //raiz.executar();
    }

    private void mostrarCapitulo(Capitulo capitulo)
    {
        mostrarTextoCapitulo(capitulo.getTexto());
        mostrarEscolhas(capitulo.getEscolhas());
    }

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
        botoesEscolha.getButtons().clear();

        for (int i = 0; i < escolhas.size(); i++) 
        {            
            BotaoEscolha botao = new BotaoEscolha(escolhas.get(i));
            
            botao.setOnAction(new EventHandler<ActionEvent>() 
            {
                @Override
                public void handle(ActionEvent event) {
                    mostrarCapitulo(botao.getEscolha().getProximo());
                }
            });

            botoesEscolha.getButtons().add(botao);
        }
    }
}
