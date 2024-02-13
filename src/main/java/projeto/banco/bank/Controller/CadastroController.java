package projeto.banco.bank.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import projeto.banco.bank.Alert.ShowMessage;
import projeto.banco.bank.Connection.ConnectionFactory;
import projeto.banco.bank.Modelo.Formatter;
import projeto.banco.bank.Modelo.TextFieldFormatter;
import projeto.banco.bank.View.ViewControl;

import java.math.BigDecimal;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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

    ConnectionFactory connection = new ConnectionFactory();
    ViewControl viewControl = new ViewControl();
    public CadastroController() {
        this.connection = new ConnectionFactory();
    }

    public void cadastrar(){
        String sql = "INSERT INTO cadastros (NOME, DATAS, CPF, ENDERECO, SENHA, CREDITO, CONTA) VALUES (?,?,?,?,?,?,?)";
        Connection conn = connection.recuperarConexao();

        try {

        PreparedStatement preparedStatement = conn.prepareStatement(sql);

        preparedStatement.setString(1, tf_nome.getText());
        preparedStatement.setString(2, tf_datas.getText());
        preparedStatement.setString(3, tf_cpf.getText());
        preparedStatement.setString(4, tf_endereco.getText());
        preparedStatement.setString(5, tf_senha.getText());
        preparedStatement.setBigDecimal(6, BigDecimal.ZERO);
        preparedStatement.setString(7, Formatter.setNumCard());
        ShowMessage.confirma("Atenção", "Cadastro", "Cadastro realizado com sucesso!");
        preparedStatement.execute();
        preparedStatement.close();
            System.out.println(tf_cpf.getText());
        Stage stage = (Stage) tf_nome.getScene().getWindow();
        stage.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TextFieldFormatter.cpfField(tf_cpf);
        TextFieldFormatter.dataField(tf_datas);
    }
}
