package fintrack.controller;

import java.util.List;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.text.NumberFormat;
import java.util.Locale;


import fintrack.model.Transacao;


/**
 * Classe responsavel pelos eventos da tela principal
 * PrincipalController
 */
public class PrincipalController {
    @FXML private Label labelSaldo;
    @FXML private TableView<Transacao> tabelaTransacao;
    @FXML private TableColumn<Transacao, Integer> campoId; 
    @FXML private TableColumn<Transacao, String> campoDescricao;
    @FXML private TableColumn<Transacao, Double> campoValor;
    @FXML private TableColumn<Transacao, String> campoTipo;
    @FXML private TableColumn<Transacao, String> campoData;

    /**
     * Método responsável por fazer a busca no banco
     * para exibir nas tabelas
     */
    @FXML
    public void initialize() {
        campoId.setCellValueFactory(new PropertyValueFactory<>("id"));
        campoDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        campoValor.setCellValueFactory(new PropertyValueFactory<>("valor"));
        campoTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        campoData.setCellValueFactory(new PropertyValueFactory<>("data"));
        
        campoId.setStyle("-fx-alignment: CENTER;");
        campoDescricao.setStyle("-fx-alignment: CENTER;");
        campoData.setStyle("-fx-alignment: CENTER;");
        campoValor.setStyle("-fx-alignment: CENTER;");
        campoTipo.setStyle("-fx-alignment: CENTER;");

        try {
            FinTracker tracker = new FinTracker();
            List<Transacao> lista = tracker.listarTransacao();
            tabelaTransacao.getItems().setAll(lista);
            double saldo = tracker.calcularSaldoTotal();
            NumberFormat nf = NumberFormat.getInstance(Locale.of("pt", "BR"));
            labelSaldo.setText(nf.format(saldo));
            if(saldo > 0) {
                labelSaldo.getStyleClass().removeAll("label-saldo-negativo");
                labelSaldo.getStyleClass().add("label-saldo-positivo");
            } else if(saldo < 0) {
                labelSaldo.getStyleClass().removeAll("label-saldo-positivo");
                labelSaldo.getStyleClass().add("label-saldo-negativo");
            } else {
                labelSaldo.getStyleClass().removeAll("label-saldo-positivo", "label-saldo-negativo");
                labelSaldo.getStyleClass().add("label-saldo-neutro");
            }
        } catch (Exception e){
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Erro");
            alerta.setHeaderText("Erro ao carregar transações");
            alerta.setContentText(e.getMessage());
            alerta.showAndWait();
        }
    }
    /**
     * Método responsavel por conectar a GUI com a ação
     * de adicionar as transações no banco
     */
    @FXML
    public void adicionarTransacao() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fintrack/view/NovaTransacao.fxml"));
            Scene scene = new Scene(loader.load(), 800, 500);
            Stage stage = (Stage) tabelaTransacao.getScene().getWindow();
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
    /**
     * Método que liga a GUI com a ação de
     * remover uma transação do banco
     */
    @FXML
    public void removerTransacao() {
        Transacao marcada = tabelaTransacao.getSelectionModel().getSelectedItem();

        if(marcada == null) {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Aviso");
            alerta.setHeaderText("Nenhuma transação selecionada");
            alerta.setContentText("Selecione uma transação para remover");
            alerta.showAndWait();
            return;
        }
        try {
            FinTracker tracker = new FinTracker();
            tracker.removerTransacao(marcada.getId());
            tabelaTransacao.getItems().remove(marcada);
            double saldo = tracker.calcularSaldoTotal();
            NumberFormat nf = NumberFormat.getInstance(Locale.of("pt", "BR"));
            labelSaldo.setText(nf.format(saldo));
        } catch(Exception e) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Erro");
            alerta.setHeaderText("Erro ao remover");
            alerta.setContentText(e.getMessage());
            alerta.showAndWait();
        }
    }
}
