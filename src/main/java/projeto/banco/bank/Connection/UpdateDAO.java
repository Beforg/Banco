package projeto.banco.bank.Connection;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import projeto.banco.bank.Alert.ShowMessage;
import projeto.banco.bank.Modelo.Conta;
import projeto.banco.bank.Modelo.HistoricoTransferencia;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UpdateDAO {
    private final ConnectionFactory connection;

    public UpdateDAO() {
        this.connection = new ConnectionFactory();
    }
    public void apagar_conta(String cpf) {
        Connection conn = connection.recuperarConexao();
        String consultaSQL = "DELETE FROM cadastros WHERE CPF = '" + cpf + "'";
        try {
            PreparedStatement ps = conn.prepareStatement(consultaSQL);
            ps.execute();
            ShowMessage.information("Sucesso", "Conta apagada", "Conta apagada com sucesso");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Conta atualiza_infos(String cpf) {
        Conta conta = null;
        String consultaSql = "SELECT * FROM cadastros WHERE CPF = '" + cpf + "'";
        Connection conn = connection.recuperarConexao();
        try {
            PreparedStatement ps = conn.prepareStatement(consultaSql);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                conta = new Conta(resultSet.getString("NOME"),
                        resultSet.getString("DATAS"),
                        resultSet.getString("CPF"),
                        resultSet.getString("ENDERECO"),
                        resultSet.getString("ID"),
                        resultSet.getString("CONTA"),
                        resultSet.getBigDecimal("CREDITO"));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return conta;
    }
    public void atualiza_dados(String nome, String data, String endereco, String cpf) {
        Connection conn = connection.recuperarConexao();
        String consultaSQL = "UPDATE cadastros SET NOME = ?, DATAS = ?, ENDERECO = ? WHERE CPF = '" + cpf + "'";
            try {
                PreparedStatement ps = conn.prepareStatement(consultaSQL);
                ps.setString(1, nome);
                ps.setString(2, data);
                ps.setString(3, endereco);
                ps.execute();
                ShowMessage.information("Sucesso", "Dados atualizados", "Dados atualizados com sucesso, reinicie a aplicação para ver as mudanças!");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

    }
    public ObservableList<HistoricoTransferencia> getTransferencias(String conta) {
        ObservableList<HistoricoTransferencia> transferencias = FXCollections.observableArrayList();
        String consultaSQL = "SELECT * FROM historico_transferencia WHERE ORIGEM = ? OR DESTINO = ?";
        try (Connection conn = connection.recuperarConexao();
             PreparedStatement ps = conn.prepareStatement(consultaSQL)) {
            ps.setString(1, conta);
            ps.setString(2, conta);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                HistoricoTransferencia transferencia = new HistoricoTransferencia(
                        resultSet.getInt("id"),
                        resultSet.getTimestamp("data_transf").toLocalDateTime(),
                        resultSet.getString("origem"),
                        resultSet.getString("destino"),
                        resultSet.getDouble("valor_transf")
                );
                System.out.println(transferencia);
                transferencias.add(transferencia);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return transferencias;
    }
}
