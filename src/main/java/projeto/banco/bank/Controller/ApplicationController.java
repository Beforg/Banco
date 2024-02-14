package projeto.banco.bank.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import projeto.banco.bank.Alert.ShowMessage;
import projeto.banco.bank.Connection.UpdateDAO;
import projeto.banco.bank.Modelo.Conta;
import projeto.banco.bank.Modelo.HistoricoTransferencia;
import projeto.banco.bank.Modelo.TextFieldFormatter;
import projeto.banco.bank.View.ViewControl;

import java.math.BigDecimal;
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

            NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
            String valorFormatado = nf.format(contaInfos.getSaldo());

            nome_cliente.setText(contaInfos.getNome());
            cliente_conta.setText(contaInfos.getNome());
            cpf_conta.setText(contaInfos.getCpf());
            data_conta.setText(contaInfos.getDatas());
            ender_conta.setText(contaInfos.getEndereco());
            id_conta.setText(contaInfos.getId());
            num_conta.setText(contaInfos.getConta());
            saldo_cliente.setText(valorFormatado);
            numero_card_conta.setText(contaInfos.getConta());
            nome_card_conta.setText(contaInfos.getNome());


            bt_att.setGraphic(new ImageView(image));

            /*Tabela Histórico de Transferência*/

            data.setCellValueFactory(new PropertyValueFactory<HistoricoTransferencia, LocalDateTime>("data"));
            origem.setCellValueFactory(new PropertyValueFactory<HistoricoTransferencia, String>("origem"));
            destino.setCellValueFactory(new PropertyValueFactory<HistoricoTransferencia, String>("destino"));
            valor.setCellValueFactory(new PropertyValueFactory<HistoricoTransferencia, Double>("valor"));
            valor.setCellFactory(column -> {
                return new TableCell<HistoricoTransferencia, Double>() {
                    @Override
                    protected void updateItem(Double item, boolean empty) {
                        super.updateItem(item, empty);

                        if (item == null || empty) {
                            setText(null);
                        } else {
                            NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
                            setText(nf.format(item));
                        }
                    }
                };
            });
            id.setCellValueFactory(new PropertyValueFactory<HistoricoTransferencia, Integer>("id"));

            historico = FXCollections.observableArrayList(updateDAO.getTransferencias(contaInfos.getConta()));
            hist_transf.setItems(historico);

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
        if (!contaInfos.getSaldo().equals(BigDecimal.ZERO)) {
            ShowMessage.error("Atenção", "Erro", "Não é possível apagar a conta com saldo positivo!");
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmação");
            alert.setHeaderText("Apagar conta");
            alert.setContentText("Deseja realmente apagar a conta?");

            if (alert.showAndWait().get() == ButtonType.OK) {
                ShowMessage.information("Atenção", "Conta", "Conta apagada com sucesso!");
                System.out.println("Apagar conta");
                updateDAO.apagar_conta(contaInfos.getCpf());
                viewControl.showLogout();
                ViewControl.isOpen = false;
                Stage stage = (Stage) nome_cliente.getScene().getWindow();
                stage.close();

            } else {
                System.out.println("Cancelar");
            }

        }

    }
    public void editar_info() {
        edita_infosBox.setVisible(true);

    }

    public void att_btn() {
        contaInfos = updateDAO.atualiza_infos(contaInfos.getCpf());
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