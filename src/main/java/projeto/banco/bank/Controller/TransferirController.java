package projeto.banco.bank.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import javafx.stage.Stage;
import projeto.banco.bank.Alert.ShowMessage;
import projeto.banco.bank.Connection.OperacaoDAO;
import projeto.banco.bank.Modelo.TextFieldFormatter;
import projeto.banco.bank.View.ViewControl;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class TransferirController implements Initializable {
    @FXML
    private TextField tf_destinatario;

    @FXML
    private TextField tf_remetente;

    @FXML
    private TextField tf_valor;
    OperacaoDAO operacaoDAO = new OperacaoDAO();
    private final String numConta = ApplicationController.contaInfos.getConta();
    private final LocalDateTime data = LocalDateTime.now();
    public void confirmar() {
        double checkValor = operacaoDAO.checkBalance(numConta);
        double valor = Double.parseDouble(tf_valor.getText()
                .replace("R$", "")
                .replace(",", ".")
                .replace(" ", ""));

        if (!tf_remetente.getText().isEmpty()
                && !tf_destinatario.getText().isEmpty()
                && !tf_valor.getText().isEmpty()
                && !tf_destinatario.equals(tf_remetente)
                && valor > 0
                && numConta.equals(tf_remetente.getText()) && checkValor >= valor && !tf_destinatario.getText().equals(numConta)) {
            operacaoDAO.registraTransferencia(tf_remetente.getText(), tf_destinatario.getText(), valor, data);

            System.out.println("Transferência realizada com sucesso!");
            operacaoDAO.transferirValor(tf_remetente.getText(), tf_destinatario.getText(), Double.parseDouble(tf_valor.getText()
                    .replace("R$", "")
                    .replace(",", ".")
                    .replace(" ", "")));
            ShowMessage.information("Atenção", "Transferência", "Transferência realizada com sucesso!");

            tf_destinatario.setText("");
            tf_remetente.setText("");
            tf_valor.setText("");

        } else if (checkValor < valor) {
            ShowMessage.error("Atenção", "Erro", "Saldo insuficiente para transferência");

        }else {
            ShowMessage.error("Atenção", "Erro", "Dados incorretos ou preenchimento incompleto!");

        }
    }
    public void cancelar() {
        ViewControl.isOpen = true;
        Stage stage = (Stage) tf_destinatario.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TextFieldFormatter.numContaField(tf_destinatario);
        TextFieldFormatter.numContaField(tf_remetente);
        TextFieldFormatter.formatarMonetario(tf_valor);

    }
}
