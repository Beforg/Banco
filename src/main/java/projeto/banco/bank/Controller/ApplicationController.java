package projeto.banco.bank.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import projeto.banco.bank.Alert.ShowMessage;
import projeto.banco.bank.Connection.UpdateDAO;
import projeto.banco.bank.Modelo.Conta;
import projeto.banco.bank.Modelo.InitializeConfig;
import projeto.banco.bank.Modelo.HistoricoTransferencia;
import projeto.banco.bank.Modelo.TextFieldFormatter;
import projeto.banco.bank.View.ViewControl;

import java.net.URL;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

public class ApplicationController implements Initializable {
    @FXML
    private TextField tfEdit_dataNasc;
    @FXML
    private TextField tfEdit_endereco;
    @FXML
    private TextField tfEdit_nome;
    @FXML
    private VBox edita_infosBox;
    @FXML
    private Label cliente_conta;

    @FXML
    private Label cpf_conta;

    @FXML
    private Label data_conta;

    @FXML
    private Label ender_conta;

    @FXML
    private Label id_conta;

    @FXML
    private Label nome_cliente;

    @FXML
    private Label num_conta;

    @FXML
    private AnchorPane pane_conta;

    @FXML
    private Label saldo_cliente;
    @FXML
    private Label numero_card_conta;
    @FXML
    private Label nome_card_conta;
    @FXML
    private Button bt_att;
    @FXML
    private TableView<HistoricoTransferencia> hist_transf;
    @FXML
    private TableColumn<HistoricoTransferencia, LocalDateTime> data;
    @FXML
    private TableColumn<HistoricoTransferencia, String> destino;
    @FXML
    private TableColumn<HistoricoTransferencia, String> origem;
    @FXML
    private TableColumn<HistoricoTransferencia, Double> valor;
    @FXML
    private TableColumn<HistoricoTransferencia, Integer> id;
    public static Conta contaInfos;
    UpdateDAO updateDAO = new UpdateDAO();
    ObservableList<HistoricoTransferencia> historico;
    Image image = new Image(Objects.requireNonNull(getClass().getResource("/projeto/banco/banco/img/att.png")).toExternalForm());


    public void transition_inicio() {
        pane_conta.setVisible(false);
        bt_att.setVisible(true);


    }
    public void transition_conta() {
        pane_conta.setVisible(true);
        bt_att.setVisible(false);
    }
    private final ViewControl viewControl = new ViewControl();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (contaInfos != null) {
            InitializeConfig.configuraTabelaHistorico(data, origem, destino, valor, id, hist_transf, updateDAO, contaInfos);

            InitializeConfig.configuraInformacoesConta(contaInfos, nome_cliente, cliente_conta, cpf_conta, data_conta,
                    ender_conta, id_conta, num_conta, saldo_cliente, numero_card_conta, nome_card_conta);
            InitializeConfig.imgBotao(image, bt_att);

            NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
            String valorFormatado = nf.format(contaInfos.getSaldo());
            bt_att.setGraphic(new ImageView(image));

            /*Formatacao*/
            TextFieldFormatter.dataField(tfEdit_dataNasc);
        }
    }
    public void depositar() {
        viewControl.showDepositar();
        ViewControl.isOpen = false;
    }
    public void transferir() {
        viewControl.showTranferir();
        ViewControl.isOpen = false;
    }
    public void sair() {
        viewControl.showLogout();
        ViewControl.isOpen = false;
        Stage stage = (Stage) nome_cliente.getScene().getWindow();
        stage.close();
    }

    public void apagar_conta() {
        InitializeConfig.apagarContaUser(contaInfos, viewControl, updateDAO, nome_cliente);
    }
    public void editar_info() {
        edita_infosBox.setVisible(true);

    }

    public void att_btn() {
        contaInfos = updateDAO.atualiza_infos(contaInfos.getCpf());
        uptadeUI();
    }
    private void uptadeUI() {
        atualizaHistoricoTransferencia();
        initialize(null, null);
    }

    private void atualizaHistoricoTransferencia () {
        hist_transf.setItems(FXCollections.observableArrayList(updateDAO.getTransferencias(contaInfos.getConta())));
        hist_transf.setItems(historico);
    }
    public void voltar_edit(){
        edita_infosBox.setVisible(false);
    }
    public void confirma_edit() {
        if (!tfEdit_nome.getText().isEmpty() && !tfEdit_dataNasc.getText().isEmpty() && !tfEdit_endereco.getText().isEmpty()) {
            updateDAO.atualiza_dados(tfEdit_nome.getText(), tfEdit_dataNasc.getText(), tfEdit_endereco.getText(), contaInfos.getCpf());
            edita_infosBox.setVisible(false);
            initialize(null, null);
        } else {
            ShowMessage.error("Atenção", "Erro", "Preencha todos os campos corretamente!");
        }
    }

}