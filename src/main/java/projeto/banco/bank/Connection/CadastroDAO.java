package projeto.banco.bank.Connection;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import projeto.banco.bank.Alert.ShowMessage;
import projeto.banco.bank.Modelo.Formatter;
import projeto.banco.bank.View.ViewControl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CadastroDAO {
    ConnectionFactory connection;
    public CadastroDAO() {
        this.connection = new ConnectionFactory();
    }
    public void cadastrarNovoUser(TextField tf_nome, TextField tf_datas, TextField tf_cpf, TextField tf_endereco, PasswordField tf_senha) {
        String sql = "INSERT INTO cadastros (NOME, DATAS, CPF, ENDERECO, SENHA, CREDITO, CONTA) VALUES (?,?,?,?,?,?,?)";
        Connection conn = connection.recuperarConexao();

        try {
            ViewControl.isOpen = true;
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
}
