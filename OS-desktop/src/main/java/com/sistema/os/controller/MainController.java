package com.sistema.os.controller;

import com.sistema.os.model.OrdemServico;
import com.sistema.os.client.OrdemServicoClient;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML private TableView<OrdemServico> tabelaOS;
    @FXML private TableColumn<OrdemServico, String> colId;
    @FXML private TableColumn<OrdemServico, String> colCliente;
    @FXML private TableColumn<OrdemServico, String> colDescricao;
    @FXML private TableColumn<OrdemServico, String> colStatus;
    @FXML private TableColumn<OrdemServico, Double> colValor;
    @FXML private TextField campoPesquisa;

    private final OrdemServicoClient client = new OrdemServicoClient();
    private final javafx.collections.ObservableList<OrdemServico> listaMestra = javafx.collections.FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurarColunas();
        carregarDadosDoServidor();
    }

    private void configurarColunas(){
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colCliente.setCellValueFactory(new PropertyValueFactory<>("nomeCliente"));
        colDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colValor.setCellValueFactory(new PropertyValueFactory<>("valorTotal"));
    }

    private void carregarDadosDoServidor(){
        try{
            List<OrdemServico> ordens = client.buscarTodas();
            listaMestra.setAll(ordens);
            configurarFiltro();
        } catch (Exception e){
            System.err.println("Erro ao carregar dados: " + e.getMessage());
        }
    }

    private void configurarFiltro(){
        javafx.collections.transformation.FilteredList<OrdemServico> dadosFiltrados = new javafx.collections.transformation.FilteredList<>(listaMestra, b -> true);
        campoPesquisa.textProperty().addListener((observable, oldValue, newValue) ->{
            dadosFiltrados.setPredicate(os -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String palavraDigitada = newValue.toLowerCase();

                if (os.getNomeCliente() != null && os.getNomeCliente().toLowerCase().contains(palavraDigitada)){
                    return true;
                } else if (os.getDescricao() != null && os.getDescricao().toLowerCase().contains(palavraDigitada)) {
                    return true;
                }

                return false;
            });
        });

        javafx.collections.transformation.SortedList<OrdemServico> dadosOrdenados = new javafx.collections.transformation.SortedList<>(dadosFiltrados);
        dadosOrdenados.comparatorProperty().bind(tabelaOS.comparatorProperty());
        tabelaOS.setItems(dadosOrdenados);
    }
}
