package projeto.banco.bank.Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    public Connection recuperarConexao(){
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/clientes?user=root&password=19822Cap.");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
