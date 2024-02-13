package projeto.banco.bank.Connection;

import projeto.banco.bank.Controller.ApplicationController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginDAO {
    ConnectionFactory connection;
    InfoDAO setInfos = new InfoDAO();
    public LoginDAO() {
        this.connection = new ConnectionFactory();
    }

    public boolean verifcarLogin(String login, String password) {
        boolean loginValido = false;

        Connection conn = connection.recuperarConexao();
        String consultaSQL = "SELECT * FROM cadastros WHERE CPF = '" + login + "' AND SENHA = '" + password + "'";
        try {
            PreparedStatement ps = conn.prepareStatement(consultaSQL);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {

                loginValido = true;
                ApplicationController.contaInfos = setInfos.setInfos(login);
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
