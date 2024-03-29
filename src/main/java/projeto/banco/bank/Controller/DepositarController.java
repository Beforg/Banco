package projeto.banco.bank.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import projeto.banco.bank.Alert.ShowMessage;
import projeto.banco.bank.Connection.OperacaoDAO;
import javafx.scene.control.TextField;
import projeto.banco.bank.Connection.UpdateDAO;
import projeto.banco.bank.Modelo.TextFieldFormatter;
import projeto.banco.bank.View.ViewControl;

import java.net.URL;
import java.util.ResourceBundle;

public class DepositarController implements Initializable {
    @FXML
    private TextField tf_valor;
    @FXML
    private TextField tf_conta;
    OperacaoDAO operacaoDAO = new OperacaoDAO();
    UpdateDAO updateDao = new UpdateDAO();
    public void confirmar() {
        try {
            double valor = Double.parseDouble(tf_valor.getText()
                    .replace("R$", "")
                    .replace(",", ".")
                    .replace(" ", ""));

            if (!tf_conta.getText().isEmpty() && !tf_valor.getText().isEmpty() && valor > 0) {
                ViewControl.isOpen = true;
                ApplicationController.contaInfos = updateDao.atualiza_infos(ApplicationController.contaInfos.getCpf());
                operacaoDAO.depositarValor(tf_conta.getText(), valor);
                ShowMessage.information("Atenção", "Depósito", "Depósito realizado com sucesso!");
                Stage stage = (Stage) tf_conta.getScene().getWindow();
                stage.close();
            } else {
                ShowMessage.error("Atenção", "Erro", "Preencha todos os campos corretamente!");
            }
        } catch (NumberFormatException e) {
            ShowMessage.error("Atenção", "Erro", "Valor inválido!");
        }

    }
    public void cancelar() {
        ViewControl.isOpen = true;
        Stage stage = (Stage) tf_conta.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TextFieldFormatter.numContaField(tf_conta);
        TextFieldFormatter.formatarMonetario(tf_valor);
    }
}
