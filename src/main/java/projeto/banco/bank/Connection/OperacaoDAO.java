package projeto.banco.bank.Connection;

import projeto.banco.bank.Alert.ShowMessage;
import projeto.banco.bank.Controller.ApplicationController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OperacaoDAO {
    private final ConnectionFactory connectionFactory;
    ApplicationController applicationController = new ApplicationController();
    public OperacaoDAO() {
        this.connectionFactory = new ConnectionFactory();
    }
    public boolean checkAccount(String account) {
        String checkAccountQuery = "SELECT * FROM cadastros WHERE CONTA = ?";

        try (Connection conn = connectionFactory.recuperarConexao();
             PreparedStatement checkAccountStmt = conn.prepareStatement(checkAccountQuery)) {
            checkAccountStmt.setString(1, account);
            ResultSet resultSet = checkAccountStmt.executeQuery();

            return resultSet.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public double checkBalance(String account) {
        String checkBalanceQuery = "SELECT CREDITO FROM cadastros WHERE CONTA = ?";

        try (Connection conn = connectionFactory.recuperarConexao();
             PreparedStatement checkBalanceStmt = conn.prepareStatement(checkBalanceQuery)) {
            checkBalanceStmt.setString(1, account);
            ResultSet resultSet = checkBalanceStmt.executeQuery();

            if (resultSet.next()) {
                return resultSet.getDouble("CREDITO");
            } else {
                throw new RuntimeException("Conta não encontrada.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void transferirValor(String remetente, String destinatario, double valor) {
        String decreaseBalanceQuery = "UPDATE cadastros SET credito = credito - ? WHERE CONTA = ?";
        String increaseBalanceQuery = "UPDATE cadastros SET credito = credito + ? WHERE CONTA = ?";

        double balance = checkBalance(remetente);
        if (balance < valor) {
            ShowMessage.error("Atenção", "Erro", "Saldo insuficiente para transferência");
            throw new RuntimeException("Insufficient balance for transfer");
        } else {
            try (Connection conn = connectionFactory.recuperarConexao()) {
                try (PreparedStatement decreaseStmt = conn.prepareStatement(decreaseBalanceQuery)) {
                    decreaseStmt.setDouble(1, valor);
                    decreaseStmt.setString(2, remetente);
                    decreaseStmt.executeUpdate();

                }

                try (PreparedStatement increaseStmt = conn.prepareStatement(increaseBalanceQuery)) {
                    increaseStmt.setDouble(1, valor);
                    increaseStmt.setString(2, destinatario);
                    increaseStmt.executeUpdate();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public double depositarValor(String conta, double valor) {
        String depositQuery = "UPDATE cadastros SET CREDITO = CREDITO + ? WHERE CONTA = ?";
        if (!checkAccount(conta)) {
            ShowMessage.error("Atenção", "Erro", "Conta não encontrada");
            throw new RuntimeException("Account not found");
        } else {
            try (Connection conn = connectionFactory.recuperarConexao();
                 PreparedStatement depositStmt = conn.prepareStatement(depositQuery)) {
                depositStmt.setDouble(1, valor);
                depositStmt.setString(2, conta);
                depositStmt.executeUpdate();
                System.out.println("Depósito realizado com sucesso!");

                return checkBalance(conta);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}