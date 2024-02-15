package projeto.banco.bank.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import projeto.banco.bank.Connection.CadastroDAO;
import projeto.banco.bank.Modelo.TextFieldFormatter;
import java.net.URL;
import java.util.ResourceBundle;

public class CadastroController implements Initializable {
    @FXML
    private TextField tf_cpf;

    @FXML
    private TextField tf_datas;

    @FXML
    private TextField tf_endereco;

    @FXML
    private TextField tf_nome;

    @FXML
    private PasswordField tf_senha;
    CadastroDAO cadastroDAO = new CadastroDAO();
    public void cadastrar(){
          cadastroDAO.cadastrarNovoUser(tf_nome, tf_datas, tf_cpf, tf_endereco, tf_senha);
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TextFieldFormatter.cpfField(tf_cpf);
        TextFieldFormatter.dataField(tf_datas);
    }
}
