package projeto.banco.bank.Modelo;

import projeto.banco.bank.ConnectionFactory;
import projeto.banco.bank.View.ViewControl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class VerificaLogin {
    ViewControl viewControl = new ViewControl();
    ConnectionFactory connection = new ConnectionFactory();
    public VerificaLogin() {
        this.connection = new ConnectionFactory();
    }

    public boolean verifcarLogin(String login, String password) {
        boolean loginValido = false;

        Connection conn = connection.recuperarConexao();
        String consultaSQL = ("SELECT * FROM cadastros WHERE CPF = " + login + " AND SENHA = " + password);

        try {
            PreparedStatement ps = conn.prepareStatement(consultaSQL);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {

                loginValido = true;
                System.out.println("Login realizado com sucesso!");
            } else {
                System.out.println("Login ou senha incorretos!");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return loginValido;
    }

}
