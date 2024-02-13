package projeto.banco.bank.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import projeto.banco.bank.Alert.ShowMessage;
import projeto.banco.bank.Connection.OperacaoDAO;
import projeto.banco.bank.Modelo.TextFieldFormatter;

import java.net.URL;
import java.util.ResourceBundle;

public class TransferirController implements Initializable {
    @FXML
    private TextField tf_destinatario;

    @FXML
    private TextField tf_remetente;

    @FXML
    private TextField tf_valor;
    OperacaoDAO operacaoDAO = new OperacaoDAO();

    public void confirmar() {
        if (!tf_remetente.getText().isEmpty() && !tf_destinatario.getText().isEmpty() && !tf_valor.getText().isEmpty() && !tf_destinatario.equals(tf_remetente)) {
            System.out.println("Transferência realizada com sucesso!");
            operacaoDAO.transferirValor(tf_remetente.getText(), tf_destinatario.getText(), Double.parseDouble(tf_valor.getText()
                    .replace("R$", "")
                    .replace(",", ".")
                    .replace(" ", "")));
            ShowMessage.information("Atenção", "Transferência", "Transferência realizada com sucesso!");
            Stage stage = (Stage) tf_remetente.getScene().getWindow();
            stage.close();

        } else {
            ShowMessage.error("Atenção", "Erro", "Dados incorretos ou preenchimento incompleto!");
        }
    }
    public void cancelar() {
        System.out.println("Voltar");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TextFieldFormatter.numContaField(tf_destinatario);
        TextFieldFormatter.numContaField(tf_remetente);
        TextFieldFormatter.formatarMonetario(tf_valor);

    }
}
