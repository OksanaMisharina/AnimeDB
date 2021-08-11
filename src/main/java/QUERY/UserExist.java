package QUERY;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserExist{
    private String sql;
    private Statement statement;

    public UserExist(Statement statement) {
        this.sql = new String("SELECT * FROM public.\"USERS\"");
        this.statement = statement;
    }

    public boolean execute(String name, String password) throws SQLException {
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            if (resultSet.getString("NAME").equals(name) &&
                    resultSet.getString("PASSWORD").equals(password))
                return true;

        }
        return false;
    }

    public boolean execute(String name) throws SQLException {
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            if (resultSet.getString("NAME").equals(name))
                return true;

        }
        return false;
    }

}
