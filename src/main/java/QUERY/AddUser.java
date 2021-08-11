package QUERY;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AddUser{
    private String sql;

    public AddUser(String name, String password) {

        this.sql = new String("INSERT INTO \"USERS\"(\"NAME\", \"PASSWORD\") " +
                "VALUES ('"+name+"', '"+password+"')");
    }


    public void execute(Statement statement) throws SQLException {
       try {
           statement.executeUpdate(sql);
       }catch (SQLException e){}
        finally {

       }
    }
}
