package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MyConnection {

    private static Statement statement;
    public MyConnection() throws SQLException {
        String url = "jdbc:postgresql://127.0.0.1:5432/Anime?searchpath=Schemas(1)";
        String name = "postgres";
        String password = "Geronimo";
        Connection connection = DriverManager.getConnection(url, name, password);
        statement = connection.createStatement();
    }

    public static Statement getStatement() {
        return statement;
    }
}
