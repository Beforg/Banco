package projeto.banco.bank.Connection;
import projeto.banco.bank.Modelo.Conta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class InfoDAO {
    ConnectionFactory connection;
    public InfoDAO() {
        this.connection = new ConnectionFactory();
    }
    public Conta setInfos(String cpf) {
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
}
