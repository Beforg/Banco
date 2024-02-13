package projeto.banco.bank.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import projeto.banco.bank.Alert.ShowMessage;
import projeto.banco.bank.Connection.UpdateDAO;
import projeto.banco.bank.Modelo.Conta;
import projeto.banco.bank.View.ViewControl;

import java.math.BigDecimal;
import java.net.URL;
import java.security.URIParameter;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

public class ApplicationController implements Initializable {
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
    public static Conta contaInfos;

    UpdateDAO updateDAO = new UpdateDAO();
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


        }
    }
    public void depositar() {
        viewControl.showDepositar();
        viewControl.isOpen = false;
    }
    public void transferir() {
        viewControl.showTranferir();
        viewControl.isOpen = false;
    }
    public void sair() {
        viewControl.showLogout();
        viewControl.isOpen = false;
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
                viewControl.isOpen = false;
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
        initialize(null, null);
    }

}