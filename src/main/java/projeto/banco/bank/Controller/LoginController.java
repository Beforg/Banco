package projeto.banco.bank.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import projeto.banco.bank.Alert.ShowMessage;
import projeto.banco.bank.Connection.LoginDAO;
import projeto.banco.bank.Modelo.TextFieldFormatter;
import projeto.banco.bank.View.ViewControl;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private TextField tf_login;
    @FXML
    private PasswordField tf_password;
    LoginDAO verificaLogin = new LoginDAO();
    ViewControl viewControl = new ViewControl();
    public void cadastro() {
        viewControl.showCadastro();
        ViewControl.isOpen = false;
    }
    public void logar() {
        System.out.println(tf_login.getText());
        boolean login = verificaLogin.verifcarLogin(tf_login.getText(), tf_password.getText());
        if (login) {
            ShowMessage.information("Atenção", "Login", "Login realizado com sucesso!");
            viewControl.showMain();
            Stage stage = (Stage) tf_login.getScene().getWindow();
            stage.close();
        } else {
            ShowMessage.information("Atenção", "Erro", "Login ou senha incorretos!");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TextFieldFormatter.cpfField(tf_login);
    }
}
