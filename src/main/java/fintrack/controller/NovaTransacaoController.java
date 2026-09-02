package fintrack.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import java.text.ParseException;
import java.text.NumberFormat;
import java.util.Locale;


import fintrack.model.Transacao;
import fintrack.model.TransacaoMensal;


/**
 * Classe responsável pela tela dedicada
 * à instância de uma nova transação
 * NovaTransacaoController
 */
public class NovaTransacaoController {
    @FXML private TextField campoDescricao;
    @FXML private TextField campoValor;
    @FXML private ToggleGroup tipoTransacao;
    @FXML private DatePicker campoData;
    @FXML private Button btnCancelar;


    /**
     * Dita as normas na inicialização da tela 
     * de nova transação
     */
    @FXML void initialize() {
        campoData.getEditor().setDisable(true);
    }
    /**
     * Método que valida a adição da transação
     * ao banco
     */
    @FXML
    public void salvar() {
        try {
            FinTracker tracker = new FinTracker();

            NumberFormat nf = NumberFormat.getInstance(Locale.of("pt", "BR"));
            double valor = nf.parse(campoValor.getText()).doubleValue();
            String tipo = ((RadioButton) tipoTransacao.getSelectedToggle()).getText();

            Transacao t;
            if(tipo.equalsIgnoreCase("Receita mensal") || tipo.equalsIgnoreCase("Despesa Mensal")) {
                t = new TransacaoMensal(0, campoDescricao.getText(), valor, tipo, campoData.getValue());
            } else {
                t = new Transacao(0, campoDescricao.getText(), valor, tipo, campoData.getValue());
            }

            tracker.adicionarTransacao(t);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fintrack/view/Principal.fxml"));
            Scene scene = new Scene(loader.load(), 800, 500);
            Stage stage = (Stage) btnCancelar.getScene().getWindow();
            scene.getStylesheets().add(getClass().getResource("/fintrack/view/style.css").toExternalForm());
            stage.setScene(scene);
        } catch(NumberFormatException | ParseException e) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Erro");
            alerta.setHeaderText("Valor inválido");
            alerta.setContentText("Digite um número válido no campo valor.");
            alerta.showAndWait();
        } catch(Exception e) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Erro");
            alerta.setHeaderText("Erro ao salvar");
            alerta.setContentText("É necessário preencher todos os campos");
            alerta.showAndWait();
        }
    }
    /**
     * Método responsável por sair da tela de cadastrar
     * nova transação
     */
    @FXML
    public void cancelar() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fintrack/view/Principal.fxml"));
            Scene scene = new Scene(loader.load(), 800, 500);
            Stage stage = (Stage) btnCancelar.getScene().getWindow();
            scene.getStylesheets().add(getClass().getResource("/fintrack/view/style.css").toExternalForm());
            stage.setScene(scene);
        } catch(Exception e) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Erro");
            alerta.setHeaderText("Erro ao transicionar telas");
            alerta.setContentText(e.getMessage());
            alerta.showAndWait();
        }
    }
}
